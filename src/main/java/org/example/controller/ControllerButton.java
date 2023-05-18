package org.example.controller;

import org.example.model.Mandatory;
import org.example.model.Replacement;

public class ControllerButton {
    private final Mandatory MANDATORY;
    private final Replacement REPLACEMENT;

    public ControllerButton(Mandatory mandatory, Replacement replacement) {
        MANDATORY = mandatory;
        REPLACEMENT = replacement;
    }

    public String mandatory() {
        return MANDATORY.getResult();
    }


    public String replacement(String worldReplace, String worldReplacement) {
        return REPLACEMENT.getResult(worldReplace, worldReplacement);
    }

}