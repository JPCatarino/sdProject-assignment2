#!/bin/bash

./compile.sh

while getopts pm option
do
case "${option}"
in
    p) ./executeParallel.sh;;
    m) ./executeTerms.sh;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      echo '-p to run in parallel'
      echo '-m to run in multiple terminals (May require a terminal emulator)'
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      echo '-p to run in parallel'
      echo '-m to run in multiple terminals (May require a terminal emulator)'
      exit 1
      ;;
esac
done