package org.example.controller;

import org.example.model.Replacement;

public class ControllerReplacement {
    private final Replacement replacement;

    public ControllerReplacement(Replacement replacement) {
        this.replacement = replacement;
    }

    public String replacement( String worldReplace, String worldReplacement){

        replacement.setWorldReplace(worldReplace);
        replacement.setWorldReplacement(worldReplacement);
        return replacement.getResult();

    }
}
