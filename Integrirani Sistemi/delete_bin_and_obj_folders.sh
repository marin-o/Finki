#!/bin/bash

# Function to delete obj and bin folders recursively
delete_obj_bin() {
    local dir="$1"
    # Look for directories
    for d in "$dir"/*; do
        if [ -d "$d" ]; then
            # Check if it's an obj or bin directory
            if [ "$(basename "$d")" = "obj" ] || [ "$(basename "$d")" = "bin" ]; then
                echo "Deleting: $d"
                rm -rf "$d"
            else
                # Recursively call this function for subdirectories
                delete_obj_bin "$d"
            fi
        fi
    done
}

# Call the function starting from the current directory
delete_obj_bin "$(pwd)"
