package com.tinij.intelij.plugin.utils.serializers;

import com.google.gson.*;
import com.tinij.intelij.plugin.models.PluginTypeEnum;

import java.lang.reflect.Type;

public class PluginSerializer implements JsonSerializer<PluginTypeEnum> {

    @Override
    public JsonElement serialize(PluginTypeEnum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}

