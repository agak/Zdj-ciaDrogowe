/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testingenvironment;

/**
 *
 * @author bartek
 */
public class Points {

    private String fileName;
    private int x;
    private int y;


    public Points(String fileName, int x,int y) {
        this.fileName = fileName;
        this.x = x;
        this.y = y;

    }
    
    public Points(){
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
 
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return getFileName().hashCode() ^ (2048 * x + y);
    }  
    
    @Override
     public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Points) {
            Points that = (Points) other;
            result = (this.getX() == that.getX() && this.getY() == that.getY() && this.fileName.equals(that.fileName));
        }
        return result;
    }

}
