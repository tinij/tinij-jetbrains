package com.tinij.intelij.plugin.utils.serializers;

import com.google.gson.*;
import com.tinij.intelij.plugin.models.ActivityTypeEnum;
import com.tinij.intelij.plugin.models.CategoryEnum;

import java.lang.reflect.Type;

public class CategorySerializer implements JsonSerializer<CategoryEnum> {

    @Override
    public JsonElement serialize(CategoryEnum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}
