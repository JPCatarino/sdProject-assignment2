#!/bin/bash

SERVERS=out/sdProject/main/*Server.class

for file in $SERVERS
do
  tmp=${file//"/"/.}
  tmp=${tmp//out.sdProject.}
  tmp=${tmp//.class}
  java -classpath out/sdProject $tmp &
done

java -classpath out/sdProject main.ClientBusDriver &
java -classpath out/sdProject main.ClientPorter &
java -classpath out/sdProject main.ClientPassenger



