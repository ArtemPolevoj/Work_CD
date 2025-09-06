package org.example;

import org.example.controller.ContRepl;
import org.example.model.*;
import org.example.view.MainFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        ReplacementExcel replacementExcel = new ReplacementExcel();
        ReplacementWord replacementWord = new ReplacementWord();
        ContRepl contRepl = new ContRepl(replacementExcel, replacementWord);
        MainFrame mainFrame = new MainFrame(contRepl);
        SwingUtilities.invokeLater(mainFrame);
    }
}