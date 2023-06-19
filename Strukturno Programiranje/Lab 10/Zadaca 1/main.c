#include <stdio.h>
#include <ctype.h>
void letterFrequency (){
    int letNum=0;
    int mala=0,golema=0;
    FILE *fajl=fopen("text.txt","r");
    char ch;
    while((ch=fgetc(fajl))!=EOF){
        if(ch>='a' && ch<='z'){
            mala++;
            letNum++;
        }
        else if(ch>='A' && ch<='Z'){
            golema++;
            letNum++;
        }
    }
    printf("%.4f\n%.4f",(float)golema/letNum,(float)mala/letNum);
    fclose(fajl);
}
void writeToFile() {
    FILE *f = fopen("text.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
    letterFrequency();
}

int main() {
    writeToFile();
}
