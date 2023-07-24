package org.example.controller;

import org.example.model.Mandatory;
import org.example.view.TextArea;

public class ContMand implements TextArea {
    private final Mandatory mandatory;

    public ContMand(Mandatory mandatory) {
        this.mandatory = mandatory;
    }
    public  void mandat(){
        areaOutText.append(mandatory.getResult());
    }
}
