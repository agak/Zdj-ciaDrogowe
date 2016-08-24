#!/bin/bash

gotowaBaza="train"
wzorcoweWyniki="train/gtred.txt"
znalezioneWyniki="znalezioneZdjDrogowe.txt"

 java -jar  $1"/ZdjDrogowe-1.0.jar" $gotowaBaza
 java -jar "run/testingEnvironment/target/testingEnvironment-1.0.jar" $wzorcoweWyniki $znalezioneWyniki




