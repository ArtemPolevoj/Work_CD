package org.example;

import java.io.File;
import java.util.ArrayList;



public class Read {
    static ArrayList<File> reading() {

        ArrayList<File> files = new ArrayList<>();

        File[] openDir = Files.open();

        for (File fileOpen : openDir) {

            if (fileOpen.isFile()) {
                openFile(fileOpen, files);
            } else {
                openDirectory(fileOpen, files);
            }
        }

        return files;
    }

    static void openFile(File fileOpen, ArrayList<File> files) {
        if (fileOpen.getName().contains("xlsx")) {
            files.add(fileOpen);
        }
    }

    static void openDirectory(File fileOpen, ArrayList<File> files) {

        File[] filesName = fileOpen.listFiles();

        assert filesName != null;
        for (File file : filesName)
            if (file.isFile()) {
                openFile(file, files);
            } else {
                openDirectory(file, files);
            }

    }
}
