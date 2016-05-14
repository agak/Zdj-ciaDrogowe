/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agnieszka
 */
public class Application {

    private static double[][] blue = {{120.0, 255.0}, {150.0, 255.0}, {120.0, 250.0}}; //blue
    private static double[][] red = {{142.0, 255.0}, {27.0, 86.0}, {28.0, 87.0}}; //czerwony
    private static double[][] blue2 = {{32.0, 61.0}, {43.0, 77.0}, {96.0, 121.0}}; //niebieski
    private static double[][] white = {{170.0, 255.0}, {170.0, 255.0}, {201.0, 255.0}}; //biały

    public static void main(String[] args) {
        File file = new File("../../gotowaBaza");
        Methods methods = new Methods();

        List<File> pictures = new ArrayList();
        pictures = methods.makePicturesList(file);
        System.out.println("Kolor niebieski: ");
        methods.loadImagesAndSaveToFile(pictures, blue2);
//        System.out.println("Kolor czerwony: ");
//        methods.loadImagesAndSaveToFile(pictures, red);

    }

}
