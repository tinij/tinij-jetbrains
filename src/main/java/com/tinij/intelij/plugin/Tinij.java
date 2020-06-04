package com.tinij.intelij.plugin;

import com.intellij.AppTopics;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PlatformUtils;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.tinij.intelij.plugin.eventListeners.*;
import com.tinij.intelij.plugin.models.ActivityModel;
import com.tinij.intelij.plugin.services.*;
import com.tinij.intelij.plugin.ui.ApiKeyPanel;
import com.tinij.intelij.plugin.utils.TinijConstantsHandler;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.time.Instant;

public class Tinij implements BaseComponent {

    public static final Logger log = (Logger) Logger.getInstance("Tinij");
    public static Boolean DEBUG = false;

    private ApiService apiService;
    private QueueHandler queueHandler;
    private ActivityComposerService activityComposerService;
    private ActivityObjectFactory activityObjectFactory;
    private SettingsService settingsService;

    public static Tinij instance = null;
    private static String lastFile = null;
    static BigDecimal lastTime = new BigDecimal(0);
    private static MessageBusConnection connection;

    public Tinij() {
        instance = this;
    }

    @Override
    public void initComponent() {
        IdeaPluginDescriptor currentPlugin = PluginManager.getPlugin(PluginId.getId("com.tinij.intellij.plugin"));
        if (currentPlugin != null) {
            TinijConstantsHandler.PLUGIN_VERSION = currentPlugin.getVersion();
        } else {
            TinijConstantsHandler.PLUGIN_VERSION = "unknown";
        }
        log.info("Initializing Tinij (tinij.com) plugin v" + TinijConstantsHandler.PLUGIN_VERSION);

        TinijConstantsHandler.IDE_NAME = PlatformUtils.getPlatformPrefix();
        TinijConstantsHandler.IDE_VERSION = ApplicationInfo.getInstance().getFullVersion();

        bootStrap();
    }

    private void bootStrap() {
        initServices();
        prepareListeners();
        checkApiKey();
    }

    private void initServices() {
        this.settingsService = new SettingsService();
        this.apiService = new ApiService(this.settingsService);
        this.queueHandler = new QueueHandler(this.apiService);
        this.activityComposerService = new ActivityComposerService();
        this.activityObjectFactory = new ActivityObjectFactory(this.activityComposerService);
        this.queueHandler.startQueueWorker();

        this.settingsService.initConfigFileIfRequired();
    }


    private void prepareListeners() {
        ApplicationManager.getApplication().invokeLater(() -> {
            // save file
            MessageBus bus = ApplicationManager.getApplication().getMessageBus();
            connection = bus.connect();
            connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new TinijSaveListener(this));

            ProjectManager.getInstance().addProjectManagerListener(new TinijProjectManagerListener(this));

            EditorFactory editFactory = EditorFactory.getInstance();
            EditorEventMulticaster editorEventMulticaster = editFactory.getEventMulticaster();

            editorEventMulticaster.addDocumentListener(new TinijDocumentListener(this));
            editorEventMulticaster.addEditorMouseListener(new TinijEditorMouseListener(this));
            editorEventMulticaster.addVisibleAreaListener(new TinijVisibleAreaListener(this));
        });
    }

    public void addActivity(final VirtualFile file, Document project, final boolean isWrite) {
        this.addActivity(file, this.activityComposerService.getProject(project), isWrite);
    }

    public void immediatelyInvokeSendingQueue() {
        this.queueHandler.invokeSendingToBackend();
    }


    public void addActivity(final VirtualFile file, Project project, final boolean isWrite) {
        if (!TinijHelpers.shouldLogFile(file))
            return;
        final BigDecimal time = TinijHelpers.getCurrentTime();
        if (!isWrite && file.getPath().equals(Tinij.lastFile) && !TinijHelpers.enoughTimePassed(time)) {
            return;
        }
        Tinij.lastFile = file.getPath();
        Tinij.lastTime = time;

        ApplicationManager.getApplication().executeOnPooledThread((Runnable) () -> {
            ActivityModel activityModel = this.activityObjectFactory.buildActivity(file, project, Instant.now().toString(), isWrite);
            this.queueHandler.pushToQueue(activityModel);
        });
    }

    public SettingsService getSettingsService() {
        return this.settingsService;
    }

    private void checkApiKey() {
        ApplicationManager.getApplication().invokeLater(() -> {
            Project project = null;
            try {
                project = ProjectManager.getInstance().getDefaultProject();
            } catch (Exception e) { }
            ApiKeyPanel apiKeyPanel = new ApiKeyPanel(project, this.settingsService);
            String currentApiKey = this.settingsService.getApiKey();
            if (currentApiKey == null || currentApiKey.equals("")) {
                apiKeyPanel.promptForApiKey();
            }
        });
    }
}
