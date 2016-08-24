/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author bzielinski91
 */
public class Methods {
    static TxtFileMaker tfm = new TxtFileMaker();
    
 
    public void loadImages(List<File> pictures, double[][] color) {

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
        short[] rectangleCoordinate = new short[4];
        short x=0, y=0;
        short[] cordinatePixel = {1,1};
        

           
        while(cordinatePixel[0] != 0 && cordinatePixel[1] != 0){
            while((signArea < 40) && cordinatePixel[0] != 0 && cordinatePixel[1] != 0){
                
                //zwraca tablice z dwoma zerami jeśli nie znalazło piksela na obrazie
                cordinatePixel = PixelFinder.findPixelWithColor(imageToChoice, color,x,y);
                x=cordinatePixel[0];
                y=cordinatePixel[1];
                
                //segmentacja
                List<Short> XCoordinatesOfArea = new ArrayList();
                List<Short> YCoordinatesOfArea = new ArrayList(); 
                
                imageToChoice=Area.designateArea(imageToChoice, cordinatePixel, XCoordinatesOfArea, YCoordinatesOfArea, color);

                //oznaczenie wyznaczonej przestrzeni (znaku drogowego) w prostokąt
                rectangleCoordinate = Area.markAreaInARectangle(XCoordinatesOfArea, YCoordinatesOfArea);

                //obliczanie pola przestrzeni
                signArea = Area.computeSignArea(rectangleCoordinate);
            }

            if(signArea > 39){ 
                //System.out.println("is a big object");
                if(Area.isSquare(rectangleCoordinate)){
                    
                    //System.out.println("is square");
                    
                    if(PixelFinder.findWhitePixels(rectangleCoordinate,imageToChoice)){
                        
                        //System.out.println("has a white pixels");
                                
                        ShapeFilter sf = new ShapeFilter(imageToChoice, rectangleCoordinate);
                        //warunek ze sprawdzaniem kształtu (trójkąt,odwrócony trójkąt,kwadrat,koło)
                        if(sf.isSign()){
                            
                            //System.out.println("is sign");
                            
                            tfm.saveRowToList(fileEntry, rectangleCoordinate);
                        }
                    }
                }
            }
            signArea=0;
            
//            try {
//                ImageIO.write(imageToChoice, "jpg", new File("imgTest/"+fileEntry.getName()));
//            } catch (IOException ex) {
//                Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
           
    }

    

}
