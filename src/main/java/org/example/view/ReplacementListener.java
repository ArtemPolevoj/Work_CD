package org.example.view;

import org.example.controller.ControllerReplacement;

public class ReplacementListener extends MainFrame {
    private final ControllerReplacement controllerReplacement;

    public ReplacementListener(ControllerReplacement controllerReplacement) {
        this.controllerReplacement = controllerReplacement;
    }

    @Override
    public void run() {
        super.run();
                BUTTON_REPLACE.addActionListener(e -> AREA_OUT_TEXT.append(
                        controllerReplacement.replacement(
                            FIELD_REPLACE.getText(),
                            FIELD_REPLACEMENT.getText())));
    }
}
