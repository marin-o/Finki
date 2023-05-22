#!/bin/bash

source="$1"
destination="$2"

if echo "$source" | grep -q "/$"; then
        source=$(echo "$source" | sed 's/\/$//')
        echo "source: $source"
fi

if echo "$destination" | grep -q "/$"; then
        destination=$(echo "$destination" | sed 's/\/$//')
        echo "destination: $destination"
fi

if [ ! -d "$source" ]; then
        echo "Source dir does not exist, exiting..."
        exit 1
fi


touch  "$source"/hasUpperCase.txt
touch  "$source"/lowercase.txt
touch  "$source"/nottxtfilelowercase.sh
touch  "$source"/notTxtHasUppercase.sh

if [ ! -d "$destination" ]; then
        echo "Destination directory does not exist, creating..."
        mkdir ./"$destination"
fi


for file in "$source"/*
do
        echo "file: $file"
        if [ -f "$file" ]; then
                echo "passed file check: $file"
                filename=$(echo "$file" | awk -F "/" '{print $NF}')
                echo "file without folder:$filename"
                if echo "$filename" | grep -E "^[a-z]+\.txt$"; then
                        echo "passed regex check: $filename"
                        extensionRemoved=$(echo "$filename" | sed 's/\.txt$//')
                        mv "$file" "$destination/$extensionRemoved.moved_txt"
                fi
        fi
done
