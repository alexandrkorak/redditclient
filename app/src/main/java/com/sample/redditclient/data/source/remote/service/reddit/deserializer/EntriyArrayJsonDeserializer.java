package com.sample.redditclient.data.source.remote.service.reddit.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sample.redditclient.data.Entry;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by osarapul on 16.11.2017.
 */

public class EntriyArrayJsonDeserializer implements JsonDeserializer<Entry[]> {
    @Override
    public Entry[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Entry> entries = new LinkedList<>();
        JsonArray jsonElements = ((JsonObject) json).getAsJsonObject("data").getAsJsonArray("children");
        for (int i = 0; i < jsonElements.size(); i++) {
            Entry entry = new Entry();
            JsonObject jsonObject = ((JsonObject) jsonElements.get(i)).getAsJsonObject("data");
            entry.title = jsonObject.get("title").getAsString();
            entry.author = jsonObject.get("author").getAsString();
            try {
                entry.thumbnail = new URL(jsonObject.get("thumbnail").getAsString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            entry.numberOfComments = jsonObject.get("num_comments").getAsInt();
            entry.dataTimeCreated = jsonObject.get("created_utc").getAsLong();
            JsonObject previewJsonObject = jsonObject.getAsJsonObject("preview");
            if (previewJsonObject != null) {
                try {
                    entry.sourceImageUrl = new URL(((JsonObject) previewJsonObject.getAsJsonArray("images").get(0)).getAsJsonObject("source")
                            .get("url").getAsString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            entries.add(entry);
        }
        return entries.toArray(new Entry[entries.size()]);
    }
}
