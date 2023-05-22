#!/bin/bash

source="$1"
destination="$2"

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


echo "Creating dummy files for purposes of lab exercise..."
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
                filename=$(echo "$file" | awk -F "/" '{print $NF}')
                if echo "$filename" | grep -E "^[a-z]+\.txt$"; then
                        extensionRemoved=$(echo "$filename" | sed 's/\.txt$//')
                        mv "$file" "$destination/$extensionRemoved.moved_txt"
                fi
        fi
done
