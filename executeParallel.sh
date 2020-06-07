#!/bin/bash

SERVERS=out/sdProject/main/*Server.class
i=0

for file in $SERVERS
do
  tmp=${file//"/"/.}
  tmp=${tmp//out.sdProject.}
  tmp=${tmp//.class}
  java -classpath out/sdProject $tmp &
  pids[${i}]=$!
  i=`expr $i + 1`
done

java -classpath out/sdProject main.ClientBusDriver &
java -classpath out/sdProject main.ClientPorter &
java -classpath out/sdProject main.ClientPassenger

for pid in ${pids[*]}; do
    wait $pid
done


