package com.tinij.intelij.plugin.utils.serializers;

import com.google.gson.*;
import com.tinij.intelij.plugin.models.PlatformTypeEnum;

import java.lang.reflect.Type;


public class PlatformSerializer implements JsonSerializer<PlatformTypeEnum> {

    @Override
    public JsonElement serialize(PlatformTypeEnum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}

