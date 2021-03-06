package com.tinij.intelij.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.tinij.intelij.plugin.services.SettingsService;
import jdk.internal.jline.internal.Nullable;

import javax.swing.*;
import java.util.UUID;

public class ApiKeyPanel extends DialogWrapper {

    private final JPanel panel;
    private final JTextField input;
    private SettingsService settingsService;

    public ApiKeyPanel(@Nullable Project project, SettingsService settingsService) {
        super(project, true);
        setTitle("Tinij API Key");

        this.settingsService = settingsService;

        setOKButtonText("Save");
        panel = new JPanel();
        input = new JTextField(36);
        panel.add(input);

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    @Override
    protected ValidationInfo doValidate() {
        String apiKey = input.getText();
        try {
            UUID.fromString(apiKey);
        } catch (Exception e) {
            return new ValidationInfo("Invalid TiniJ API key.");
        }
        return null;
    }

    @Override
    public void doOKAction() {
        super.doOKAction();
    }

    public String promptForApiKey() {
        input.setText(settingsService.getApiKey());
        this.show();
        return input.getText();
    }
}
