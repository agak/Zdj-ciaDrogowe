/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testingenvironment;

/**
 *
 * @author agnieszka
 */
public class MeanSquaredErrorBetweenPoints {

    private int modelPointNumber;
    private int obtainedPointNumber;
    private double meanSquaredError;

    public MeanSquaredErrorBetweenPoints(int modelPointNumber, int obtainedPointNumber, double meanSquaredError) {
        this.modelPointNumber = modelPointNumber;
        this.obtainedPointNumber = obtainedPointNumber;
        this.meanSquaredError = meanSquaredError;
    }

    public int getModelPoint() {
        return modelPointNumber;
    }

    public void setModelPoint(int modelPointNumber) {
        this.modelPointNumber = modelPointNumber;
    }

    public int getObtainedPoint() { 
        return obtainedPointNumber;
    }

    public void setObtainedPoint(int obtainedPointNumber) {
        this.obtainedPointNumber = obtainedPointNumber;
    }

    public double getMeanSquaredError() {
        return meanSquaredError;
    }

    public void setMeanSquaredError(double meanSquaredError) {
        this.meanSquaredError = meanSquaredError;
    }
}
