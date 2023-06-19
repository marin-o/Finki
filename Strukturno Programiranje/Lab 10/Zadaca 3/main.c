//GRESHEN E EXPECTED OUTPUT
//NA PR ZA TRETIOT TEST CASE
//PRVATA LINIJA MANUELNO AKO BROIME IMA 23 ZBORA, A VO EXPECTED OUTPUT SE 24


#include <stdio.h>
#include <ctype.h>

void writeToFile() {
    FILE *f = fopen("text.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    writeToFile();
    FILE *fp=fopen("text.txt","r");
    char c;
    int inWord=0;
    int countwords=0, mostWords=0;
    int average=0;
    int line=0,longLine;

    while((c=fgetc(fp))!=EOF){
        if(c=='\n'){
            line++;
            average+=countwords;
            printf("%d\n",countwords);
            if(mostWords<countwords){
                mostWords=countwords;
                longLine=line;
            }

            countwords=0;
            inWord=0;
        }
        else if(isalpha(c) && !inWord){
            inWord=1;
            countwords++;
        }
        else if(!isalpha(c) && c!='\''){
            inWord=0;
        }
    }
    fclose(fp);
    printf("%.2f\n",(float)average/line);
    fp=fopen("text.txt","r");
    for(int i=1;i<=longLine;i++){
        if(i==longLine){
            while((c=fgetc(fp))!='\n'){
                if(isupper(c)){
                    c=tolower(c);
                    printf("%c",c);
                }
                else{
                    c=toupper(c);
                    printf("%c",c);
                }
            }

        }
        else while((c=fgetc(fp))!='\n');
    }
}


