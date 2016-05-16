/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testingenvironment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author agnieszka
 */
public class Test {

    public void meanSquaredErrorTest(String modelResultFileName, String obtainedResultFileName) {
        List<Rows> obtainedResultList = loadDataFromFiles(obtainedResultFileName);
        List<Rows> modelResultList = loadDataFromFiles(modelResultFileName);
        List<MeanSquaredErrorBetweenPoints> meanSquaredErrorList = new ArrayList<>();

        String prevFileName = "";
        int obtainedSize = 0;
        for (Rows obtainedRows : obtainedResultList) {
            //System.out.println("A " + obtainedRows.getFileName());
            if (!prevFileName.equals(obtainedRows.getFileName())) {
                obtainedSize = returnResult(meanSquaredErrorList, obtainedSize, prevFileName);
            }

            for (Rows modelRows : modelResultList) {
                if (modelRows.getFileName().equals(obtainedRows.getFileName())) {
                    //System.out.println("B " + modelRows.getFileName());

                    double meanSquaredError = Math.sqrt(Math.pow(modelRows.getXmin() - obtainedRows.getXmin(), 2) + Math.pow(modelRows.getXmax() - obtainedRows.getXmax(), 2)
                            + Math.pow(modelRows.getYmin() - obtainedRows.getYmin(), 2) + Math.pow(modelRows.getYmax() - obtainedRows.getYmax(), 2)) / 4.0;

                    //System.out.println("meanSquaredError" + meanSquaredError);
                    meanSquaredErrorList.add(new MeanSquaredErrorBetweenPoints(obtainedResultList.indexOf(obtainedRows), modelResultList.indexOf(modelRows), meanSquaredError));
                    //System.out.println(obtainedResultList.indexOf(obtainedRows) + " " + modelResultList.indexOf(modelRows) + " " + meanSquaredError);
                    //System.out.println("distancesList size" + meanSquaredErrorList.size());
                }
            }
            obtainedSize++;
            prevFileName = obtainedRows.getFileName();
        }
    }

    private int returnResult(List<MeanSquaredErrorBetweenPoints> meanSquaredErrorList, int obtainedSize, String prevFileName) {
        Comparator<MeanSquaredErrorBetweenPoints> c = (s1, s2) -> new Double(s1.getMeanSquaredError()).compareTo(s2.getMeanSquaredError());
        meanSquaredErrorList.sort(c);
        if (meanSquaredErrorList.size() > 0) {
            for (int i = 0; i < obtainedSize; i++) {
                
                System.out.println("Błąd śreniokwadratowy dla obrazu " + prevFileName + " " + meanSquaredErrorList.get(0).getMeanSquaredError());
                int modelPointNumber = meanSquaredErrorList.get(0).getModelPoint();
                int obtainedPointNumber = meanSquaredErrorList.get(0).getObtainedPoint();
                //System.out.println(modelPointNumber + " " + obtainedPointNumber);
                
                Iterator<MeanSquaredErrorBetweenPoints> iterator = meanSquaredErrorList.iterator();
                while (iterator.hasNext()) {
                    MeanSquaredErrorBetweenPoints meanSquaredErrorBetweenPoints = iterator.next();
                    if (meanSquaredErrorBetweenPoints.getModelPoint() == modelPointNumber || meanSquaredErrorBetweenPoints.getObtainedPoint() == obtainedPointNumber) {
                        iterator.remove();
                    }
                }                obtainedSize = returnResult(meanSquaredErrorList, obtainedSize, prevFileName);
            }
        }
        obtainedSize = 0;
        meanSquaredErrorList.clear();
        return obtainedSize;
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
