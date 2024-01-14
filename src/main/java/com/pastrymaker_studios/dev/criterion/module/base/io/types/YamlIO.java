package com.pastrymaker_studios.dev.criterion.module.base.io.types;

import de.leonhard.storage.Yaml;

import java.io.File;

public class YamlIO extends Yaml implements FlatFileIO {
    private YamlIO(String extensionFromBase) {
        super(new File(baseDir + extensionFromBase));
    }
}
