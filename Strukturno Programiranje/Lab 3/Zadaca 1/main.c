#include <stdio.h>

int main(){
    
    int broj,temp,i;
    scanf("%d", &broj);
    if(broj<0)
    broj=-broj;
    temp=broj;
    
    for(i=0;temp!=0;temp/=10)
        i++;
    if(i==7)
    {
        for(i=1000000;i!=0;i/=10)
        {
            printf("%d\n",broj/i);
            broj%=i;
        }
    }
    else printf("Vneseniot broj ne e 7 cifren!");
    return 0;
}