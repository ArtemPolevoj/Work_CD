package org.example.controller;

import org.example.model.Mandatory;

public class ControllerMandatory {
    private final Mandatory MANDATORY;

    public ControllerMandatory(Mandatory mandatory) {
        MANDATORY = mandatory;
    }
    public String mandatory() {
        return MANDATORY.getResult();
    }
}
