package com.tinij.intelij.plugin.services;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.models.ActivityModel;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityObjectFactory {

    private ActivityComposerService activityComposerService;

    public ActivityObjectFactory(ActivityComposerService activityComposerService) {
        this.activityComposerService = activityComposerService;
    }

    public ActivityModel buildActivity(final VirtualFile file, Project project, String time, boolean isWrite) {

        ActivityModel activityModel = new ActivityModel();

        activityModel.setEntity(file.getPath());
        activityModel.setTime(time);
        activityModel.setIs_write(isWrite);
        activityModel.setLanguage(this.activityComposerService.getLanguage(file));
        activityModel.setBranch(this.activityComposerService.getBranch(project));
        activityModel.setPlugin(this.activityComposerService.getCurrentPlugin());
        activityModel.setCategory(this.activityComposerService.getCurrentCategory());
        activityModel.setMachine(this.activityComposerService.getCurrentMachineName());
        activityModel.setSystem(this.activityComposerService.getCurrentPlatform());
        activityModel.setProject(this.activityComposerService.getCurrentProject(project));
        activityModel.setType(this.activityComposerService.getCurrentActivity());

        return activityModel;
    }

}
