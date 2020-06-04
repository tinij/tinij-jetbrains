package com.tinij.intelij.plugin.services;

import com.google.gson.Gson;
import com.tinij.intelij.plugin.Tinij;
import com.tinij.intelij.plugin.models.SettingsModel;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsService {

    private final String fileName = ".tinij_settings.json";
    private String cachedConfigFilePath = null;
    private SettingsModel cachedConfigFile;
    private String cachedApiKey;

    private String getConfigFilePath() {
        if (this.cachedConfigFilePath == null) {
            this.cachedConfigFilePath = new File(System.getProperty("user.home"), this.fileName).getAbsolutePath();
            Tinij.log.debug("Using $HOME for config folder: " + this.cachedConfigFilePath);
        }
        return this.cachedConfigFilePath;
    }

    public void initConfigFileIfRequired() {
        Gson gson = new Gson();
        String file = this.getConfigFilePath();
        boolean isInitRequired = false;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(file));
            SettingsModel settingsModel = gson.fromJson(reader, SettingsModel.class);
            if (settingsModel == null || settingsModel.activityLogFile == null)
                isInitRequired = true;
            else {
                this.cachedConfigFile = settingsModel;
            }
        } catch (IOException e) {
            isInitRequired = true;
        }
        if (isInitRequired) {
            try {
                SettingsModel defaultSettings = new SettingsModel();
                Writer writer = Files.newBufferedWriter(Paths.get(file));
                gson.toJson(defaultSettings, writer);
                writer.close();
                cachedConfigFile = defaultSettings;
            } catch (IOException e) {
                Tinij.log.error("Failed to init TiniJ config file", e);
            }
        }
    }

    private SettingsModel getSettings() {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.getConfigFilePath()));
            return gson.fromJson(reader, SettingsModel.class);
        } catch (IOException e) {
            Tinij.log.error("Failed to read TiniJ config file", e);
            return null;
        }
    }


    public String getApiKey() {
        if (cachedApiKey != null)
            return cachedApiKey;
        if (this.cachedConfigFile == null) {
            this.cachedConfigFile = getSettings();
        }
        if (this.cachedConfigFile != null) {
            cachedApiKey = this.cachedConfigFile.apiKey;
            return cachedApiKey;
        } else {
            Tinij.log.error("TiniJ not able to read the API KEY settings");
            return "";
        }
    }

    public void setApiKey(String newApiKey) {
        Gson gson = new Gson();
        cachedApiKey = newApiKey;
        if (this.cachedConfigFile == null) {
            this.cachedConfigFile = getSettings();
        }
        try {
            if (this.cachedConfigFile != null) {
                SettingsModel newSettings = this.cachedConfigFile;
                newSettings.apiKey = newApiKey;
                Writer writer = Files.newBufferedWriter(Paths.get(this.getConfigFilePath()));
                gson.toJson(newSettings, writer);
                writer.close();
                cachedConfigFile = newSettings;

            } else {
                Tinij.log.error("TiniJ not able to write to the API KEY settings");
            }
        }
        catch (IOException e) {
            Tinij.log.error("TiniJ not able to set the API KEY", e);
        }
    }
}
