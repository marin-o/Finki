#include <stdio.h>
#include <math.h>
int main(){
    
    int a,b,c;
    float discriminant;
    scanf("%d%d%d", &a,&b,&c);
    discriminant=pow(b,2)-4*a*c;
    if(discriminant>0)
    printf("Reshenija na ravenkata se: %.2f i %.2f", ((-b-sqrt(discriminant)))/(2.0*a), ((-b+sqrt(discriminant)))/(2.0*a));
    else if(discriminant==0)
    printf("Dvojno reshenie na ravenkata e: %.2f", ((-b-sqrt(discriminant)))/(2.0*a));
    else if(discriminant<0)
    printf("Ravenkata nema realni reshenija");
    return 0;
}