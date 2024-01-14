package com.pastrymaker_studios.dev.criterion.module.base.io.types;

import de.leonhard.storage.Json;

import java.io.File;

public class JsonIO extends Json implements FlatFileIO {
    private JsonIO(File file) {
        super(file);
    }
}
