/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.awt.image.WritableRasterNative;

/**
 *
 * @author agnieszka
 */
public class Methods {

    FileOutputStream obtainedResulFile = null;
    List<String> coordinatesToFile =new ArrayList<>();

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
    
    public void saveListToFile(){
        
        try {
            obtainedResulFile = new FileOutputStream("znalezioneZdjDrogowe.txt");
        
        //sortowanie listy po nazwach
        java.util.Collections.sort(this.coordinatesToFile);
        
        //tutaj zapisywanie listy kordynatów do pliku
                for(String str: this.coordinatesToFile) {
                    for (int i=0; i<str.length(); i++)
                    {
                        obtainedResulFile.write((int) str.charAt(i));
                    }
                }
        
        
        
        
        
            obtainedResulFile.close();
        } catch (IOException e) {
            System.out.println("Błąd zamykania pliku: " + e);
        }
        
    }


    public void loadImagesAndSaveToList(List<File> pictures, double[][] color) {

        pictures.stream().forEach((pict) -> {
            //System.out.println(pict.getName());
            loadImage(pict, color);
        });

        
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
           
        while(!(cordinatePixel[0] == 0) && !(cordinatePixel[1] == 0)){
            while((signArea < 25) && !(cordinatePixel[0] == 0) && !(cordinatePixel[1] == 0)){
                

                
                //zwraca tablice z dwoma zerami jeśli nie znalazło piksela na obrazie
                cordinatePixel = findPixelWithColor(imageToChoice, color,x,y);

                //segmentacja
                List<Integer> XCoordinatesOfArea = new ArrayList();
                List<Integer> YCoordinatesOfArea = new ArrayList(); 
                
                //System.out.println("cor before: x = "+cordinatePixel[0]+" y = "+cordinatePixel[1]);
                imageToChoice=designateArea(imageToChoice, cordinatePixel, XCoordinatesOfArea, YCoordinatesOfArea, color);
                //System.out.println("cor after: x = "+cordinatePixel[0]+" y = "+cordinatePixel[1]);

                //oznaczenie wyznaczonej przestrzeni (znaku drogowego) w prostokąt
                rectangleCoordinate = markAreaInARectangle(XCoordinatesOfArea, YCoordinatesOfArea);

                //obliczanie pola przestrzeni
                signArea = computeSignArea(rectangleCoordinate);
                //System.out.println("Area: "+signArea);
                
                
                if(!XCoordinatesOfArea.isEmpty() && !YCoordinatesOfArea.isEmpty()){
                    x=(int) Collections.max(XCoordinatesOfArea)+5;
                    y=(int) Collections.min(YCoordinatesOfArea)+5;
                }

            }

            if(signArea > 25){
                saveRowToList(fileEntry, rectangleCoordinate);
            }
            signArea=0;
            
            /*try {
                ImageIO.write(imageToChoice, "png", new File("imgTest/"+fileEntry.getName()));
            } catch (IOException ex) {
                Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
            }*/
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

    private BufferedImage designateArea(BufferedImage imageToChoice, int[] cordinatePixel, List<Integer> XCoordinatesOfArea, List<Integer> YCoordinatesOfArea, double[][] color) {

        double[] colorRGB = new double[3];
        int[] colorRGBvisited = new int[]{0,0,0};

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
                            
                            imageToChoice.getRaster().setPixel(x, y, colorRGBvisited);
                            
                            imageToChoice=designateArea(imageToChoice, cordinatePixel, XCoordinatesOfArea, YCoordinatesOfArea, color);
                        }
                    }
                }
            }
        }
        return imageToChoice;
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

    private void saveRowToList(File imageFile, int[] rectangleCoordinate) {
        if (-1!=rectangleCoordinate[0]) {
            String output = imageFile.getName() + ";" + rectangleCoordinate[0] + ";" + rectangleCoordinate[2] + ";" + rectangleCoordinate[1] + ";" + rectangleCoordinate[3] + "\n";

            coordinatesToFile.add(output);
            
            /*try {
                for (int i = 0; i < output.length(); i++) {
                    obtainedResulFile.write((int) output.charAt(i));
                }
            } catch (IOException ex) {
                System.out.println("Błąd zapisu do pliku: " + ex);
            }*/
        }

    }
    
    private int computeSignArea(int[] rectangleCoordinate){
        int signArea = (rectangleCoordinate[1]-rectangleCoordinate[0])^2*(rectangleCoordinate[3]-rectangleCoordinate[2])^2;
        return signArea;
    }
}
