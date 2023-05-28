package org.example.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

abstract class FileChooser extends JFileChooser {

    void settingExcel() {
        super.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        super.setMultiSelectionEnabled(true);
        super.setAcceptAllFileFilterUsed(false);
        super.addChoosableFileFilter(new FileNameExtensionFilter
                ("Excel.xlsx", "xlsx"));
    }
}
