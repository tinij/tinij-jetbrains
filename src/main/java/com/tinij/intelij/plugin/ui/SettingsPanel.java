package com.tinij.intelij.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.tinij.intelij.plugin.services.SettingsService;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;


public class SettingsPanel extends DialogWrapper {
    private final JPanel panel;
    private final JLabel apiKeyLabel;
    private final JTextField apiKey;
    private SettingsService settingsService;

    public SettingsPanel(@Nullable Project project, SettingsService settingsService) {
        super(project, true);
        this.settingsService = settingsService;
        setTitle("TiniJ Settings");
        setOKButtonText("Save");
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));

        apiKeyLabel = new JLabel("API Key:", JLabel.CENTER);
        panel.add(apiKeyLabel);
        apiKey = new JTextField(36);
        apiKey.setText(settingsService.getApiKey());
        panel.add(apiKey);

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    @Override
    protected ValidationInfo doValidate() {
        try {
            UUID.fromString(apiKey.getText());
        } catch (Exception e) {
            return new ValidationInfo("Invalid api key.");
        }
        return null;
    }

    @Override
    public void doOKAction() {
        settingsService.setApiKey(apiKey.getText());
        super.doOKAction();
    }

}
