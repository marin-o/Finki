#include <stdio.h>

int funkcija(int n,int max){
    if(n==0) return max;
    if(n%10>max){
        max=n%10;
        return funkcija(n/10,max);
    }
    else return funkcija(n/10,max);
}

int main(){
    int n;
    while(scanf("%d",&n)){
        printf("%d\n",funkcija(n,0));
    }
}