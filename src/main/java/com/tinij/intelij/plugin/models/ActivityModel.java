package com.tinij.intelij.plugin.models;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityModel {
    private String entity;
    private ActivityTypeEnum type;
    private CategoryEnum category;
    private PluginTypeEnum plugin;
    private PlatformTypeEnum system;
    private String machine;
    private String time;
    private String project;
    private String branch;
    private String language;

    private int lines;
    private int lineno;
    private boolean is_write;

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setType(ActivityTypeEnum type) {
        this.type = type;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public void setPlugin(PluginTypeEnum plugin) {
        this.plugin = plugin;
    }

    public void setSystem(PlatformTypeEnum system) {
        this.system = system;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public void setIs_write(boolean is_write) {
        this.is_write = is_write;
    }
}
