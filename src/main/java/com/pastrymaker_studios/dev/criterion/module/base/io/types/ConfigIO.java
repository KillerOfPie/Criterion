package com.pastrymaker_studios.dev.criterion.module.base.io.types;

import de.leonhard.storage.Config;

import java.io.File;

public class ConfigIO extends Config implements FlatFileIO {
    public ConfigIO(File file) {
        super(file);
    }
}
