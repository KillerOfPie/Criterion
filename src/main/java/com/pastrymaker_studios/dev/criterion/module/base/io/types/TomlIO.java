package com.pastrymaker_studios.dev.criterion.module.base.io.types;

import de.leonhard.storage.Toml;

import java.io.File;

public class TomlIO extends Toml implements FlatFileIO {
    public TomlIO(File file) {
        super(file);
    }
}
