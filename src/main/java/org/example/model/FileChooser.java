package org.example.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public interface FileChooser {
    JFileChooser fileChooser = new JFileChooser();
    static void settingExcel(){
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter
                ("Excel.xlsx", "xlsx"));
    }
}
