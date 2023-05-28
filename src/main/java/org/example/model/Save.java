package org.example.model;

public class Save extends FileChooser {
    private String selectedFilePath;

    private void filePath() {
        super.settingExcel();
        super.setDialogTitle("Выберете и создайте файл для сохранения.");
        int i = super.showSaveDialog(null);
        if (i == 1) selectedFilePath = "";
        else selectedFilePath =  super.getSelectedFile().getPath();
    }

    public String getSelectedFilePath() {
        filePath();
        return selectedFilePath;
    }
}
