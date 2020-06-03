package com.tinij.intelij.plugin.models;

import com.google.gson.annotations.SerializedName;

public enum  CategoryEnum {
    @SerializedName("0")
    CODING(0),
    @SerializedName("1")
    BUILDING(1),
    @SerializedName("2")
    INDEXING (2),
    @SerializedName("3")
    DEBUGING (3),
    @SerializedName("4")
    BROWSING (4),
    @SerializedName("5")
    RESEARCHING (5),
    @SerializedName("6")
    CODE_REVIEWING(6),
    @SerializedName("7")
    RUNNING_TESTS(7),
    @SerializedName("8")
    WRITING_TESTS (8),
    @SerializedName("9")
    WRITING_DOCS (9),
    @SerializedName("10")
    DESIGNING (10),
    @SerializedName("11")
    LEARNING (11);

    private final int value;

    private CategoryEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
