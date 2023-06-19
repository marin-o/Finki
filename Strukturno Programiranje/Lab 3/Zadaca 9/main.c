#include <stdio.h>

int main()
{
    
    int kwh;
    float cena;
    scanf("%d",&kwh);
    if(kwh<=500)
    cena=kwh*5;
    else if(kwh<=650)
    cena=500*5+(kwh-500)*7.5;
    else if(kwh<=850)
    cena=500*5+150*7.5+(kwh-650)*11;
    else if(kwh>850)
    cena=500*5+150*7.5+200*11+(kwh-850)*13.5;
    float cenaDDV;
    if(cena<=7000)
    cenaDDV=cena+10/100.0*cena;
    else cenaDDV=cena+18/100.0*cena;
    printf("%.2f",cenaDDV);
    
    return 0;
}