package com.tinij.intelij.plugin.services;

import com.tinij.intelij.plugin.models.ActivityModel;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class QueueHandler {

    private final int queueTimeoutSeconds = 30;
    private ConcurrentLinkedQueue<ActivityModel> activitiesQueue;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFixture;

    private ApiService apiService;

    public QueueHandler(ApiService apiService) {
        this.activitiesQueue =  new ConcurrentLinkedQueue<ActivityModel>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.apiService = apiService;
    }

    public void startQueueWorker() {
        final Runnable handler = new Runnable() {
            public void run() {
                processHeartbeatQueue();
            }
        };
        long delay = queueTimeoutSeconds;
        scheduledFixture = scheduler.scheduleAtFixedRate(handler, delay, delay, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void pushToQueue(ActivityModel activityModel) {
        activitiesQueue.add(activityModel);
    }

    public void invokeSendingToBackend() {
        processHeartbeatQueue();
    }

    private void processHeartbeatQueue() {
        if (activitiesQueue == null || activitiesQueue.isEmpty())
            return;
        ArrayList<ActivityModel> activeActivities = new ArrayList<ActivityModel>();
        while (true) {
            ActivityModel activityModel = activitiesQueue.poll();
            if (activityModel == null)
                break;
            activeActivities.add(activityModel);
        }
        sendActivities(activeActivities);
    }

    private void sendActivities(ArrayList<ActivityModel> activityModels) {
        this.apiService.sendApiRequest(activityModels);
    }
}
