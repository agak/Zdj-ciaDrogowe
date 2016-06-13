/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testingenvironment;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author bartek
 */
public class Test {



    public void meanSquaredErrorTest(String modelResultFileName, String obtainedResultFileName) {
        List<Rows> obtainedResultList = loadDataFromFiles(obtainedResultFileName);
        List<Rows> modelResultList = loadDataFromFiles(modelResultFileName);
        
        List<Points> obtainedPointsList = makeListOfPoints(obtainedResultList);
        List<Points> modelPointsList = makeListOfPoints(modelResultList);
        
        obtainedPointsList= removeRepetitionsFromList(obtainedPointsList);
        modelPointsList=removeRepetitionsFromList(modelPointsList);
        
        List<Points> correctSignsPointsList = findPointsCommon(modelPointsList, obtainedPointsList);
        correctSignsPointsList=removeRepetitionsFromList(correctSignsPointsList);

        
        //System.out.println("lista a: "+modelPointsList.size());
        //System.out.println("lista b: "+obtainedPointsList.size());
        //System.out.println("lista c: "+correctSignsPointsList.size());
        
        double a = (double) modelPointsList.size();
        double b = (double) obtainedPointsList.size();
        double c = (double) correctSignsPointsList.size();
        double resultTest=(c/(a+(b-c)))*100;
        System.out.println("Wynik: "+resultTest+ "%");   
    }
    
    
    private List<Points> removeRepetitionsFromList(List<Points> a){
        Set<Points> aTmp = new LinkedHashSet<>(a);
        a.clear();
        a.addAll(aTmp);
        
        return a;
    }
    
    
    
    
    private List<Points> findPointsCommon(List<Points> a, List<Points> b){

        Set<Points> aTmp = new LinkedHashSet<>(a);
        Set<Points> bTmp = new LinkedHashSet<>(b);
        
        aTmp.retainAll(bTmp);
        
        return new ArrayList<>(aTmp);
    }
    
    
    
    
    private List<Points> makeListOfPoints(List<Rows> signs){
       List<Points> listOfPoints = new ArrayList<>();
       
       for(Rows sign : signs){
           for(int i = sign.getXmin();i<sign.getXmax();i++){
               for(int j = sign.getYmin();j<sign.getYmax();j++){
                   Points point = new Points(sign.getFileName(), i, j);
                   listOfPoints.add(point);
               }
           }
       }
       
       return listOfPoints;
   }
   
   


    private List<Rows> loadDataFromFiles(String resultFileName) {
        FileReader resultFile = null;
        String obtainedLine = "";
        try {
            resultFile = new FileReader(resultFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Bład przy otwieraniu pliku" + e);
        }
        BufferedReader resultFileBuffer = new BufferedReader(resultFile);
        List<Rows> resultList = new ArrayList<>();
        try {
            while ((obtainedLine = resultFileBuffer.readLine()) != null) {
                String[] obtainedArray = obtainedLine.split(";");
                resultList.add(new Rows(obtainedArray[0], Integer.parseInt(obtainedArray[1]), Integer.parseInt(obtainedArray[2]), Integer.parseInt(obtainedArray[3]), Integer.parseInt(obtainedArray[4])));
            }
        } catch (IOException e) {
            System.out.println("Bląd odczytu z pliku" + e);
        }
        try {
            resultFile.close();
        } catch (IOException e) {
            System.out.println("Błąd zamykania pliku: " + e);
        }
        return resultList;
    }

}
