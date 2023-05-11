package org.example;

import org.example.controller.Controller;
import org.example.model.*;
import org.example.view.MainFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Replacement replacement = new Replacement();
        Mandatory mandatory = new Mandatory();
        Controller controller = new Controller(mandatory,replacement);

        SwingUtilities.invokeLater(new MainFrame(controller));

    }
}