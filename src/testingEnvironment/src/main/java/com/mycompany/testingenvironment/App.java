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
public class App {

    public static void main(String[] args) {
        Test test = new Test();
        test.meanSquaredErrorTest(args[0],args[1]);
        //test.meanSquaredErrorTest("../gotowaBaza/gt.txt","../znalezioneZdjDrogowe.txt");

    }

}
