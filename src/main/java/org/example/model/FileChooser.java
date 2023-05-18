package org.example.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

abstract class FileChooser extends JFileChooser {
    JFileChooser fileChooser = new JFileChooser();

    void settingExcel() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter
                ("Excel.xlsx", "xlsx"));
    }
}
