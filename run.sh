#!/bin/bash

gotowaBaza="run/gotowaBaza"
wzorcoweWyniki="run/gotowaBaza/gt.txt"
znalezioneWyniki="znalezioneZdjDrogowe.txt"

 java -jar  $1"/ZdjDrogowe-1.0.jar" $gotowaBaza
 java -jar "run/testingEnvironment/target/testingEnvironment-1.0.jar" $wzorcoweWyniki $znalezioneWyniki




