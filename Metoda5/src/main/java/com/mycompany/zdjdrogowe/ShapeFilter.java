/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zdjdrogowe;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bzielinski91
 */
public class ShapeFilter {
    
    private BufferedImage img;
    private short[] rectangleCoordinate;

    public ShapeFilter(BufferedImage img, short[] rectangleCoordinate) {
        this.img = img;
        this.rectangleCoordinate = rectangleCoordinate;
    }
    
    
    
    public boolean isSign(){
        
        List<boolean[]> shapes = new ArrayList<>();
        
        //boolean[] square = {true,true,true,true,true,true,true,true}; //kształt kwadratu
        boolean[] triangle = {false,true,false,false,true,true,true,false}; //kształt trójkąta (delta)
        boolean[] circle = {false,true,false,true,false,true,false,true}; //kształt koła
        boolean[] traingle2 = {true,true,true,true,true,true,true,true}; //kształt trójkąta (odwrócona delta)
        
        //shapes.add(square);
        shapes.add(triangle);
        shapes.add(circle);
        shapes.add(traingle2);
        
        boolean[] testShape = designateShape();
        
        boolean result = false;
        
        for(boolean[] shape:shapes){
            if(Arrays.equals(shape, testShape)){
                result=true;
                break;
            }
        } 
        
        
        return result;
    }
    
    
    private boolean[] designateShape(){
        //od górnego lewego rogu i zgodnie z ruchem wskazówek zegara
        Point a = new Point(this.rectangleCoordinate[0], this.rectangleCoordinate[2]);
        Point b = new Point((short)(this.rectangleCoordinate[0]+(this.rectangleCoordinate[1]-this.rectangleCoordinate[0])/2), this.rectangleCoordinate[2]);
        Point c = new Point(this.rectangleCoordinate[1], this.rectangleCoordinate[2]);
        Point d = new Point(this.rectangleCoordinate[1], (short)(this.rectangleCoordinate[2]+(this.rectangleCoordinate[3]-this.rectangleCoordinate[2])/2));
        Point e = new Point(this.rectangleCoordinate[1], this.rectangleCoordinate[3]);
        Point f = new Point((short)(this.rectangleCoordinate[0]+(this.rectangleCoordinate[1]-this.rectangleCoordinate[0])/2), this.rectangleCoordinate[3]);
        Point g = new Point(this.rectangleCoordinate[0], this.rectangleCoordinate[3]);
        Point h = new Point(this.rectangleCoordinate[0], (short)(this.rectangleCoordinate[2]+(this.rectangleCoordinate[3]-this.rectangleCoordinate[2])/2));
        
        List<Point> points = new ArrayList<>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);
        points.add(f);
        points.add(g);
        points.add(h);
        
        boolean[] result= new boolean[8];
        
        byte i =0;
        for(Point point:points){
            result[i]=checkPixel(point);
            i++;
        }
        

        return result;
    }
    
    private boolean checkPixel(Point point){
        
        double[] colorRGB = new double[3];
        short numberOfPixels=0;
        boolean result=false;
        
        for(short x = (short)(point.x-5); x<point.x+5;x++){
            for(short y = (short)(point.y-5); y<point.y+5;y++){
                this.img.getRaster().getPixel(x, y, colorRGB);
                if(colorRGB[0]==255 && colorRGB[1]==255 && colorRGB[2]==0){
                    numberOfPixels++;
                }
            }
        }
        
        if(numberOfPixels>1){
            result=true;
        }
        
        return result;
    }
    
    
}
