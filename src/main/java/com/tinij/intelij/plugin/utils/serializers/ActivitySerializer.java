package com.tinij.intelij.plugin.utils.serializers;

import com.google.gson.*;
import com.tinij.intelij.plugin.models.ActivityTypeEnum;

import java.lang.reflect.Type;

public class ActivitySerializer implements JsonSerializer<ActivityTypeEnum> {

    @Override
    public JsonElement serialize(ActivityTypeEnum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}
