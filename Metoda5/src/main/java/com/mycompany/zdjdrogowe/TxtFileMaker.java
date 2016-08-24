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
public class TxtFileMaker {
    FileOutputStream obtainedResulFile = null;
    List<String> coordinatesToFile =new ArrayList<>();

    
    
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
    
    
    public void saveRowToList(File imageFile, short[] rectangleCoordinate) {
        if (-1!=rectangleCoordinate[0]) {
            String output = imageFile.getName() + ";" + rectangleCoordinate[0] + ";" + rectangleCoordinate[2] + ";" + rectangleCoordinate[1] + ";" + rectangleCoordinate[3] + "\n";
            coordinatesToFile.add(output);
        }
    }
}
