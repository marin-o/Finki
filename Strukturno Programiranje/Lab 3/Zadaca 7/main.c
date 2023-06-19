#include <stdio.h>

int main(){
    int poeni;
    scanf("%d",&poeni);
    if(poeni<51 && poeni>-1)
    printf("Ocenka 5");
    else if(poeni>50&&poeni<61)
        if(poeni%10<4)
        printf("Ocenka 6-");
        else if(poeni%10>3&&poeni%10<8)
        printf("Ocenka 6");
        else printf("Ocenka 6+");
    else if(poeni>60&&poeni<71)
        if(poeni%10<4)
        printf("Ocenka 7-");
        else if(poeni%10>3&&poeni%10<8)
        printf("Ocenka 7");
        else printf("Ocenka 7+");
    else if(poeni>70&&poeni<81)
        if(poeni%10<4)
        printf("Ocenka 8-");
        else if(poeni%10>3&&poeni%10<8)
        printf("Ocenka 8");
        else printf("Ocenka 8+");
    else if(poeni>80&&poeni<91)
        if(poeni%10<4)
        printf("Ocenka 9-");
        else if(poeni%10>3&&poeni%10<8)
        printf("Ocenka 9");
        else printf("Ocenka 9+");
    else if(poeni>90&&poeni<101)
        if(poeni%10<4)
        printf("Ocenka 10-");
        else if(poeni%10>3&&poeni%10<8)
        printf("Ocenka 10");
        else printf("Ocenka 10+");
    else printf("Nevalidna vrednost za poeni!");
    return 0;
}