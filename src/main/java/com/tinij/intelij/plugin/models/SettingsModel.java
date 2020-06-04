package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public class SettingsModel {
    public String trackActivityURL;
    public String activityLogFile;
    public String logFile;
    public String settingsFile;
    public String apiKey;
    public int debugLevel;
    public boolean memoryBasedQueue;

    @SerializedName("enable.notifications")
    public boolean enableNotifications;

    public SettingsModel() {
        trackActivityURL = "https://api.tinij.com/v1/collector/activity/bulk";
        activityLogFile = ".tinij_log.json";
        logFile = ".tinij.log";
        settingsFile = ".tinij_settings.json";
        debugLevel = 5;
        memoryBasedQueue = false;
        apiKey = "";
        enableNotifications = false;
    }

    SettingsModel(String apiKey) {
        this.apiKey = apiKey;
    }
}
