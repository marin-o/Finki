#include <stdio.h>
int main(){
    
    
    int destinacija;
    
    
    
    int c;
    int k;
    int p; //prva kompanija
    
    int v;
    int b;
    int d;
    int m;//vtora kompanija
    
    scanf("%d",&destinacija);
    scanf("%d%d%d", &c,&k,&p);
    scanf("%d%d%d%d", &v,&b,&m,&d);
    
    int Taxi1=c+p*(destinacija-k);
    int Taxi2=b+destinacija*d+destinacija/v*m;
    
    printf("%d %d", Taxi1,Taxi2);
    printf("\n%d",Taxi1<Taxi2 || Taxi1==Taxi2);
    
    return 0;
}