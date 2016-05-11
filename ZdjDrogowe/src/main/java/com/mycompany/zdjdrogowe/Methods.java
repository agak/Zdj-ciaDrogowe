/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 *
 * @author agnieszka
 */
public class Methods {

    public void search() {
        try {
            File file = new File("gotowaBaza");
            listFilesForFolder(file);
        } catch (IOException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listFilesForFolder(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String extension = "";

                int i = fileEntry.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = fileEntry.getName().substring(i + 1);
                }
                if (extension.equals("jpg")) {
                    System.out.println(fileEntry.getName());

                    getPixelFromImage(fileEntry);

                }
            }
        }
    }

    private void getPixelFromImage(final File fileEntry) {
        BufferedImage imageToChoice = null;
        try {
            imageToChoice = ImageIO.read(fileEntry);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
        }
        
        double[] pixel = new double[3];
        for (int a = 0; a < imageToChoice.getWidth(); a++) {
            for (int b = 0; b < imageToChoice.getHeight(); b++) {
                imageToChoice.getRaster().getPixel(a, b, pixel);
                //System.out.println(a+" "+b+" "+pixel[0]+" "+pixel[1]+" "+pixel[2]+" ");
            }
        }
    }
}
