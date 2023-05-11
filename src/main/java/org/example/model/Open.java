package org.example.model;

import java.io.File;

public class Open implements FileChooser {
    private File[] selectedFiles;

    private void getFiles() {
        FileChooser.settingExcel();
        fileChooser.setDialogTitle("Выберете папку или файл(ы) для обработки.");
        fileChooser.showOpenDialog(null);
        selectedFiles = fileChooser.getSelectedFiles();
    }

    public File[] getSelectedFiles() {
        getFiles();
        return selectedFiles;
    }
}
