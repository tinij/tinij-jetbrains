package com.tinij.intelij.plugin.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class TinijPluginMenu extends AnAction {

    public TinijPluginMenu() {
        super("Tinij Settings");
    }

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
    }
}
