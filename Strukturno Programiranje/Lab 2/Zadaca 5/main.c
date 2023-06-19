#include <stdio.h>
int main(){
    
    int indeks;
    int a,b,c,d,e,f;
    
    scanf("%d%d%d%d%d%d%d", &indeks,&a,&b,&c,&d,&e,&f);
    printf("Prosek: %.3f\nStudent: %d\n%d godina\nNagraden: %d",(a+b+c+d+e+f)/6.0,indeks,20-(indeks/10000),((a+b+c+d+e+f)/6.0)>=9.5);
    return 0;
}