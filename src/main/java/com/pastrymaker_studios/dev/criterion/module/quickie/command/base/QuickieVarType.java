package com.pastrymaker_studios.dev.criterion.module.quickie.command.base;

import com.bergerkiller.bukkit.common.utils.MaterialUtil;
import com.bergerkiller.bukkit.common.utils.ParseUtil;
import com.pastrymaker_studios.dev.criterion.Criterion;
import de.leonhard.storage.shaded.json.JSONArray;
import de.leonhard.storage.shaded.json.JSONException;
import de.leonhard.storage.shaded.json.JSONObject;
import org.bukkit.OfflinePlayer;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Optional;
import java.util.function.Function;

public enum QuickieVarType {

    STRING(Optional::of),
    JSON((s) -> {
        try {
            return Optional.of(new JSONObject(s));
        } catch (JSONException e) {
            try {
                return Optional.of(new JSONArray(s));
            } catch (JSONException ignored) { }
        }
        return Optional.empty();
    }),
    INT((s) -> {
        try {
            return Optional.of(NumberFormat.getInstance().parse(s).intValue());
        } catch (ParseException ignored) { }

        return Optional.empty();
    }),
    DOUBLE((s) -> {
        try {
            return Optional.of(NumberFormat.getInstance().parse(s).doubleValue());
        } catch (ParseException ignored) { }

        return Optional.empty();
    }),
    FLOAT((s) -> {
        try {
            return Optional.of(NumberFormat.getInstance().parse(s).floatValue());
        } catch (ParseException ignored) { }

        return Optional.empty();
    }),
    LONG((s) -> {
        try {
            return Optional.of(NumberFormat.getInstance().parse(s).longValue());
        } catch (ParseException ignored) { }

        return Optional.empty();
    }),
    PLAYER_OFFLINE((s) -> {
        OfflinePlayer pl = Criterion.getInstance().getServer().getOfflinePlayer(s);
        return pl.hasPlayedBefore() ? Optional.of(pl) : Optional.empty();
    }),
    PLAYER((s) -> {
        OfflinePlayer pl = Criterion.getInstance().getServer().getPlayer(s);
        return pl != null ? Optional.of(pl) : Optional.empty();
    }),
    BOOLEAN((s) -> ParseUtil.isBool(s) ? Optional.of(ParseUtil.parseBool(s)) : Optional.empty()),
    TIME((s) -> {
        long time = ParseUtil.parseTime(s);

        return time > 0 ? Optional.of(time) : Optional.empty();
    }),
    MATERIAL((s) -> {
        try {
            return Optional.of(MaterialUtil.getFirst(s));
        } catch (RuntimeException ignored) { }

        return Optional.empty();
    });

    private final Function<String, Optional<?>> reader;

    QuickieVarType(Function<String, Optional<?>> reader) {
        this.reader = reader;
    }

    public boolean validate(String arg) {
        return reader.apply(arg).isPresent();
    }

    public <T> T read(String arg) {
        Optional<?> optional = reader.apply(arg);

        try {
            if (optional.isPresent()) {
                return (T) optional.get();
            }
        } catch (ClassCastException ignored) { }

        return null;
    }

}
