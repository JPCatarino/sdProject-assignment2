#!/bin/bash

mkdir -p out/sdProject
javac -d out/sdProject -classpath out/sdProject src/common/* src/exceptions/* src/states/* src/entities/* src/interfaces/* src/sharedRegions/* src/stubs/* src/proxies/* src/main/*
