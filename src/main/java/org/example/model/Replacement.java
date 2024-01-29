package org.example.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Replacement {
    private String worldReplace;
    private String worldReplacement;

    private final StringBuilder result = new StringBuilder();

    private ArrayList<File> openFile = new ArrayList<>();


    public String getResult(String worldReplace, String worldReplacement) {
        setWorldReplace(worldReplace);
        setWorldReplacement(worldReplacement);
        setResult();
        return result.toString();
    }


    private void setOpenFile() {
        this.openFile = new Files().getFiles("");
    }

    private void setWorldReplace(String worldReplace) {

        this.worldReplace = worldReplace;
    }

    private void setWorldReplacement(String worldReplacement) {
        this.worldReplacement = worldReplacement;
    }

    private void replace() {

        for (File file : openFile) {
            int amount = 0;
            String nameFile = file.getName();
            try (XSSFWorkbook workbook = new XSSFWorkbook(
                    new FileInputStream(file))) {
                for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                    Sheet sheet = workbook.getSheetAt(s);

                    for (Row row : sheet) {

                        Iterator<Cell> cellIterator = row.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            if (cell.toString().contains(worldReplace)) {
                                cell.setCellValue(cell.toString()
                                        .replaceAll(worldReplace, worldReplacement));
                                amount++;
                            }
                        }
                    }
                }
                try (FileOutputStream writeFile = new FileOutputStream(file)) {
                    workbook.write(writeFile);
                }
                workbook.close();
                result.append(switch (amount) {
                    case (0) -> "В файле \"" + nameFile
                            + "\" отсутствуют совпадения.\n";
                    case (1), (21), (31), (41) ->
                            "В файле \"" + nameFile + "\" выполнена "
                                    + amount + " замена.\n";
                    case (2), (3), (4), (22), (23), (24) ->
                            "В файле \"" + nameFile + "\" выполнено "
                                    + amount + " замены.\n";
                    default -> "В файле \"" + nameFile + "\" выполнено "
                            + amount + " замен.\n";
                });
            } catch (Exception e) {
                result.append("В файле \"")
                        .append(nameFile)
                        .append("\" произошла ошибка.").append(" Проверьте файл.\n");
            }
        }

    }

    private void setResult() {
        result.delete(0, result.length());
        if (worldReplace.isEmpty()) {
            worldReplace = JOptionPane.showInputDialog(null,
                    "Введите слово для замены.",
                    "Нельзя выполнить замену! ",
                    JOptionPane.WARNING_MESSAGE);
            if (worldReplace == null || worldReplace.isEmpty()) {
                result.append("Не введено слово для замены.\n");

            } else {
                setOpenFile();
                if (openFile.isEmpty()) {
                    result.append("Не выбран файл.\n");
                } else {
                    setWorldReplace(worldReplace);
                    replace();
                }
            }
        } else {
            setOpenFile();
            if (openFile.isEmpty()) {
                result.append("Не выбран файл.\n");
            } else {
                replace();
            }
        }
    }
}
