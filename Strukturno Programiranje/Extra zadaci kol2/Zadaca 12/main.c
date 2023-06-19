#include <stdio.h>
int BrojPozitivni(int niza[],int n);

int main(){
    int n;
    scanf("%d", &n);
    int a[n];
    for(int i = 0; i < n; i++)
    {
        scanf("%d", &a[i]);
    }
    printf("%d", BrojPozitivni(a, n - 1));
    return 0;
}

int BrojPozitivni(int niza[],int n){
    if(n==0){
        if(niza[0]>0){
            return 1;
        }
        else return 0;
    }
    else{
        if(niza[n]>0) return 1+BrojPozitivni(niza,n-1);
        else return 0+BrojPozitivni(niza,n-1);
    }
}