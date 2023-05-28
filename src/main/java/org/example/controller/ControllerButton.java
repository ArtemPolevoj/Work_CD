package org.example.controller;

import org.example.model.Mandatory;
import org.example.model.Replacement;

public class ControllerButton {
    private final Mandatory mandatory;
    private final Replacement replacement;

    public ControllerButton(Mandatory mandatory, Replacement replacement) {
        this.mandatory = mandatory;
        this.replacement = replacement;
    }

    public String mandatory() {
        return mandatory.getResult();
    }


    public String replacement(String worldReplace, String worldReplacement) {
        return replacement.getResult(worldReplace, worldReplacement);
    }

}