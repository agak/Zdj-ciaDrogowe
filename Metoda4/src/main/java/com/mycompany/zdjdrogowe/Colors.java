/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzielinski91
 */
public class Colors {
    
    public List<double[][]> makeListWithColors(){
        List<double[][]> colorsList = new ArrayList();
        
//          double[][] red = {{107.0, 255.0}, {0.0, 144.0}, {19.0, 116.0}}; //czerwony
//          double[][] red2 = {{51.0, 81.0}, {19.0, 50.0}, {23.0, 62.0}}; //bordo
//          double[][] blue = {{19.0, 80.0}, {30.0, 94.0}, {61.0, 122.0}}; //niebieski
//          double[][] blue2 = {{46.0, 80.0}, {15.0, 71.0}, {122.0, 153.0}}; //niebieski
//          double[][] blue3 = {{55.0, 124.0}, {43.0, 107.0}, {158.0, 197.0}}; //niebieski
//          double[][] blue4 = {{53.0, 170.0}, {89.0, 205.0}, {210.0, 255.0}}; //niebieski
//          double[][] blue5 = {{143.0, 205.0}, {253.0, 255.0}, {249.0, 255.0}}; //jasno niebieski (w b14)
//          double[][] blue6 = {{81.0, 90.0}, {28.0, 75.0}, {212.0, 255.0}}; // niebieski
//          double[][] yellow = {{251.0, 255.0}, {251.0, 255.0}, {78.0, 221.0}}; //zółty
//          double[][] yellow2 = {{131.0, 142.0}, {79.0, 86.0}, {29.0, 55.0}}; //zółty tak naprawde pomarańczowy
//          double[][] yellow3 = {{195.0, 241.0}, {151.0, 180.0}, {99.0, 123.0}}; //zółty na pół pomarańczowy
//          double[][] yellow4 = {{119.0, 201.0}, {81.0, 113.0}, {38.0, 80.0}}; // pomarańczowy
//          double[][] yellow5 = {{95.0, 123.0}, {57.0, 69.0}, {20.0, 37.0}}; // pomarańczowy
//          double[][] yellow6 = {{204.0, 250.0}, {113.0, 136.0}, {36.0, 61.0}}; // pomarańczowy
//          double[][] yellow7 = {{95.0, 108.0}, {74.0, 85.0}, {42.0, 50.0}}; // pomarańczowy
//
//          //------------------------------------
//          double[][] red10 = {{34.0, 72.0}, {17.0, 40.0}, {17.0, 47.0}}; //bordo wrecz czarny (99,93,88,88,75,75,73,71,71,71,69,65,63,61)
//          double[][] red11 = {{62.0, 77.0}, {32.0, 53.0}, {30.0, 62.0}}; //bordo (98,98,85,83,73, 64, 60)
//          double[][] red12 = {{76.0, 130.0}, {17.0, 66.0}, {32.0, 79.0}}; //bordo pod fiolet(97,97,96,96,94,94,93,89,89,81,80,79,73,73,71,68,67)
//          double[][] red13 = {{111.0, 142.0}, {71.0, 97.0}, {68.0, 97.0}}; //szary czerwony (91,91,90)
//          double[][] red14 = {{245.0, 255.0}, {0.0, 55.0}, {24.0, 52.0}}; //intensywnie czerwony (78)
//          double[][] red15 = {{230.0, 255.0}, {64.0, 116.0}, {76.0, 118.0}}; //rózowy wpadajacy w pomarańczowy (77)
//          double[][] red16 = {{184.0, 249.0}, {51.0, 90.0}, {58.0, 96.0}}; //czerwono różowo pomarańczowy (77,73)
//          double[][] red17 = {{108.0, 162.0}, {12.0, 54.0}, {26.0, 58.0}}; //bordo pod fioloet (76,76)
//          double[][] red18 = {{98.0, 255.0}, {18.0, 52.0}, {40.0, 58.0}}; //od bordo do intensywnej czerwieni (74,74,55)
//          double[][] red19 = {{231.0, 255.0}, {0.0, 30.0}, {0.0, 68.0}}; // intensywnej czerwieni (57)
//          double[][] red20 = {{94.0, 110.0}, {27.0, 36.0}, {19.0, 36.0}}; // czerwony (56)
//
//          double[][] blue10 = {{31.0, 59.0}, {30.0, 53.0}, {70.0, 90.0}}; //granatowy pod fioletowy (97,83,60)
//          double[][] blue11 = {{70.0, 79.0}, {80.0, 85.0}, {106.0, 114.0}}; //niebieski szary (92)
//          double[][] blue12 = {{38.0, 57.0}, {28.0, 56.0}, {111.0, 140.0}}; //niebieski intensywny (84,74)
//          double[][] blue13 = {{27.0, 34.0}, {28.0, 35.0}, {46.0, 54.0}}; //granatowy  (82)
//          double[][] blue14 = {{111.0, 191.0}, {249.0, 255.0}, {246.0, 255.0}}; //jasno niebieski  (81)
//          double[][] blue15 = {{52.0, 65.0}, {54.0, 66.0}, {97.0, 106.0}}; //jasno niebieski  (66)
          
          double[][] red = {{142.0, 252.0}, {27.0, 86.0}, {28.0, 87.0}};
          double[][] blue = {{32.0, 61.0}, {43.0, 77.0}, {102.0, 130.0}};
                    colorsList.add(red); 
          colorsList.add(blue); 
          
          //colorsList.add(red); //ten jest be
          //colorsList.add(red2); //daje prawie 70 wyników i 8% sktueczności
          
          
          //colorsList.add(blue); //ten jest be
          //colorsList.add(blue2); //ten jest be bo nie znajduje nic
          //colorsList.add(blue3);// ten znajduje jeden wynik
          //colorsList.add(blue4); //dla tego zero procent
          //colorsList.add(blue5); //nie znajduje nic
          //colorsList.add(blue6); //nie znajduje nic
          
          
          /*colorsList.add(yellow);
          colorsList.add(yellow2);
          colorsList.add(yellow3);
          colorsList.add(yellow4);
          colorsList.add(yellow5);
          colorsList.add(yellow6);
          colorsList.add(yellow7);*/ //wszystkie żółte dają łącznie 7 wyników i 0% skuteczności
          
          
          //colorsList.add(red10); //ten znajduje prawie 600 wyników
          //colorsList.add(red11); //ten znajduje prawie 600 wyników
          //colorsList.add(red12); // znajduje prawie 100 wyników ale daje tylko 0.3% skutczności
          //colorsList.add(red13); //daje 5 wyników i 0.3% skuteczności
          //colorsList.add(red14); //nie znajduje nic
          //colorsList.add(red15); //znajduje 8 wyników ale 0% skuteczności
          //colorsList.add(red16);
          //colorsList.add(red17);
          //colorsList.add(red18);
          //colorsList.add(red19);
          //colorsList.add(red20);
          /*colorsList.add(blue10);
          colorsList.add(blue11);
          colorsList.add(blue12);
          colorsList.add(blue13);
          colorsList.add(blue14);
          colorsList.add(blue15);*/
          
        return colorsList;
    }
    
}
