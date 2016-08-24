/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzielinski91
 */
public class Colors {
    
    public List<double[][]> makeListWithColors(){
        List<double[][]> colorsList = new ArrayList();
  
    
//          double[][] blue = {{20.0, 45.0}, {35.0, 75.0}, {70.0, 190.0}}; 
//          colorsList.add(blue);
//          
//          double[][] blue2 = {{50.0, 100.0}, {70.0, 100.0}, {150.0, 185.0}};
//          colorsList.add(blue2);

          double[][] red = {{90.0, 120.0}, {35.0, 65.0}, {30.0, 90.0}};
          colorsList.add(red);
          
          double[][] red2 = {{175.0, 255.0}, {20.0, 120.0}, {15.0, 120.0}};
          colorsList.add(red2);
 
          
        return colorsList;
    }
    
}
