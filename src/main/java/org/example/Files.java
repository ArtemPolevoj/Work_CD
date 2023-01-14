package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Files {
    static File[] open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle("Выберете папку или файл(ы) для замены.");
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Exsel.xlsx", "xlsx"));
        int k = fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFiles();
    }

    public static String save() {

        JFileChooser saveFileChooser = new JFileChooser();

        saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFileChooser.setDialogTitle("Выберете и создайте файл для сохранения.");
        saveFileChooser.setMultiSelectionEnabled(true);
        saveFileChooser.setAcceptAllFileFilterUsed(false);
        saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Exsel.xlsx", "xlsx"));

        int i =saveFileChooser.showSaveDialog(null);

        if(i==1)return "";

        else return saveFileChooser.getSelectedFile().getPath();

    }
}
