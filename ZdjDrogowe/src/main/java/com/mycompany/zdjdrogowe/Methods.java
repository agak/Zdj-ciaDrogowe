/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.io.File;

/**
 *
 * @author agnieszka
 */
public class Methods {

    public void search() {
        File file = new File("gotowaBaza");
        listFilesForFolder(file);
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String extension = "";

                int i = fileEntry.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = fileEntry.getName().substring(i + 1);
                }
                if (extension.equals("ppm")) {
                    System.out.println(fileEntry.getName());
                }
            }
        }
    }

}
