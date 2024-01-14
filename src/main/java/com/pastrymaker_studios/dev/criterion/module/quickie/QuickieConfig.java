package com.pastrymaker_studios.dev.criterion.module.quickie;

import com.google.common.collect.Maps;
import com.pastrymaker_studios.dev.criterion.module.base.io.ModuleConfig;
import com.pastrymaker_studios.dev.criterion.module.modulemanagement.SemVer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QuickieConfig extends ModuleConfig {
    public QuickieConfig(String moduleName) {
        super(moduleName);
    }

    /**
     * @return
     */
    @Override
    protected Map<String, Object> getDefaults() {
        SemVer modVer = new SemVer(0, 0, 1);
        HashMap<String, Object> defaults = Maps.newHashMap();
        defaults.put("module-name", "Quickie");




        // Must be added last. Use modVer.incXXX() when adding/changing defaults or other reads.
        // Avoid removing existing adds and instead add a new call to remove it from the map.
        defaults.put("module-version", modVer);
        return defaults;
    }
}
