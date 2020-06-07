#!/bin/bash


SERVERS=out/sdProject/main/*Server.class

for file in $SERVERS
do
  tmp=${file//"/"/.}
  tmp=${tmp//out.sdProject.}
  tmp=${tmp//.class}
  x-terminal-emulator java -classpath out/sdProject $tmp
done

x-terminal-emulator java -classpath out/sdProject main.ClientBusDriver
x-terminal-emulator java -classpath out/sdProject main.ClientPorter
x-terminal-emulator java -classpath out/sdProject main.ClientPassenger
