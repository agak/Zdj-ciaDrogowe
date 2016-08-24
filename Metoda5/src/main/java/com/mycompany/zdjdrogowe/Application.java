/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzielinski91
 */
public class Application {
    
    //private static double[][] blue = {{120.0, 255.0}, {150.0, 255.0}, {120.0, 250.0}}; //blue
    //private static double[][] red = {{142.0, 255.0}, {27.0, 86.0}, {28.0, 87.0}}; //czerwony
    //private static double[][] blue2 = {{32.0, 61.0}, {43.0, 77.0}, {96.0, 121.0}}; //niebieski
    //private static double[][] white = {{170.0, 255.0}, {170.0, 255.0}, {201.0, 255.0}}; //biały

    public static void main(String[] args) {
        File file = new File(args[0]);
        //   File file = new File("../run/gotowaBaza");
        

        List<File> pictures = new ArrayList();
        
        PictureListMaker plm = new PictureListMaker();
        pictures = plm.makePicturesList(file);

        
        
       Colors colors = new Colors();
       List<double[][]> colorsList = colors.makeListWithColors();
       
       Methods methods = new Methods();
       
       for(double[][] color : colorsList){
           methods.loadImages(pictures, color);
        //   System.out.println("kolor");
       }
        
        
        
        Methods.tfm.saveListToFile();
        
        


    }

}
