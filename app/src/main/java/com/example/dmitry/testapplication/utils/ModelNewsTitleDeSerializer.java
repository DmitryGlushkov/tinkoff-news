package com.example.dmitry.testapplication.utils;

import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ModelNewsTitleDeSerializer implements JsonDeserializer<ModelNewsTitle> {
    @Override
    public ModelNewsTitle deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ModelNewsTitle modelNewsTitle = new ModelNewsTitle();
        modelNewsTitle.id = json.getAsJsonObject().get("id").getAsString();
        modelNewsTitle.text = json.getAsJsonObject().get("text").getAsString();
        modelNewsTitle.publicationDate = json.getAsJsonObject().get("publicationDate").getAsJsonObject().get("milliseconds").getAsLong();
        return modelNewsTitle;
    }
}
