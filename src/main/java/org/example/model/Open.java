package org.example.model;

import java.io.File;

public class Open extends FileChooser {
    private File[] selectedFiles;

    private void getFiles() {
        super.settingExcel();
        super.setDialogTitle("�������� ����� ��� ����(�) ��� ���������.");
        super.showOpenDialog(null);
        selectedFiles = super.getSelectedFiles();
    }

    public File[] getSelectedFiles() {
        getFiles();
        return selectedFiles;
    }
}
