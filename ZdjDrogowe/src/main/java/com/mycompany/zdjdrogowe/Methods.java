/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author agnieszka
 */
public class Methods {
    
    private double[][] color = {{244.0, 255.0}, {244.0, 255.0}, {244.0, 240.0}}; //blue
    

    
    public List<File> makePicturesList(File folder) {
        List<File> pictures = new ArrayList();
        for (final File fileEntry : folder.listFiles()) {
            
            String extension = "";
            
            int i = fileEntry.getName().lastIndexOf('.');
            if (i > 0) {
                extension = fileEntry.getName().substring(i + 1);
            }
            if (extension.equals("jpg")) {
                pictures.add(fileEntry);
            }
        }
        Comparator<File> c = (s1, s2) -> s1.getName().compareTo(s2.getName());
        pictures.sort(c);
        return pictures;  
    }
    
    public void loadImages(List<File> pictures){
        pictures.stream().forEach((pict) -> {
            System.out.println(pict.getName());
            loadImage(pict);
        });
    }
    
    private void loadImage(File fileEntry) {
        BufferedImage imageToChoice = null;
        try {
            imageToChoice = ImageIO.read(fileEntry);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
        }
        
        int[] cordinatePixel=findPixelWithColor(imageToChoice, color);
        //segmentacja
    }
    
    private int[] findPixelWithColor(BufferedImage imageToChoice, double[][] color) {
        double[] pixel = new double[3];
        int [] cordinate= new int [2];
        outerloop:
        for (int a = 0; a < imageToChoice.getWidth(); a++) {
            for (int b = 0; b < imageToChoice.getHeight(); b++) {
                imageToChoice.getRaster().getPixel(a, b, pixel);
                
                if (pixel[0] > color[0][0] && pixel[0] < color[0][1] && pixel[1] > color[1][0] && pixel[1] < color[1][1] & pixel[2] > color[2][0] && pixel[2] < color[2][1]) {
                   cordinate[0]=a;
                    cordinate[1]=b;
                    break outerloop;
                }
                //System.out.println(a+" "+b+" "+pixel[0]+" "+pixel[1]+" "+pixel[2]+" ");
            }
        }
        System.out.println(cordinate[0]+" "+cordinate[1]);
        return cordinate;
    }
}
