package org.example.controller;

import org.example.model.Mandatory;
import org.example.model.Replacement;


public class Controller {
    private final Mandatory MANDATORY;
    private final Replacement replacement;

    public Controller(Mandatory mandatory, Replacement replacement) {
        MANDATORY = mandatory;
        this.replacement = replacement;
    }
    public String mandatory() {
        return MANDATORY.getResult();
    }
    public String replacement( String worldReplace, String worldReplacement){

        replacement.setWorldReplace(worldReplace);
        replacement.setWorldReplacement(worldReplacement);
        return replacement.getResult();
    }
}
