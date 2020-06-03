package com.tinij.intelij.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import jdk.internal.jline.internal.Nullable;

import javax.swing.*;
import java.util.UUID;

public class ApiKeyPanel extends DialogWrapper {

    private final JPanel panel;
    private final JTextField input;
    private static String _api_key = "";

    public ApiKeyPanel(@Nullable Project project) {
        super(project, true);
        setTitle("Tinij API Key");

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
        input.setText("xxxx-xxxx-xxxx-xxxx");
        this.show();
        return input.getText();
    }

    public static String getApiKey() {
        return "";
    }

    public static void setApiKey(String apiKey) {
        //implemente later on
    }

}
