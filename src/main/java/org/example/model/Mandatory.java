package org.example.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mandatory {
    private String filePath;
    private String result;
    private ArrayList<File> openFile = new ArrayList<>();
    private final ArrayList<PartNumber> partList = new ArrayList<>();

    public String getResult() {
        mandatory();
        return result;
    }

    private void setOpenFile() {
        this.openFile = new Files().getFiles("ver.2");
    }

    private void setFilePath() {
        this.filePath = new Save().getSelectedFilePath();
    }

    public void mandatory() {
        setOpenFile();
        if (openFile.isEmpty()) {
            result = "В выбранной папке отсутствуют файлы для получения ОЗ.\n";
        } else {
            setFilePath();
            if (filePath.isEmpty()) {
                result = "Не выбран файл для сохранения.\n";
            } else {
                if (new File(filePath).exists()) {
                    try (XSSFWorkbook saveBook = new XSSFWorkbook(new FileInputStream(filePath))) {
                        result = write(filePath, true, saveBook);
                    } catch (IOException e) {
                        result = "Не удалось обработать файл.\n";
                    }
                } else {
                    try (XSSFWorkbook saveBook = new XSSFWorkbook()) {
                        result = write(filePath + ".xlsx", false, saveBook);
                    } catch (IOException e) {
                        result = "Не удалось создать новый файл.\n";
                    }
                }
            }
        }
    }

    private String write(String fileName,
                         boolean fileExists,
                         XSSFWorkbook saveBook) {

        StringBuilder textCatch = new StringBuilder();
        StringBuilder text = new StringBuilder();
        Pattern pattern = Pattern.compile("[^КД].*[^\\Sv]");
        String nameFile = new File(fileName).getName().
                replace(".xlsx", "");

        XSSFSheet newSheet;
        String preSheetName;
        String sheetName;

        for (int i = 0; i < openFile.size(); i++) {
            File file = openFile.get(i);

            try {
                partList.clear();
                setPartList(file);
                partList.sort(null);
            } catch (Exception e) {
                textCatch.append("Не удалось обработать файл - ")
                        .append(file.getName().replace(".xlsx", "\n"));
            }
            Matcher matcher = pattern.matcher(file.getName());

            if (matcher.find()) {
                preSheetName = matcher.group().trim();
            } else {
                return "Ошибка в получении предполагаемого имени ОЗ (листа).";
            }

            sheetName = JOptionPane.showInputDialog(null,
                    "Введите название ОЗ (листа).",
                    preSheetName);
            if (sheetName == null)
                return "Не выбрано имя ОЗ (листа) для сохранения.";

            if (fileExists) {
                try {
                    newSheet = saveBook.createSheet(sheetName);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null,
                            "Такое ОЗ уже существует в файле "" +
                                    nameFile + "\".");
                    continue;
                }
            } else {
                newSheet = saveBook.createSheet(sheetName);
            }

            if (i == openFile.size() - 1) {
                text.append(sheetName).append(".");
            } else {
                text.append(sheetName).append(",\n\t");
            }

            //region Setting style
            CellStyle borders = saveBook.createCellStyle();
            borders.setBorderLeft(BorderStyle.THIN);
            borders.setBorderRight(BorderStyle.THIN);
            borders.setBorderBottom(BorderStyle.THIN);
            borders.setBorderTop(BorderStyle.THIN);

            Font font = saveBook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            CellStyle styleFont = saveBook.createCellStyle();
            styleFont.setFont(font);
            styleFont.setBorderLeft(BorderStyle.THIN);
            styleFont.setBorderRight(BorderStyle.THIN);
            styleFont.setBorderBottom(BorderStyle.THIN);
            styleFont.setBorderTop(BorderStyle.THIN);

            CellStyle center = saveBook.createCellStyle();
            center.setBorderLeft(BorderStyle.THIN);
            center.setBorderRight(BorderStyle.THIN);
            center.setBorderBottom(BorderStyle.THIN);
            center.setBorderTop(BorderStyle.THIN);
            center.setAlignment(HorizontalAlignment.CENTER);

            XSSFRow row1 = newSheet.createRow(0);
            XSSFRow row2 = newSheet.createRow(2);
            XSSFRow row3 = newSheet.createRow(3);
            XSSFRow row4 = newSheet.createRow(4);
            XSSFRow row5 = newSheet.createRow(6);
            newSheet.setColumnWidth(0, 4000);
            newSheet.setColumnWidth(1, 2000);
            newSheet.setColumnWidth(2, 7000);
            newSheet.setColumnWidth(3, 5000);
            //endregion

            //region Setting head
            int rows = 7;
            int colPart = 0;
            int colQuantity = 1;
            int colName = 2;
            int colType = 3;
            int colLabor = 0;
            XSSFCell nameList = row1.createCell(0);
            newSheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 2));
            nameList.setCellValue(sheetName);
            nameList.setCellStyle(styleFont);

            XSSFCell labor = row2.createCell(colLabor);
            labor.setCellValue("Трудозатраты");
            labor.setCellStyle(styleFont);

            XSSFCell laborQuantity = row2.createCell(colLabor + 1);
            laborQuantity.setCellValue(0);
            laborQuantity.setCellStyle(center);

            XSSFCell test = row3.createCell(colLabor);
            test.setCellValue("Тест");
            test.setCellStyle(styleFont);

            XSSFCell testQuantity = row3.createCell(colLabor + 1);
            testQuantity.setCellValue(0);
            testQuantity.setCellStyle(center);

            XSSFCell laborTotalName = row4.createCell(colLabor);
            laborTotalName.setCellValue("Всего (ч/ч)");
            laborTotalName.setCellStyle(styleFont);

            XSSFCell laborTotal = row4.createCell(colLabor + 1);
            laborTotal.setCellFormula("B3+B4");
            laborTotal.setCellStyle(center);

            XSSFCell partNumber = row5.createCell(colPart);
            partNumber.setCellValue("Парт номер");
            partNumber.setCellStyle(styleFont);

            XSSFCell quantity = row5.createCell(colQuantity);
            quantity.setCellValue("Кол-во");
            quantity.setCellStyle(styleFont);

            XSSFCell partName = row5.createCell(colName);
            partName.setCellValue("Название");
            partName.setCellStyle(styleFont);

            XSSFCell partType = row5.createCell(colType);
            partType.setCellValue("Тип");
            partType.setCellStyle(styleFont);
            //endregion

            for (PartNumber p : partList) {
                XSSFRow newRow = newSheet.createRow(rows);

                XSSFCell newPart = newRow.createCell(colPart);
                newPart.setCellValue(p.getNumber());
                newPart.setCellStyle(borders);

                XSSFCell newCounts = newRow.createCell(colQuantity);
                newCounts.setCellValue(p.getCount());
                newCounts.setCellStyle(center);

                XSSFCell newName = newRow.createCell(colName);
                newName.setCellValue(p.getName());
                newName.setCellStyle(borders);

                XSSFCell newType = newRow.createCell(colType);
                newType.setCellValue(p.getType());
                newType.setCellStyle(borders);

                rows++;
            }

            try (FileOutputStream uotFile = new FileOutputStream(fileName)) {
                saveBook.write(uotFile);
            } catch (IOException e) {
                return "Не удалось записать файл - \"" + nameFile + "\".";
            }

        }
        if (text.toString().isEmpty()) {
            return "В файле \"" + nameFile + "\" уже есть выбранные ОЗ.";
        } else if (fileExists) {
            return "Файл \"" + nameFile + "\" - обновлён.\nДобавлено ОЗ: "
                    + text + "\n" + textCatch;
        } else {
            return "Файл \"" + nameFile + "\" - создан.\nДобавлено ОЗ: "
                    + text + "\n" + textCatch;
        }
    }

    private void setPartList(File file) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream(file))) {
            XSSFSheet openSheet = workbook.getSheet("Ðåìîíò");
            for (Row row : openSheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    boolean consist = false;
                    Cell cell = cellIterator.next();
                    if ((cell.toString().contains("Ðàñõîä") || cell.toString().contains("ÎÇ")) && cell.getColumnIndex() == 7) {
                        int colGet = cell.getColumnIndex();
                        int rowGet = cell.getRowIndex();
                        XSSFCell pNumber = openSheet.getRow(rowGet).getCell(colGet - 6);
                        XSSFCell pCount = openSheet.getRow(rowGet).getCell(colGet - 5);
                        XSSFCell pName = openSheet.getRow(rowGet).getCell(colGet - 3);
                        XSSFCell pType = openSheet.getRow(rowGet).getCell(colGet);
                        for (PartNumber p : partList) {
                            if (p.getNumber().equals(pNumber.toString())) {
                                p.setCount(p.getCount() + pCount.getNumericCellValue());
                                consist = true;
                                break;
                            }
                        }
                        if (!pNumber.toString().isEmpty() && !consist) {
                            PartNumber temp = new PartNumber(pNumber.toString(), pCount.getNumericCellValue(),
                                    pName.toString(), pType.toString());
                            partList.add(temp);
                        }
                    }
                }
            }
        }
    }


    public ArrayList<PartNumber> getPartList(File file) {
        return partList;
    }
}
