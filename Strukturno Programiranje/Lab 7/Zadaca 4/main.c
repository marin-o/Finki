#include <stdio.h>
int obratenBroj (int broj){
    int obraten=0;
    for(broj;broj!=0;broj/=10){
        obraten=obraten*10+broj%10;
    }
    return obraten;
}
int sumCifri(int broj){
    int sum=0;
    for(broj;broj!=0;broj/=10){
        sum+=broj%10;
    }
    return sum;
}

void pecati(int a, int b){
    for(int i=a;i<=b;i++){
        if(((i+obratenBroj(i))%sumCifri(i+obratenBroj(i)))==0) printf("%d\n",i);
    }
}
int main()
{
    int a,b;
    scanf("%d%d",&a,&b);
    pecati(a,b);
   return 0;
}