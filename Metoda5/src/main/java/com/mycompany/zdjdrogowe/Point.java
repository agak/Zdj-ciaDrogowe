/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

/**
 *
 * @author bzielinski91
 */
public class Point {
    public final short x;
    public final short y;
    
    public Point(short x, short y) {
        this.x=x;
        this.y=y;
    }
    
    @Override
    public int hashCode() {
        return (2048 * x + y);
    }  
    
    @Override
     public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Point) {
            Point that = (Point) other;
            result = (this.x == that.y && this.y == that.y);
        }
        return result;
    }
    
}
