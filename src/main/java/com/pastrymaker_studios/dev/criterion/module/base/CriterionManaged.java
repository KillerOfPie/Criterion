package com.pastrymaker_studios.dev.criterion.module.base;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.pastrymaker_studios.dev.criterion.module.modulemanagement.ManagedImportance;
import com.pastrymaker_studios.dev.criterion.module.modulemanagement.SemVer;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Getter
@SuppressWarnings("unused")
public abstract class CriterionManaged {

    @NotNull
    protected Map<String, Object> data;

    @NotNull
    protected ManagedImportance importance;


    CriterionManaged() {
        data = Collections.emptySortedMap();
        importance = ManagedImportance.NONE;
    }

    @NotNull
    @OverridingMethodsMustInvokeSuper
    @SuppressWarnings("unchecked")
    protected <T> Optional<T> getDataVal(String key) {
        Optional<T> d = Optional.empty();
        try {
            d = (Optional<T>) Optional.of(Variables.valueOf(key.toLowerCase()).getDEFAULT());
            return Optional.of((T) data.get(key));
        } catch (ClassCastException|NullPointerException ignored) {
            return d;
        }
    }

    @NotNull
    @OverridingMethodsMustInvokeSuper
    public Map<String, Object> serialize() {
        return data;
    }

    @OverridingMethodsMustInvokeSuper
    public void deserialize(@NotNull Map<String, Object> map) {
        data.putAll(map);
    }

}
