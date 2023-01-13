package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class Replacement {

    static String replace(File file, String worldReplace, String replacement) {

        String txt;
        String outText = "";
        String nameFile = file.getName();
        int amount = 0;

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file))) {

            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);

                for (Row row : sheet) {

                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();

                        if (cell.toString().contains(worldReplace)) {
                            cell.setCellValue(cell.toString().replaceAll(worldReplace, replacement));
                            amount++;
                        }
                    }
                }
            }

            try (FileOutputStream writeFile = new FileOutputStream(file)) {
                workbook.write(writeFile);
            }
            workbook.close();

            txt = switch (amount) {
                case (0) -> "В файле \"" + nameFile + "\" замен не выполнено.\n";
                case (2), (3), (4), (22), (23), (24) ->
                        "В файле  \"" + nameFile + "\" выполнено " + amount + " замены.\n";
                default -> "В файле  \"" + nameFile + "\" выполнено " + amount + " замен.\n";
            };

            outText += txt;

        } catch (Exception e) {
            outText = "В файле \"" + nameFile + "\" произошла ошибка. Проверьте файл.\n";
        }
        return outText;
    }

}
