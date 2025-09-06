package org.example.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Open extends JFileChooser {

    public Open(boolean isExcel) {
        if (isExcel) {
            setExcelFiles();
        } else {
            setWordFiles();
        }
        setting();
    }

    private void setExcelFiles() {
        super.addChoosableFileFilter(new FileNameExtensionFilter
                ("Excel.xlsx", "xlsx"));
    }

    private void setWordFiles() {
        super.addChoosableFileFilter(new FileNameExtensionFilter
                ("Word.docx", "docx"));
    }

    private void setting() {
        super.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        super.setMultiSelectionEnabled(true);
        super.setAcceptAllFileFilterUsed(false);
        super.setDialogTitle("Выберете папку или файл(ы) для обработки.");
        super.showOpenDialog(null);
    }
}
