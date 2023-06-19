#include <stdio.h>
void funkcija(int cifra,int k, int *sum){
    if(cifra==0) return;
    int temp=cifra;
    long int delitel=1;
    
    while(temp){
        delitel*=10;
        temp/=10;
    }
    delitel/=10;
    if(cifra/delitel%10>k){
        *sum+=cifra/delitel%10;
        printf("%d",cifra/delitel%10);
    }
    funkcija(cifra%delitel,k,sum);
    
}
int main()
{
    int k,n,b;
    int zbir;
    scanf("%d",&k);
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        zbir=0;
        scanf("%d",&b);
        funkcija(b,k,&zbir);
        printf(" : %d\n",zbir);
    }
    
    return 0;
}