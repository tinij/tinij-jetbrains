package com.tinij.intelij.plugin.eventListeners;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.tinij.intelij.plugin.Tinij;
import org.jetbrains.annotations.NotNull;

public class TinijProjectManagerListener implements ProjectManagerListener {

    private Tinij tinij;

    public TinijProjectManagerListener(Tinij tinij) {
        this.tinij = tinij;
    }

    @Override
    public boolean canCloseProject(@NotNull Project project) {
        this.tinij.immediatelyInvokeSendingQueue();
        return true;
    }

}
