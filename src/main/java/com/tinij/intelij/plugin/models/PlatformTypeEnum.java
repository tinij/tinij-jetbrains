package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public enum PlatformTypeEnum {
    @SerializedName("-1")
    UNKNOWN(-1),
    @SerializedName("0")
    Unix(0),
    @SerializedName("1")
    MacOS(1),
    @SerializedName("2")
    FreeBSD(2),
    @SerializedName("3")
    Linux(3),
    @SerializedName("4")
    OpenBSD(4),
    @SerializedName("5")
    Solaris(5),
    @SerializedName("6")
    Windows(6);

    private final int value;

    private PlatformTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
