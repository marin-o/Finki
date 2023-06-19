#include <stdio.h>
#include <string.h>
#include <ctype.h>

void letterFrequency (char * text, char letter){
    int letNum=0;
    int mala=0,golema=0;
    for(letNum;text[letNum]!='\0';letNum++){
        if(text[letNum]==tolower(letter)){
            mala++;
        }
        else if(text[letNum]==toupper(letter)){
            golema++;
        }
    }
    printf("%c -> %.3f%%\n%c -> %.3f%%",(char)tolower(letter),(float)mala/letNum*100,(char)toupper(letter),(float)golema/letNum*100);
}

int main(){
    char text[1000];
    fgets(text,1000,stdin);
    char letter;
    scanf("%c",&letter);
    letterFrequency(text,letter);
}