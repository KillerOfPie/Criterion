package com.pastrymaker_studios.dev.criterion.module.modulemanagement;

import com.pastrymaker_studios.dev.criterion.module.base.CriterionManaged;

import java.util.Collections;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Manager {

    private static Manager instance;
    private SortedMap<ManagedImportance , Set<CriterionManaged>> managed;

    protected Manager() {
        if (instance != null) {
            throw new UnsupportedOperationException("Attempting to create further instance of singleton class " + this.getClass() + "!");
        }

        reset();
    }

    public Manager getManager() {
        if(instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public void reset() {
        managed = new TreeMap<>();
        Stream.of(ManagedImportance.values()).forEach(importance -> managed.put(importance , Collections.emptySet()));
    }

    public boolean register(CriterionManaged managed) {
        return this.managed.get(managed.getImportance()).add(managed);
    }

    public void unregister(CriterionManaged managed) {
        this.managed.forEach((importance , managedSet) -> managedSet.remove(managed));
    }
}
