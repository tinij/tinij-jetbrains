package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public enum ActivityTypeEnum {

    @SerializedName("0")
    File(0),

    @SerializedName("1")
    Domain(1);

    private final int value;

    private ActivityTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
