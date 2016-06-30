/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author agnieszka
 */
public class Methods {

    FileOutputStream obtainedResulFile = null;

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

    public void loadImagesAndSaveToFile(List<File> pictures, double[][] color) {
        try {
            obtainedResulFile = new FileOutputStream("znalezioneZdjDrogowe.txt");
        } catch (IOException ex) {
            System.out.println("Błąd utworzenia pliku: " + ex);
        }

        pictures.stream().forEach((pict) -> {
         //   System.out.println(pict.getName());
            loadImage(pict, color);
        });

        try {
            obtainedResulFile.close();
        } catch (IOException e) {
            System.out.println("Błąd zamykania pliku: " + e);
        }
    }

    private void loadImage(File fileEntry, double[][] color) {
        BufferedImage imageToChoice = null;
        try {
            imageToChoice = ImageIO.read(fileEntry);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
        }
        findTrafficSign(imageToChoice,color,fileEntry);
    }
    
    
    private void findTrafficSign(BufferedImage imageToChoice, double[][] color,File fileEntry){
        int signArea=0;
        int[] rectangleCoordinate = new int[4];
        int x=0, y=0;
        int[] cordinatePixel = {1,1};
                
        while((signArea < 10) && !(cordinatePixel[0] == 0) && !(cordinatePixel[1] == 0)){
            cordinatePixel = findPixelWithColor(imageToChoice, color,x,y);
            
            
            //segmentacja
            List<Integer> XCoordinatesOfArea = new ArrayList();
            List<Integer> YCoordinatesOfArea = new ArrayList(); 
            designateArea(imageToChoice, cordinatePixel, XCoordinatesOfArea, YCoordinatesOfArea, color);
            

            //oznaczenie wyznaczonej przestrzeni (znaku drogowego) w prostokąt
            rectangleCoordinate = markAreaInARectangle(XCoordinatesOfArea, YCoordinatesOfArea);

            //obliczanie pola przestrzeni
            signArea = computeSignArea(rectangleCoordinate);
            //System.out.println("Area: "+signArea);
            if(!XCoordinatesOfArea.isEmpty() && !YCoordinatesOfArea.isEmpty()){
                x=(int) Collections.max(XCoordinatesOfArea)+3;
                y=(int) Collections.max(YCoordinatesOfArea)+3;
                //System.out.println("x: "+x+" y: "+y);
            }
            //System.out.println("cord: "+cordinatePixel[0]+" cord: "+cordinatePixel[1]);
        }
   
        if(signArea > 10){
            saveRowToFile(fileEntry, rectangleCoordinate);
        }
        
    }
    
    

    private int[] findPixelWithColor(BufferedImage imageToChoice, double[][] color,int x,int y) {
        double[] pixel = new double[3];
        int[] cordinate = new int[2];
        outerloop:
        for (int a = x; a < imageToChoice.getWidth(); a++) {
            for (int b = y; b < imageToChoice.getHeight(); b++) {
                imageToChoice.getRaster().getPixel(a, b, pixel);
                //System.out.println("a "+a+" b: "+b);
                if (pixel[0] > color[0][0] && pixel[0] < color[0][1] && pixel[1] > color[1][0] && pixel[1] < color[1][1] & pixel[2] > color[2][0] && pixel[2] < color[2][1]) {
                    cordinate[0] = a;
                    cordinate[1] = b;
                    break outerloop;
                }
                //System.out.println(a+" "+b+" "+pixel[0]+" "+pixel[1]+" "+pixel[2]+" ");
            }
        }
        //System.out.println("PixelWithColor: " + cordinate[0] + " " + cordinate[1]);
        return cordinate;
    }

    private void designateArea(BufferedImage imageToChoice, int[] cordinatePixel, List<Integer> XCoordinatesOfArea, List<Integer> YCoordinatesOfArea, double[][] color) {

        double[] colorRGB = new double[3];

        for (int x = cordinatePixel[0] - 1; x <= cordinatePixel[0] + 1; x++) {
            for (int y = cordinatePixel[1] - 1; y <= cordinatePixel[1] + 1; y++) {
                if (x > 0 && x < imageToChoice.getWidth() && y > 0 && y < imageToChoice.getHeight()) {
                    imageToChoice.getRaster().getPixel(x, y, colorRGB);
                    if (colorRGB[0] > color[0][0] && colorRGB[0] < color[0][1] && colorRGB[1] > color[1][0] && colorRGB[1] < color[1][1] & colorRGB[2] > color[2][0] && colorRGB[2] < color[2][1]) {
                        if ((!XCoordinatesOfArea.contains(x)) || (!YCoordinatesOfArea.contains(y))) {
                            if (!XCoordinatesOfArea.contains(x)) {
                                XCoordinatesOfArea.add(x);
                            }
                            if (!YCoordinatesOfArea.contains(y)) {
                                YCoordinatesOfArea.add(y);
                            }

                            cordinatePixel[0] = x;
                            cordinatePixel[1] = y;
                            designateArea(imageToChoice, cordinatePixel, XCoordinatesOfArea, YCoordinatesOfArea, color);
                        }
                    }
                }
            }
        }
    }

    private int[] markAreaInARectangle(List XCoordinatesOfArea, List YCoordinatesOfArea) {
        int[] rectangleCoordinate = {-1, -1, -1, -1};  //-1 jeśli nie znaleziono znaku
        if (XCoordinatesOfArea.size() > 0) {
            rectangleCoordinate[0] = (int) Collections.min(XCoordinatesOfArea);
            rectangleCoordinate[1] = (int) Collections.max(XCoordinatesOfArea);
            rectangleCoordinate[2] = (int) Collections.min(YCoordinatesOfArea);
            rectangleCoordinate[3] = (int) Collections.max(YCoordinatesOfArea);

            /*XCoordinatesOfArea.stream().forEach((Xc) -> {
            System.out.println("Xcord: "+Xc);
        });
        YCoordinatesOfArea.stoperacji naream().forEach((Yc) -> {
            System.out.println("Ycord: "+Yc);
        });*/
            //System.out.println("Xmin: " + rectangleCoordinate[0] + "; Xmax: " + rectangleCoordinate[1] + "; Ymin: " + rectangleCoordinate[2] + "; Ymax: " + rectangleCoordinate[3]);
        }
        return rectangleCoordinate;
    }

    private void saveRowToFile(File imageFile, int[] rectangleCoordinate) {
        if (-1!=rectangleCoordinate[0]) {
            String output = imageFile.getName() + ";" + rectangleCoordinate[0] + ";" + rectangleCoordinate[2] + ";" + rectangleCoordinate[1] + ";" + rectangleCoordinate[3] + "\n";

            try {
                for (int i = 0; i < output.length(); i++) {
                    obtainedResulFile.write((int) output.charAt(i));
                }
            } catch (IOException ex) {
                System.out.println("Błąd zapisu do pliku: " + ex);
            }
        }

    }
    
    private int computeSignArea(int[] rectangleCoordinate){
        int signArea = (rectangleCoordinate[1]-rectangleCoordinate[0])^2*(rectangleCoordinate[3]-rectangleCoordinate[2])^2;
        return signArea;
    }
}
