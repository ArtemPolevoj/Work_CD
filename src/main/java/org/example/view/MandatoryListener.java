package org.example.view;

import org.example.controller.ControllerMandatory;

public class MandatoryListener extends MainFrame {
    private final ControllerMandatory ControllerMandatory;

    public MandatoryListener(ControllerMandatory controllerMandatory) {
        this.ControllerMandatory = controllerMandatory;
    }

    @Override
    public void run() {
        super.run();
        BUTTON_MANDATORY.addActionListener(e ->
                AREA_OUT_TEXT.append(ControllerMandatory.mandatory()));
    }
}
