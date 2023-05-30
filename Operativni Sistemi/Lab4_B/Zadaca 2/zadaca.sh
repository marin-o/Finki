for file in "$source"/*
do
        if [ -f "$file" ]; then
                filename=$(echo "$file" | awk -F "/" '{print $NF}')
                if echo "$filename" | grep -qE "^[A-Z]+\.csv$"; then
                        extensionRemoved=$(echo "$filename" | sed
's/\.csv$//i')
                        mv "$file"
"$destination/$extensionRemoved.moved_csv"
                fi
        fi
done

# valda vaka e ama ne ja proveriv. mn mn mn slicna e so istata zadaca od grupa A