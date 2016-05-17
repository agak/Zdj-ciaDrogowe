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
public class Rows {

    private String fileName;
    private int Xmin;
    private int Xmax;
    private int Ymin;
    private int Ymax;

    public Rows(String fileName, int Xmin, int Xmax, int Ymin, int Ymax) {
        this.fileName = fileName;
        this.Xmin = Xmin;
        this.Xmax = Xmax;
        this.Ymin = Ymin;
        this.Ymax = Ymax;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getXmin() {
        return Xmin;
    }

    public void setXmin(int Xmin) {
        this.Xmin = Xmin;
    }

    public int getXmax() {
        return Xmax;
    }

    public void setXmax(int Xmax) {
        this.Xmax = Xmax;
    }

    public int getYmin() {
        return Ymin;
    }

    public void setYmin(int Ymin) {
        this.Ymin = Ymin;
    }

    public int getYmax() {
        return Ymax;
    }

    public void setYmax(int Ymax) {
        this.Ymax = Ymax;
    }

}
