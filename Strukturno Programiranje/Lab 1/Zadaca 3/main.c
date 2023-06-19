#include <stdio.h>
#include <math.h>
int main(){
    float masa, visina,BMI;
    scanf("%f%f",&masa,&visina);
    visina=visina/100;
    BMI=masa/(visina*visina);
    printf("%.2f",BMI);
return 0;   
}