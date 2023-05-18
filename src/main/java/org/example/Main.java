package org.example;

import org.example.controller.ControllerButton;
import org.example.model.*;
import org.example.view.MainFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Replacement replacement = new Replacement();
        Mandatory mandatory = new Mandatory();
        ControllerButton controllerButton = new ControllerButton(mandatory, replacement);
        SwingUtilities.invokeLater(new MainFrame(controllerButton));


    }
}