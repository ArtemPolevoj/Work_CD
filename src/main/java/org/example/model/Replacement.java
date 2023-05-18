package org.example.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Replacement {
    private String worldReplace;
    private  String worldReplacement;

    private final StringBuilder RESULT = new StringBuilder();

    private  ArrayList<File> openFile = new ArrayList<>();


    public String getResult(String worldReplace, String worldReplacement) {
        setWorldReplace(worldReplace);
        setWorldReplacement(worldReplacement);
        replace();
        return RESULT.toString();
    }

    private void setOpenFile() {
        this.openFile = new Files().getFiles("xlsx");
    }

    private void setWorldReplace(String worldReplace) {
        this.worldReplace = worldReplace;
    }

    private void setWorldReplacement(String worldReplacement) {
        this.worldReplacement = worldReplacement;
    }

    private void replace() {

        setOpenFile();

        if (openFile.isEmpty()) {
                RESULT.append("Не выбран файл.");
        } else {
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
                    RESULT.append(switch (amount) {
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
                    }) ;
                } catch (Exception e) {
                    RESULT.append("В файле \"")
                            .append(nameFile)
                            .append("\" произошла ошибка.").append(" Проверьте файл.\n");
                }
            }
        }
    }
}
