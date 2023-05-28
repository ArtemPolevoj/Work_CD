package org.example.model;

import java.io.File;

public class Open extends FileChooser {
    private File[] selectedFiles;

    private void getFiles() {
        super.settingExcel();
        super.setDialogTitle("Выберете папку или файл(ы) для обработки.");
        super.showOpenDialog(null);
        selectedFiles = super.getSelectedFiles();
    }

    public File[] getSelectedFiles() {
        getFiles();
        return selectedFiles;
    }
}
