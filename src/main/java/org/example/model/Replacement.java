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
                    case (0) -> "� ����� \"" + nameFile
                            + "\" ����������� ����������.\n";
                    case (1), (21), (31), (41) ->
                            "� ����� \"" + nameFile + "\" ��������� "
                                    + amount + " ������.\n";
                    case (2), (3), (4), (22), (23), (24) ->
                            "� ����� \"" + nameFile + "\" ��������� "
                                    + amount + " ������.\n";
                    default -> "� ����� \"" + nameFile + "\" ��������� "
                            + amount + " �����.\n";
                });
            } catch (Exception e) {
                result.append("� ����� \"")
                        .append(nameFile)
                        .append("\" ��������� ������.").append(" ��������� ����.\n");
            }
        }

    }

    private void setResult() {
        result.delete(0, result.length());
        if (worldReplace.isEmpty()) {
            worldReplace = JOptionPane.showInputDialog(null,
                    "������� ����� ��� ������.",
                    "������ ��������� ������! ",
                    JOptionPane.WARNING_MESSAGE);
            if (worldReplace == null || worldReplace.isEmpty()) {
                result.append("�� ������� ����� ��� ������.\n");

            } else {
                setOpenFile();
                if (openFile.isEmpty()) {
                    result.append("�� ������ ����.\n");
                } else {
                    setWorldReplace(worldReplace);
                    replace();
                }
            }
        } else {
            setOpenFile();
            if (openFile.isEmpty()) {
                result.append("�� ������ ����.\n");
            } else {
                replace();
            }
        }
    }
}
