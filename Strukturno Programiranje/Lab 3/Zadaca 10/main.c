#include <stdio.h>

int main(){
    
    int broj,prvacifra,vtoracifra,zbirdve;
    scanf("%d",&broj);
    int i=0;
    if (broj<0) broj=-broj;
    int temp=broj;
    while(temp!=0){
        i++; temp/=10;
    };
    if(i==7)
    {
       prvacifra=broj/1000000;
       vtoracifra=broj/100000%10;
       zbirdve=broj%10+broj%100/10;
       if(prvacifra!=0&&vtoracifra!=0&&zbirdve!=0)
       {
           if((broj%prvacifra==0 && broj%zbirdve!=0) || (broj%vtoracifra!=0 && broj%zbirdve!=0))
           printf("DA");
           else printf("NE");
       }
       else printf("Nevaliden broj");
    }
    else printf("Nevaliden broj");
    
}