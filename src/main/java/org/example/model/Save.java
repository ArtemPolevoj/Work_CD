package org.example.model;

public class Save extends FileChooser {
    private String selectedFilePath;

    private void filePath() {
        super.settingExcel();
        fileChooser.setDialogTitle("�������� � �������� ���� ��� ����������.");
        int i = fileChooser.showSaveDialog(null);
        if (i == 1) selectedFilePath = "";
        else selectedFilePath =  fileChooser.getSelectedFile().getPath();
    }

    public String getSelectedFilePath() {
        filePath();
        return selectedFilePath;
    }
}
