package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mandatory {

    static String mandatory() {

        String outText;
        ArrayList<File> files = Read.reading();
        ArrayList<File> openFile = new ArrayList<>();
        for (File file:files) {
            if (file.getName().contains("КД") && file.getName().contains("ver.2")){
                openFile.add(file);
            }
        }


        if (openFile.isEmpty()){
            return  "Ошибка выбора.";
        }else {

            String fileName = Files.save();

            if (fileName.equals("")){
                return "Ошибка выбора." ;
            }else {

                boolean fileExists = false;

                if (new File(fileName).exists()) {

                    try (XSSFWorkbook saveBook = new XSSFWorkbook(new FileInputStream(fileName))) {

                        outText =  write(fileName, true, saveBook, openFile);

                    } catch (IOException e) {
                        return "Не удалось обработать файл.";
                    }

                } else {

                    try (XSSFWorkbook saveBook = new XSSFWorkbook()) {
                        outText = write(fileName + ".xlsx", false, saveBook, openFile);
                    } catch (IOException e) {
                        return "Не удалось обработать файл.";

                    }
                }
            }
        }
        return outText;
    }

    static String write(String fileName, boolean fileExists, XSSFWorkbook saveBook, ArrayList<File> openFile)  {

        String textCatch = "";
        String text= "";
        Pattern pattern = Pattern.compile("[^КД].*[^\\Sv]");
        String nameFile = new File(fileName).getName().replace(".xlsx","");


        file:

        for (int i = 0; i < openFile.size(); i++){
            File file = openFile.get(i);

            Matcher matcher = pattern.matcher(file.getName());

            try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file))) {

                XSSFSheet openSheet = workbook.getSheet("Ремонт");
                XSSFSheet newSheet ;
                String preSheetName ;
                String sheetName ;

                if (matcher.find()) {
                    preSheetName = matcher.group().trim();
                }else{
                    return  "Ошибка выбора имени файла.";
                }

                sheetName = JOptionPane.showInputDialog(null, "Введите название ОЗ (листа).", preSheetName);
                if (sheetName == null) return "Не выьрано название.";

                if (fileExists){

                    boolean IllegalArgumentException = false;
                    try {
                        newSheet = saveBook.createSheet(sheetName);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Такое ОЗ уже существует в файле \"" + nameFile + "\".");
                        continue file;
                    }

                    if (IllegalArgumentException) {
                        newSheet = saveBook.createSheet(sheetName);
                    }

                }else {
                    newSheet = saveBook.createSheet(sheetName);
                }

                if (i == openFile.size() - 1){
                    text = text + sheetName + ".";
                } else {
                    text = text + sheetName + ", ";
                }



                int rows = 7;
                int colPart = 0;
                int colQuantry = 1;
                int colName = 2;
                int colLabor = 0;

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

                XSSFCell partTemp = newSheet.getRow(6).createCell(4);
                XSSFCell countrTemp = newSheet.getRow(6).createCell(5);
                XSSFCell nameTemp = newSheet.getRow(6).createCell(6);

                XSSFCell nameList = row1.createCell(0);
                newSheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 2));
                nameList.setCellValue(sheetName);
                nameList.setCellStyle(styleFont);

                XSSFCell labor = row2.createCell(colLabor);
                labor.setCellValue("Трудозатраты");
                labor.setCellStyle(styleFont);

                XSSFCell laborQuantry = row2.createCell(colLabor + 1);
                laborQuantry.setCellValue(0);
                laborQuantry.setCellStyle(center);

                XSSFCell test = row3.createCell(colLabor);
                test.setCellValue("Тест");
                test.setCellStyle(styleFont);

                XSSFCell testQuantry = row3.createCell(colLabor + 1);
                testQuantry.setCellValue(0);
                testQuantry.setCellStyle(center);

                XSSFCell laborTotalName = row4.createCell(colLabor);
                laborTotalName.setCellValue("Всего (ч/ч)");
                laborTotalName.setCellStyle(styleFont);

                XSSFCell laborTotal = row4.createCell(colLabor + 1);
                // laborTotal.setCellValue(laborQuantry.getNumericCellValue() + testQuantry.getNumericCellValue());
                laborTotal.setCellFormula("B3+B4");
                laborTotal.setCellStyle(center);

                XSSFCell partNumer = row5.createCell(colPart);
                partNumer.setCellValue("Парт номер");
                partNumer.setCellStyle(styleFont);

                XSSFCell quantity = row5.createCell(colQuantry);
                quantity.setCellValue("Кол-во");
                quantity.setCellStyle(styleFont);

                XSSFCell partName = row5.createCell(colName);
                partName.setCellValue("Название");
                partName.setCellStyle(styleFont);

                for (Row row : openSheet) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.toString().contains("ОЗ") && cell.getColumnIndex() == 7) {
                            int colGet = cell.getColumnIndex();
                            int rowGet = cell.getRowIndex();

                            XSSFCell partOpen = openSheet.getRow(rowGet).getCell(colGet - 6);

                            if (partOpen.toString().contains("CA")) {

                                XSSFCell qtyOpen = openSheet.getRow(rowGet).getCell(colGet - 5);
                                XSSFCell nameOpen = openSheet.getRow(rowGet).getCell(colGet - 3);

                                boolean located = false;

                                for (int k = 7; k <= newSheet.getLastRowNum(); k++) {

                                    XSSFCell newpart = newSheet.getRow(k).getCell(0);
                                    XSSFCell newcountr = newSheet.getRow(k).getCell(1);


                                    if (newpart.toString().equals(partOpen.toString())) {
                                        newcountr.setCellValue(newcountr.getNumericCellValue() + qtyOpen.getNumericCellValue());
                                        located = true;
                                    }
                                }
                                if (!located) {
                                    XSSFRow newRow = newSheet.createRow(rows);

                                    XSSFCell newPart = newRow.createCell(colPart);
                                    newPart.setCellValue(partOpen.toString());
                                    newPart.setCellStyle(borders);

                                    XSSFCell newCounts = newRow.createCell(colQuantry);
                                    newCounts.setCellValue(qtyOpen.getNumericCellValue());
                                    newCounts.setCellStyle(center);

                                    XSSFCell newName = newRow.createCell(colName);
                                    newName.setCellValue(nameOpen.toString());
                                    newName.setCellStyle(borders);

                                    rows++;
                                }
                            }
                        }
                    }
                }
                boolean sorted = false;

                while (!sorted) {
                    sorted = true;
                    for (int l = 7; l < newSheet.getLastRowNum(); l++) {

                        XSSFCell part = newSheet.getRow(l).getCell(0);
                        XSSFCell countr = newSheet.getRow(l).getCell(1);
                        XSSFCell name = newSheet.getRow(l).getCell(2);

                        XSSFCell part1 = newSheet.getRow(l + 1).getCell(0);
                        XSSFCell countr1 = newSheet.getRow(l + 1).getCell(1);
                        XSSFCell name1 = newSheet.getRow(l + 1).getCell(2);

                        if (part1.toString().compareTo(part.toString()) < 0) {

                            partTemp.setCellValue(part.toString());
                            countrTemp.setCellValue(countr.getNumericCellValue());
                            nameTemp.setCellValue(name.toString());

                            part.setCellValue(part1.toString());
                            countr.setCellValue(countr1.getNumericCellValue());
                            name.setCellValue(name1.toString());

                            part1.setCellValue(partTemp.toString());
                            countr1.setCellValue(countrTemp.getNumericCellValue());
                            name1.setCellValue(nameTemp.toString());

                            sorted = false;

                        }
                    }
                }

                if(!(partTemp == null)){
                    newSheet.getRow(6).removeCell(partTemp);
                    newSheet.getRow(6).removeCell(countrTemp);
                    newSheet.getRow(6).removeCell(nameTemp);
                }


                try (FileOutputStream uotFile = new FileOutputStream(fileName)) {
                    saveBook.write(uotFile);
                } catch (IOException e) {

                    return  "Не удалось обработать файл - \"" + nameFile + "\".";

                }
            } catch (IOException e) {

                textCatch = "Не удалось обработать файл - " + file.getName().replace(".xlsx","");

            }

        }
        if (text.equals("")){
            return "В файле \"" + nameFile +"\" нет данных для получения ОЗ";
        } else if (fileExists){
            return "Файл \"" + nameFile +"\" - обновлён.\nДобавлено ОЗ: " + text + "\n" + textCatch ;
        } else {
            return "Файл \"" + nameFile +"\" - создан.\nДобавлено ОЗ: " + text + "\n" + textCatch ;
        }


    }
}
