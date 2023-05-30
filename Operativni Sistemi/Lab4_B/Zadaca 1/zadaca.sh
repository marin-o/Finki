#!/bin/bash

today=$(date +%b" "%d)
yesterday=$(date -d "yesterday" +%b" "%d)
files=$(ls -l | grep "^-" | grep -E "$today|$yesterday")
echo "$files" > modified.txt