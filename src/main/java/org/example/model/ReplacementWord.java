package org.example.model;

import org.apache.poi.xwpf.usermodel.*;

import org.openxmlformats.schemas.drawingml.x2006.chart.CTHeaderFooter;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReplacementWord {
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
        this.openFile = new Files().getFiles(".docx", false);
    }

    private void setWorldReplace(String worldReplace) {
        this.worldReplace = worldReplace;
    }

    private void setWorldReplacement(String worldReplacement) {
        this.worldReplacement = worldReplacement;
    }

    private void replace() {
        result.setLength(0);
        for (File file : openFile) {
            int amount = 0;
            String nameFile = file.getName();
            try (XWPFDocument workbook = new XWPFDocument(new FileInputStream(file))) {
//                for (XWPFParagraph paragraph : workbook.getParagraphs()) {
//                    List<XWPFRun> runs = paragraph.getRuns();
//                    if (runs != null && !runs.isEmpty()) {
//                        for (XWPFRun run : runs) {
//                            String text = run.text();
//                            if (text.contains(worldReplace)) {
//                                amount++;
//                                text = text.replaceAll(worldReplace, worldReplacement);
//                                run.setText(text, 0); // Устанавливаем новый текст
//                            }
//
//                        }
//                    }
//                }
                List<XWPFHeader> footers = workbook.getHeaderList();
                for(XWPFHeader footer : footers) {
                    if(footer != null) {
                        for(XWPFParagraph paragraph : footer.getParagraphs()) {
                            List<XWPFRun> runs = paragraph.getRuns();
                            for(XWPFRun run : runs) {
                                String text = run.text();
                                if(text.contains(worldReplace)) {
                                    amount++;
                                    text = text.replaceAll(worldReplace, worldReplacement);
                                run.setText(text, 0);
                                }
                            }
                        }
                    }
                }

                try (FileOutputStream writeFile = new FileOutputStream(file)) {
                    workbook.write(writeFile);
                }
                result.append(switch (amount) {
                    case (0) -> "В файле \"" + nameFile
                            + "\" отсутствуют совпадения.\n";
                    case (1), (21), (31), (41) -> "В файле \"" + nameFile + "\" выполнена "
                            + amount + " замена.\n";
                    case (2), (3), (4), (22), (23), (24) -> "В файле \"" + nameFile + "\" выполнено "
                            + amount + " замены.\n";
                    default -> "В файле \"" + nameFile + "\" выполнено "
                            + amount + " замен.\n";
                });
            } catch (Exception e) {
                result.append("В файле \"")
                        .append(nameFile)
                        .append("\"  произошла ошибка: ")
                        .append(e.getMessage())
                        .append("\n")
                        .append(" Проверьте файл.\n");

            }
        }

    }

    private void setResult() {
        result.setLength(0);
        openFile.clear();
        setOpenFile();
        if (openFile.isEmpty()) {
            result.append("Не выбран файл.\n");
        } else {
            replace();
        }
    }
}
