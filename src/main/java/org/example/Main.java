package org.example;

import org.example.controller.ContMand;
import org.example.controller.ContRepl;
import org.example.model.*;
import org.example.view.MainFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Replacement replacement = new Replacement();
        Mandatory mandatory = new Mandatory();
        ContRepl contRepl = new ContRepl(replacement);
        ContMand contMand = new ContMand(mandatory);
        SwingUtilities.invokeLater(new MainFrame(contRepl, contMand));


    }
}