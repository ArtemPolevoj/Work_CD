package org.example.model;

import java.io.File;

public class Open extends FileChooser {
    private File[] selectedFiles;

    private void getFiles() {
        super.settingExcel();
        fileChooser.setDialogTitle("�������� ����� ��� ����(�) ��� ���������.");
        fileChooser.showOpenDialog(null);
        selectedFiles = fileChooser.getSelectedFiles();
    }

    public File[] getSelectedFiles() {
        getFiles();
        return selectedFiles;
    }
}
