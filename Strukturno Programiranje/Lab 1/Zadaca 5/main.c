#include <stdio.h>

int main(){
    int s,min,h,sec;
    scanf("%d",&s);
    sec=s;
    h=s/3600;
    min=(s-h*3600)/60;
    s=s-h*3600-min*60;
    printf("%d sekundi se %d casovi, %d minuti i %d sekundi",sec,h,min,s);
    return 0;
}