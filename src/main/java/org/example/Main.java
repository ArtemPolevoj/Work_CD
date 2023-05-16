package org.example;

import org.example.controller.ControllerMandatory;
import org.example.controller.ControllerReplacement;
import org.example.model.*;
import org.example.view.MandatoryListener;
import org.example.view.ReplacementListener;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Replacement replacement = new Replacement();
        Mandatory mandatory = new Mandatory();

        ControllerMandatory controllerMandatory = new ControllerMandatory(mandatory);
        ControllerReplacement controllerReplacement = new ControllerReplacement(replacement);

        SwingUtilities.invokeLater(new ReplacementListener(controllerReplacement));
        SwingUtilities.invokeLater(new MandatoryListener(controllerMandatory));

    }
}