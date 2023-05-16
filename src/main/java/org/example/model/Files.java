package org.example.model;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Files {
    private File[] openDir;
    private ArrayList<File> files = new ArrayList<>();
    private String check;

    public ArrayList<File> getFiles(String check) {
        this.check = check;
        getList();
        return files;
    }

    public void setOpenDir() {
        this.openDir = new Open().getSelectedFiles();
    }

    private void getList() {
        setOpenDir();

        Vizit vizit = new Vizit();

        for (File fileOpen : openDir) {
            if (fileOpen.isFile() & fileOpen.getName().contains(check)) {
                files.add(fileOpen);

            } else {
                Path path = Path.of(fileOpen.getPath());
                try {
                    java.nio.file.Files.walkFileTree(path, vizit);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Не удалось обработать " + fileOpen,
                            "Ошибка обработки", JOptionPane.ERROR_MESSAGE);
                }
                files = Vizit.get(check);
            }
        }
    }

    private static class Vizit extends SimpleFileVisitor<Path> {
        static ArrayList<File> temp = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            temp.add(file.toFile());
            return CONTINUE;
        }

        private static ArrayList<File> get(String check) {
            ArrayList<File> files = new ArrayList<>();
            for (File file : temp) {
                if (file.getName().contains(check)) {
                    files.add(file);
                }
            }
            return files;
        }
    }
}
