package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public enum PluginTypeEnum {
    @SerializedName("-1")
    UNKNOWN(-1),
    @SerializedName("0")
    VSCODE(0),
    @SerializedName("1")
    IDEA(1);

    private final int value;

    private PluginTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
