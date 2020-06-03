package com.tinij.intelij.plugin.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinij.intelij.plugin.utils.TinijConstantsHandler;
import com.tinij.intelij.plugin.models.*;
import com.tinij.intelij.plugin.utils.serializers.ActivitySerializer;
import com.tinij.intelij.plugin.utils.serializers.CategorySerializer;
import com.tinij.intelij.plugin.utils.serializers.PlatformSerializer;
import com.tinij.intelij.plugin.utils.serializers.PluginSerializer;
import org.jetbrains.concurrency.Promise;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ApiService {
    private Gson gson;

    public ApiService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ActivityTypeEnum.class, new ActivitySerializer());
        gsonBuilder.registerTypeAdapter(CategoryEnum.class, new CategorySerializer());
        gsonBuilder.registerTypeAdapter(PlatformTypeEnum.class, new PlatformSerializer());
        gsonBuilder.registerTypeAdapter(PluginTypeEnum.class, new PluginSerializer());
        this.gson = gsonBuilder.create();
    }

    Promise<Boolean> sendApiRequest(ArrayList<ActivityModel> activities) {
        String json = this.gson.toJson(activities);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TinijConstantsHandler.ACTIVITY_URL))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .header("TINIJ-API-KEY", "")
                .header("User-Agent", "tinij/intelij:1.0")
                .build();


        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return null;
    };
}
