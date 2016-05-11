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
    
    
    public static void main(String [] args){
        File file = new File("../../gotowaBaza");
        Methods methods = new Methods();
        
        List<File> pictures = new ArrayList();
        pictures = methods.makePicturesList(file);
        
        methods.loadImages(pictures);
        
    }
    
}
