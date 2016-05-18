/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testingenvironment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author agnieszka
 */
public class Test {

    private double sumMeanSquaredError = 0;
    private int countResult = 0;
    private int correctFondAmount = 0;

    public void meanSquaredErrorTest(String modelResultFileName, String obtainedResultFileName) {
        List<Rows> obtainedResultList = loadDataFromFiles(obtainedResultFileName);
        List<Rows> modelResultList = loadDataFromFiles(modelResultFileName);
        List<MeanSquaredErrorBetweenPoints> meanSquaredErrorList = new ArrayList<>();

        String prevFileName = "";
        int obtainedForImageSize = 0;
        int modelForImageSize = 0;
        for (Rows obtainedRows : obtainedResultList) {
            //System.out.println("A " + obtainedRows.getFileName());
            if (!prevFileName.equals(obtainedRows.getFileName()) && !"".equals(prevFileName)) {
                obtainedForImageSize = returnResult(meanSquaredErrorList, obtainedForImageSize, prevFileName, modelForImageSize);
            }

            modelForImageSize = compareWithModel(modelResultList, obtainedRows, meanSquaredErrorList, obtainedResultList);
            obtainedForImageSize++;
            prevFileName = obtainedRows.getFileName();

            if (obtainedResultList.indexOf(obtainedRows) == (obtainedResultList.size() - 1)) {
                obtainedForImageSize = returnResult(meanSquaredErrorList, obtainedForImageSize, prevFileName, modelForImageSize);
            }
        }
        System.out.println("Średnia błędu śreniokwadratowy dla wszystkich obrazów ");
        System.out.println(sumMeanSquaredError / countResult);
        System.out.println("Średnia ilość poprawnie znalezionych znaków ");
        System.out.println((double) correctFondAmount / countResult);
    }

    private int compareWithModel(List<Rows> modelResultList, Rows obtainedRows, List<MeanSquaredErrorBetweenPoints> meanSquaredErrorList, List<Rows> obtainedResultList) {
        int modelForImageSize = 0;
        for (Rows modelRows : modelResultList) {
            if (modelRows.getFileName().equals(obtainedRows.getFileName())) {
                modelForImageSize++;
                //System.out.println("B " + modelRows.getFileName());

                double meanSquaredError = Math.sqrt(Math.pow(modelRows.getXmin() - obtainedRows.getXmin(), 2) + Math.pow(modelRows.getXmax() - obtainedRows.getXmax(), 2)
                        + Math.pow(modelRows.getYmin() - obtainedRows.getYmin(), 2) + Math.pow(modelRows.getYmax() - obtainedRows.getYmax(), 2)) / 4.0;

                //System.out.println("meanSquaredError" + meanSquaredError);
                meanSquaredErrorList.add(new MeanSquaredErrorBetweenPoints(obtainedResultList.indexOf(obtainedRows), modelResultList.indexOf(modelRows), meanSquaredError));

                //System.out.println(obtainedResultList.indexOf(obtainedRows) + " " + modelResultList.indexOf(modelRows) + " " + meanSquaredError);
                //System.out.println("distancesList size" + meanSquaredErrorList.size());
            }
        }
        return modelForImageSize;
    }

    private int returnResult(List<MeanSquaredErrorBetweenPoints> meanSquaredErrorList, int obtainedForImageSize, String prevFileName, int modelForImageSize) {
        boolean amountIsTheSame = obtainedForImageSize == modelForImageSize;
        System.out.println(prevFileName + "\t Wzorcowe " + "\t Znalezione" + "\t Poprawność");
        System.out.println("\t \t \t" + obtainedForImageSize + "\t \t" + modelForImageSize + " \t " + Boolean.toString(amountIsTheSame));
        if (amountIsTheSame) {
            correctFondAmount++;
        }
        Comparator<MeanSquaredErrorBetweenPoints> c = (s1, s2) -> new Double(s1.getMeanSquaredError()).compareTo(s2.getMeanSquaredError());
        meanSquaredErrorList.sort(c);
        for (int i = 0; i < obtainedForImageSize; i++) {
            if (meanSquaredErrorList.size() > 0) {
                System.out.println("Błąd średniokwadratowy dla obrazu " + prevFileName + " ");
                System.out.println(meanSquaredErrorList.get(0).getMeanSquaredError());
                countResult++;
                sumMeanSquaredError = sumMeanSquaredError + meanSquaredErrorList.get(0).getMeanSquaredError();
                int modelPointNumber = meanSquaredErrorList.get(0).getModelPoint();
                int obtainedPointNumber = meanSquaredErrorList.get(0).getObtainedPoint();
                //System.out.println(modelPointNumber + " " + obtainedPointNumber);

                Iterator<MeanSquaredErrorBetweenPoints> iterator = meanSquaredErrorList.iterator();
                while (iterator.hasNext()) {
                    MeanSquaredErrorBetweenPoints meanSquaredErrorBetweenPoints = iterator.next();
                    if (meanSquaredErrorBetweenPoints.getModelPoint() == modelPointNumber || meanSquaredErrorBetweenPoints.getObtainedPoint() == obtainedPointNumber) {
                        iterator.remove();
                    }
                }
            }
        }
        obtainedForImageSize = 0;
        meanSquaredErrorList.clear();
        return obtainedForImageSize;
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
