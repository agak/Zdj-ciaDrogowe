/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;

/**
 *
 * @author bzielinski91
 */
public class PixelFinder {
    
    public static short[] findPixelWithColor(BufferedImage imageToChoice, double[][] color,short x,short y) {
        double[] pixel = new double[3];
        short[] cordinate = new short[2];
        outerloop:
        for (short a = x; a < imageToChoice.getWidth(); a++) {
            for (short b = 0; b < imageToChoice.getHeight(); b++) {
                imageToChoice.getRaster().getPixel(a, b, pixel);

                if (pixel[0] >= color[0][0] && pixel[0] <= color[0][1] && pixel[1] >= color[1][0] && pixel[1] <= color[1][1] && pixel[2] >= color[2][0] && pixel[2] <= color[2][1]) {
                    cordinate[0] = a;
                    cordinate[1] = b;
                    break outerloop;
                }
            }
        }
        return cordinate;
    }
    
    
    public static boolean findWhitePixels(short[] rectangleCoordinate, BufferedImage imageToChoice){
        int white=0;
        double[] pixel=new double[3];
        
        for(short i = rectangleCoordinate[0]; i<=rectangleCoordinate[1];i++){
            for(short j=rectangleCoordinate[2]; j<=rectangleCoordinate[3];j++){
                imageToChoice.getRaster().getPixel(i, j, pixel);
                if(pixel[0]>65 && pixel[1]>65 && pixel[2]>65){
                    double avarage=(pixel[0]+pixel[1]+pixel[2])/3;
                    double MSE = (Math.pow((double)pixel[0]-avarage,2)+Math.pow((double)pixel[1]-avarage,2)+Math.pow((double)pixel[2]-avarage,2))/3;
                    if (MSE<20) {
                        white++;
                    }
                }
            }
        }
        
        boolean isTrafficSign = false;
        
        if(white >= 10){
            isTrafficSign=true;
        }
        return isTrafficSign;
    }
    
}
