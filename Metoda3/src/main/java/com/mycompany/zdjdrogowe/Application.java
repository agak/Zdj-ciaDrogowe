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

//    private static double[][] blue = {{120.0, 255.0}, {150.0, 255.0}, {120.0, 250.0}}; //blue
//    private static double[][] red = {{142.0, 255.0}, {27.0, 86.0}, {28.0, 87.0}}; //czerwony
//    private static double[][] blue2 = {{32.0, 61.0}, {43.0, 77.0}, {96.0, 121.0}}; //niebieski
//    private static double[][] white = {{170.0, 255.0}, {170.0, 255.0}, {201.0, 255.0}}; //biały
    private static double[][] red = {{107.0, 255.0}, {0.0, 144.0}, {19.0, 116.0}}; //czerwony
    private static double[][] red2 = {{51.0, 81.0}, {19.0, 50.0}, {23.0, 62.0}}; //bordo
    private static double[][] blue = {{19.0, 80.0}, {30.0, 94.0}, {61.0, 122.0}}; //niebieski
    private static double[][] blue2 = {{46.0, 80.0}, {15.0, 71.0}, {122.0, 153.0}}; //niebieski
    private static double[][] blue3 = {{55.0, 124.0}, {43.0, 107.0}, {158.0, 197.0}}; //niebieski
    private static double[][] blue4 = {{53.0, 170.0}, {89.0, 205.0}, {210.0, 255.0}}; //niebieski
    private static double[][] blue5 = {{143.0, 205.0}, {253.0, 255.0}, {249.0, 255.0}}; //jasno niebieski (w b14)
    private static double[][] blue6 = {{81.0, 90.0}, {28.0, 75.0}, {212.0, 255.0}}; // niebieski
    private static double[][] yellow = {{251.0, 255.0}, {251.0, 255.0}, {78.0, 221.0}}; //zółty
    private static double[][] yellow2 = {{131.0, 142.0}, {79.0, 86.0}, {29.0, 55.0}}; //zółty tak naprawde pomarańczowy
    private static double[][] yellow3 = {{195.0, 241.0}, {151.0, 180.0}, {99.0, 123.0}}; //zółty na pół pomarańczowy
    private static double[][] yellow4 = {{119.0, 201.0}, {81.0, 113.0}, {38.0, 80.0}}; // pomarańczowy
    private static double[][] yellow5 = {{95.0, 123.0}, {57.0, 69.0}, {20.0, 37.0}}; // pomarańczowy
    private static double[][] yellow6 = {{204.0, 250.0}, {113.0, 136.0}, {36.0, 61.0}}; // pomarańczowy
    private static double[][] yellow7 = {{95.0, 108.0}, {74.0, 85.0}, {42.0, 50.0}}; // pomarańczowy

    //------------------------------------
    private static double[][] red10 = {{34.0, 72.0}, {17.0, 40.0}, {17.0, 47.0}}; //bordo wrecz czarny (99,93,88,88,75,75,73,71,71,71,69,65,63,61)
    private static double[][] red11 = {{62.0, 77.0}, {32.0, 53.0}, {30.0, 62.0}}; //bordo (98,98,85,83,73, 64, 60)
    private static double[][] red12 = {{76.0, 130.0}, {17.0, 66.0}, {32.0, 79.0}}; //bordo pod fiolet(97,97,96,96,94,94,93,89,89,81,80,79,73,73,71,68,67)
    private static double[][] red13 = {{111.0, 142.0}, {71.0, 97.0}, {68.0, 97.0}}; //szary czerwony (91,91,90)
    private static double[][] red14 = {{245.0, 255.0}, {0.0, 55.0}, {24.0, 52.0}}; //intensywnie czerwony (78)
    private static double[][] red15 = {{230.0, 255.0}, {64.0, 116.0}, {76.0, 118.0}}; //rózowy wpadajacy w pomarańczowy (77)
    private static double[][] red16 = {{184.0, 249.0}, {51.0, 90.0}, {58.0, 96.0}}; //czerwono różowo pomarańczowy (77,73)
    private static double[][] red17 = {{108.0, 162.0}, {12.0, 54.0}, {26.0, 58.0}}; //bordo pod fioloet (76,76)
    private static double[][] red18 = {{98.0, 255.0}, {18.0, 52.0}, {40.0, 58.0}}; //od bordo do intensywnej czerwieni (74,74,55)
    private static double[][] red19 = {{231.0, 255.0}, {0.0, 30.0}, {0.0, 68.0}}; // intensywnej czerwieni (57)
    private static double[][] red20 = {{94.0, 110.0}, {27.0, 36.0}, {19.0, 36.0}}; // czerwony (56)
    
    private static double[][] blue10 = {{31.0, 59.0}, {30.0, 53.0}, {70.0, 90.0}}; //granatowy pod fioletowy (97,83,60)
    private static double[][] blue11 = {{70.0, 79.0}, {80.0, 85.0}, {106.0, 114.0}}; //niebieski szary (92)
    private static double[][] blue12 = {{38.0, 57.0}, {28.0, 56.0}, {111.0, 140.0}}; //niebieski intensywny (84,74)
    private static double[][] blue13 = {{27.0, 34.0}, {28.0, 35.0}, {46.0, 54.0}}; //granatowy  (82)
    private static double[][] blue14 = {{111.0, 191.0}, {249.0, 255.0}, {246.0, 255.0}}; //jasno niebieski  (81)
    private static double[][] blue15 = {{52.0, 65.0}, {54.0, 66.0}, {97.0, 106.0}}; //jasno niebieski  (66)

    public static void main(String[] args) {
        File file = new File(args[0]);
        //   File file = new File("../run/gotowaBaza");
        Methods methods = new Methods();

        List<File> pictures = new ArrayList();
        pictures = methods.makePicturesList(file);
        //System.out.println("Kolor niebieski: ");
        methods.loadImagesAndSaveToFile(pictures, red);
//        System.out.println("Kolor czerwony: ");
//        methods.loadImagesAndSaveToFile(pictures, red);

    }

}
