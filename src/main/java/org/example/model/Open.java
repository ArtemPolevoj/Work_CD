package org.example.model;

import java.io.File;

public class Open implements FileChooser {
    private File[] selectedFiles;

    private void getFiles() {
        FileChooser.settingExcel();
        fileChooser.setDialogTitle("�������� ����� ��� ����(�) ��� ���������.");
        fileChooser.showOpenDialog(null);
        selectedFiles = fileChooser.getSelectedFiles();
    }

    public File[] getSelectedFiles() {
        getFiles();
        return selectedFiles;
    }
}
