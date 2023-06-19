#include <stdio.h>
int funkcija(int x){
    if(x>9){
        if(x%10<x/10%10) {return funkcija(x/10);}
        else return 0;
    }
    if (x<10){
        return 1;
    }
    
}
int main()
{
    int n,b;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        scanf("%d",&b);
        printf("%d\n",funkcija(b));
    }
    return 0;
}