#include <stdio.h>

float funkcija(int *niza,int i,int n){
    if(n==1){
        return niza[i];
    }
    if(i<n-1) return niza[i]+1/funkcija(niza,i+1,n);
    else return niza[n-1];
}

int main(){
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    printf("%f",funkcija(niza,0,n));
}