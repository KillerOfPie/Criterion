package com.pastrymaker_studios.dev.criterion.utilities;

import de.leonhard.storage.shaded.json.JSONArray;
import de.leonhard.storage.shaded.json.JSONException;
import de.leonhard.storage.shaded.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Optional;

public class ZipIt<Type> {

    private final Type content;


    public ZipIt(Type content) {
        this.content = content;
    }

    public Type get() {
        return content;
    }

    public <T> Optional<T> getAdapted() {
        try {
            return Optional.of((T) content);
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

    public Optional<JSONObject> asJsonObject() {
        if(!(content instanceof String)) return Optional.empty();

        String contentStr = (String) this.content;

        try {
            return Optional.of(new JSONObject(contentStr));
        } catch (JSONException ignored) { }
        return Optional.empty();
    }

    public Optional<JSONArray> asJsonArray() {
        if(!(content instanceof String)) return Optional.empty();

        String contentStr = (String) this.content;

        try {
                return Optional.of(new JSONArray(contentStr));
            } catch (JSONException ignored) { }
        return Optional.empty();
    }

    /**
     * Attempts return a number by Casting to {@link Number}.
     *
     * @return An optional containing the number, or empty if the content is not a number.
     */
    public Optional<Number> asNumber() {
        return (content instanceof Number) ? Optional.of((Number) content) : Optional.empty();
    }

    /**
     * Attempts to parse the content as a number by using {@link NumberFormat#parse(String)} and the toString value of content.
     *
     * @return An optional containing the parsed number, or empty if the content is not a number.
     */
    public Optional<Number> toNumber() {
        try {
            return Optional.of(NumberFormat.getInstance().parse(content.toString()));
        } catch (ParseException ignored) { }

        return Optional.empty();
    }
}
