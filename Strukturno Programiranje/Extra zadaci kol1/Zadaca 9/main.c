#include <stdio.h>
int main(){
    char hex;
    int sum=0;
    do{
        scanf("%c",&hex);
        switch(hex){
            case '0': sum+=0; break;
            case '1': sum+=1; break;
            case '2': sum+=2; break;
            case '3': sum+=3; break;
            case '4': sum+=4; break;
            case '5': sum+=5; break;
            case '6': sum+=6; break;
            case '7': sum+=7; break;
            case '8': sum+=8; break;
            case '9': sum+=9; break;
            case 'a':
            case 'A': sum+=10; break;
            case 'b':
            case 'B': sum+=11; break;
            case 'c':
            case 'C': sum+=12; break;
            case 'd':
            case 'D': sum+=13; break;
            case 'e':
            case 'E': sum+=14; break;
            case 'f':
            case 'F': sum+=15; break;
        }
    }while(hex!='.');
    if(sum%16==0 && sum%100==16){
        printf("Poln pogodok");
    }
    else if(sum%16==0){
        printf("Pogodok");
    }
    else printf("%d",sum);
    return 0;
}