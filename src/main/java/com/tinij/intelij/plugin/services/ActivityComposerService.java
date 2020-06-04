package com.tinij.intelij.plugin.services;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.models.ActivityTypeEnum;
import com.tinij.intelij.plugin.models.CategoryEnum;
import com.tinij.intelij.plugin.models.PlatformTypeEnum;
import com.tinij.intelij.plugin.models.PluginTypeEnum;
import git4idea.GitLocalBranch;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;


public class ActivityComposerService {

    PlatformTypeEnum detectedOS = null;

    public ActivityComposerService() {

    }

    public String getCurrentProject(Project project) {
        return project != null ? project.getName() : null;
    }

    public Project getProject(Document document) {
        Editor[] editors = EditorFactory.getInstance().getEditors(document);
        if (editors.length > 0) {
            return editors[0].getProject();
        }
        return null;
    }

    public String getLanguage(final VirtualFile file) {
        FileType type = file.getFileType();
        if (type != null)
            return type.getName();
        return null;
    }

    public String getBranch(Project project) {
        List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
        for (GitRepository repository : gitRepositories) {
            GitLocalBranch gitLocalBranch = repository.getCurrentBranch();
            if (gitLocalBranch == null) {
                continue;
            }
            return gitLocalBranch.getName();
        }
        return null;
    }

    public String getCommit() {
        return "";
    }

    public String getCurrentMachineName() {
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            return addr.getHostName();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
        }
        return null;
    }

    public PlatformTypeEnum getCurrentPlatform() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);

            if ((OS.contains("mac")) || (OS.contains("darwin"))) {
                detectedOS = PlatformTypeEnum.MacOS;
            } else if (OS.contains("win")) {
                detectedOS =  PlatformTypeEnum.Windows;
            } else if (OS.contains("nux")) {
                detectedOS = PlatformTypeEnum.Linux;
            } else if (OS.contains("aix")) {
                detectedOS = PlatformTypeEnum.Unix;
            } else if (OS.contains("freebsd")) {
                detectedOS = PlatformTypeEnum.FreeBSD;
            } else if (OS.contains("openbsd")) {
                detectedOS = PlatformTypeEnum.OpenBSD;
            } else if (OS.contains("sunos")) {
                detectedOS = PlatformTypeEnum.Solaris;
            } else {
                detectedOS = PlatformTypeEnum.UNKNOWN;
            }
        }
        return detectedOS;
    }

    public CategoryEnum getCurrentCategory() {
        return CategoryEnum.CODING;
    }

    public ActivityTypeEnum getCurrentActivity() {
        return ActivityTypeEnum.File;
    }

    public PluginTypeEnum getCurrentPlugin() {
        return PluginTypeEnum.INTELIJ_IDEA;
    }
}
