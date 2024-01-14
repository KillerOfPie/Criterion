package com.pastrymaker_studios.dev.criterion.module.base;

import lombok.Setter;
import se.eris.notnull.NotNull;
import se.eris.notnull.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class CriterionCommand extends CriterionManaged {

    String name, description, usage;
    Map<String, CriterionCommand> children;
    Set<String> aliases;

    protected CriterionCommand(String name, @Nullable String description, @Nullable String usage, @Nullable Map<String, CriterionCommand> children, @Nullable Set<String> aliases) {
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.usage = usage != null ? usage : "";
        this.children = children != null ? children : Collections.emptyMap();
        this.aliases = aliases != null ? aliases : Collections.emptySet();
    }

    public static CritCmdFactory factory(String name) {
        return new CritCmdFactory(name);
    }

}

class CritCmdFactory {

    String name;
    @Setter(onParam_={@NotNull})
    String description = null, usage = null;
    @Setter(onParam_={@NotNull})
    Map<String, CriterionCommand> children = null;
    @Setter(onParam_={@NotNull})
    Set<String> aliases = null;

    public CritCmdFactory(String name) {
        this.name = name;
    }

    public CriterionCommand build() {
        return new CriterionCommand(name, description, usage, children, aliases);
    }


}
