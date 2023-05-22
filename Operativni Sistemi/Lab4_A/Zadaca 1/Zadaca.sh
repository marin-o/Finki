#!/bin/bash

touch /home/<indeks>/todayFile.txt

today=`date +%b" "%d`
datoteki=$(ls -l  | grep "^-" | grep "$today" | sed 's/.*:[0-9][0-9] //gi')
echo "$datoteki" > modified.txt
