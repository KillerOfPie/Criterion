package com.pastrymaker_studios.dev.criterion.module.base.io.types;

import com.pastrymaker_studios.dev.criterion.Criterion;

import java.io.File;

public interface FlatFileIO extends IOType {
    File baseDir = Criterion.getInstance().getDataFolder(),
        configPath = new File(baseDir, "conf" + File.pathSeparator),
        dataPath = new File(baseDir, "data" + File.pathSeparator),
        langPath = new File(baseDir, "lang" + File.pathSeparator);

}
