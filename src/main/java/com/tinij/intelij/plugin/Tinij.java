package com.tinij.intelij.plugin;

import com.intellij.AppTopics;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.util.PlatformUtils;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.tinij.intelij.plugin.eventListeners.TinijDocumentListener;
import com.tinij.intelij.plugin.eventListeners.TinijEditorMouseListener;
import com.tinij.intelij.plugin.eventListeners.TinijSaveListener;
import com.tinij.intelij.plugin.eventListeners.TinijVisibleAreaListener;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class Tinij implements BaseComponent {
    private static final Logger log = (Logger) Logger.getInstance("Tinij");
    private static MessageBusConnection connection;

    public static String lastFile = null;
    public static BigDecimal lastTime = new BigDecimal(0);

    public static Boolean DEBUG = false;
    public static Boolean READY_TO_ACTION = false;

    @Override
    public void initComponent() {
        TinijConstantsHandler.PLUGIN_VERSION = PluginManager.getPlugin(PluginId.getId("com.tinij.intellij.plugin")).getVersion();
        log.info("Initializing Tinij (tinij.com) plugin v" + TinijConstantsHandler.PLUGIN_VERSION);

        TinijConstantsHandler.IDE_NAME = PlatformUtils.getPlatformPrefix();
        TinijConstantsHandler.IDE_VERSION = ApplicationInfo.getInstance().getFullVersion();

        prepareListeners();
    }

    public void prepareListeners() {
        ApplicationManager.getApplication().invokeLater(() -> {
            // save file
            MessageBus bus = ApplicationManager.getApplication().getMessageBus();
            connection = bus.connect();
            connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new TinijSaveListener());

            EditorFactory editFactory = EditorFactory.getInstance();
            EditorEventMulticaster editorEventMulticaster = editFactory.getEventMulticaster();

            editorEventMulticaster.addDocumentListener(new TinijDocumentListener());
            editorEventMulticaster.addEditorMouseListener(new TinijEditorMouseListener());
            editorEventMulticaster.addVisibleAreaListener(new TinijVisibleAreaListener());
        });
    }


}
