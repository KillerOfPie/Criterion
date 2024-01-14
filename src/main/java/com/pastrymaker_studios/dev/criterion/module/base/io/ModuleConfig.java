package com.pastrymaker_studios.dev.criterion.module.base.io;

import com.pastrymaker_studios.dev.criterion.module.base.io.types.FlatFileIO;
import de.leonhard.storage.Config;

import java.util.Map;

public abstract class ModuleConfig extends Config implements FlatFileIO {
    public ModuleConfig(String moduleName) {
        super(moduleName + ".conf.yml", configPath.getAbsolutePath());
        addDefaultsFromMap(getDefaults());
    }


    protected abstract Map<String, Object> getDefaults();


}
