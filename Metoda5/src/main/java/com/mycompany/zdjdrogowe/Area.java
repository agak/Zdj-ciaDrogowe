/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author bzielinski91
 */
public class Area {
    
    
    public static BufferedImage designateArea(BufferedImage imageToChoice, short[] cordinatePixel, List<Short> XCoordinatesOfArea, List<Short> YCoordinatesOfArea, double[][] color) {
        double[] colorRGB = new double[3];
        int[] colorRGBvisited = new int[]{255,255,0};
 
        ArrayDeque<Point> kolejka = new ArrayDeque<>();
        kolejka.add(new Point(cordinatePixel[0], cordinatePixel[1]));
        HashSet<Point> odwiedzone = new HashSet<>();
        
        while(!kolejka.isEmpty()){
            Point temp = kolejka.remove();
            for (short x = (short)(temp.x - 1); x <= temp.x + 1; x++) {
                for (short y = (short)(temp.y - 1); y <= temp.y + 1; y++) {
                    if (x >= 0 && x < imageToChoice.getWidth() && y >= 0 && y < imageToChoice.getHeight()) {
                       imageToChoice.getRaster().getPixel(x, y, colorRGB);
                       if(colorRGB[0] >= color[0][0] && colorRGB[0] <= color[0][1] && colorRGB[1] >= color[1][0] && colorRGB[1] <= color[1][1] && colorRGB[2] >= color[2][0] && colorRGB[2] <= color[2][1]){
                           Point kolejny = new Point(x,y);
                           if(!odwiedzone.contains(kolejny)){
                               odwiedzone.add(kolejny);
                               kolejka.add(kolejny);
                               imageToChoice.getRaster().setPixel(kolejny.x, kolejny.y, colorRGBvisited);
                               cordinatePixel[0] = kolejny.x;
                               cordinatePixel[1] = kolejny.y;
                           }
                       }
                    }
                }
            }
        }
        
        if(odwiedzone.size() < 8000){
            for(Point p:odwiedzone){
                XCoordinatesOfArea.add(p.x);
                YCoordinatesOfArea.add(p.y);
            }   
        }
        return imageToChoice;
    }
    
    
    
    public static short[] markAreaInARectangle(List XCoordinatesOfArea, List YCoordinatesOfArea) {
        short[] rectangleCoordinate = {-1, -1, -1, -1};  //-1 jeśli nie znaleziono znaku
        if (XCoordinatesOfArea.size() > 0) {
            rectangleCoordinate[0] = (short) Collections.min(XCoordinatesOfArea);
            rectangleCoordinate[1] = (short) Collections.max(XCoordinatesOfArea);
            rectangleCoordinate[2] = (short) Collections.min(YCoordinatesOfArea);
            rectangleCoordinate[3] = (short) Collections.max(YCoordinatesOfArea);
            }
        return rectangleCoordinate;
    }
    
    public static int computeSignArea(short[] rectangleCoordinate){
        int signArea = (rectangleCoordinate[1]-rectangleCoordinate[0])^2*(rectangleCoordinate[3]-rectangleCoordinate[2])^2;
        return signArea;
    }
    
    public static boolean isSquare(short[] rectangleCoordinate){
        
        short a = (short) (rectangleCoordinate[1] - rectangleCoordinate[0]);
        short b = (short) (rectangleCoordinate[3] - rectangleCoordinate[2]);
        
        boolean isSquare=false;
        
        if(Math.abs(a-b) < 10){
            isSquare=true;
        }

        return isSquare;
    }
}
