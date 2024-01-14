package com.pastrymaker_studios.dev.criterion.module.base;

import com.pastrymaker_studios.dev.criterion.module.modulemanagement.SemVer;
import de.leonhard.storage.internal.FlatFile;
import lombok.Getter;

public abstract class CriterionIO extends CriterionManaged {

    FlatFile flatfile;


    public CriterionIO() {

    }

}

@Getter
@SuppressWarnings("unused")
enum Vars {
    name("CriterionIO"),
    version(new SemVer(0, 0, 1)),
    iotype("flatfile"),
    iosubtype("json"),
    iodatapath("data/"),
    ioconfigpath("config/");

    private final Object DEFAULT;

    Vars(Object DEFAULT) {
        this.DEFAULT = DEFAULT;
    }
}
