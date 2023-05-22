#!/bin/bash

source="$1"
destination="$2"

if [ -z "$1" ]; then
        echo "Source folder is missing."
        echo "Usage: \"bash zadaca3.sh <source> <destination>"
        echo "Exiting..."
        exit 1
fi

if [ -z "$2" ]; then
        echo "Destination folder is missing."
        echo "Usage: \"bash zadaca3.sh <source> <destination>"
        echo "Exiting..."
        exit 1
fi

if echo "$source" | grep -q "/$"; then
        source=$(echo "$source" | sed 's/\/$//')
fi

if echo "$destination" | grep -q "/$"; then
        destination=$(echo "$destination" | sed 's/\/$//')
fi

if [ ! -d "$source" ]; then
        echo "Source dir does not exist, exiting..."
        exit 1
fi

touch  "$source"/hasUpperCase.txt
touch  "$source"/lowercase.txt
echo "This is some text" > "$source"/lowercase.txt
touch  "$source"/nottxtfilelowercase.sh
touch  "$source"/notTxtHasUppercase.sh
touch  "$source"/anotherlowercase.txt

if [ ! -d "$destination" ]; then
        echo "Destination directory does not exist, creating..."
        mkdir -p ./"$destination"
fi


for file in "$source"/*
do
        if [ -f "$file" ]; then
                filename=$(echo "$file" | awk -F "/" '{print $NF}')
                if echo "$filename" | grep -qE "^[a-z]+\.txt$"; then
                        extensionRemoved=$(echo "$filename" | sed 's/\.txt$//i')
                        mv "$file" "$destination/$extensionRemoved.moved_txt"
                fi
        fi
done


totalsize=$(ls -l "$destination" | awk '{ sum += $6 } END {print "Total file size:", sum}')
echo "$totalsize"
