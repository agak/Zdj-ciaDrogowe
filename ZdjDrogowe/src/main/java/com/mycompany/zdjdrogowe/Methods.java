/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author agnieszka
 */
public class Methods {

    public void search() {
        File file = new File("../../gotowaBaza");
        listFilesForFolder(file);
    }

    public void listFilesForFolder(final File folder) {
        List<File> pictures = new ArrayList();
        for (final File fileEntry : folder.listFiles()) {
            
            String extension = "";

            int i = fileEntry.getName().lastIndexOf('.');
            if (i > 0) {
                extension = fileEntry.getName().substring(i + 1);
            }
            if (extension.equals("ppm")) {
                pictures.add(fileEntry);
            }
        }
        Comparator<File> c = (s1, s2) -> s1.getName().compareTo(s2.getName());
        pictures.sort(c);
        pictures.stream().forEach((pict) -> {
            System.out.println(pict.getName());
        });
    }

}
