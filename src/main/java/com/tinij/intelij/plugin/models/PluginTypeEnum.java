package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public enum PluginTypeEnum {
    @SerializedName("-1")
    UNKNOWN(-1),
    @SerializedName("0")
    VSCODE(0),
    @SerializedName("1")
    INTELIJ_IDEA(1),
    @SerializedName("1")
    WEBSTORM(2),
    @SerializedName("1")
    PHPSTORM(3),
    @SerializedName("1")
    CLion(4),
    @SerializedName("1")
    DataGrip(5),,
    @SerializedName("1")
    AppCode(6),,
    @SerializedName("1")
    GoLand(7),
    @SerializedName("1")
    Rider(8),
    @SerializedName("1")
    RubyMine(8);

    private final int value;

    private PluginTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
