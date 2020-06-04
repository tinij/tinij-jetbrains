package com.tinij.intelij.plugin.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.tinij.intelij.plugin.Tinij;

public class TinijPluginMenu extends AnAction {

    public TinijPluginMenu() {
        super("Tinij Settings");
    }

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        SettingsPanel popup = new SettingsPanel(project, Tinij.instance.getSettingsService());
        popup.show();
    }
}
