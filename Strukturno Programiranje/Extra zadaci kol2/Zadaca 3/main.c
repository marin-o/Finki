#include <stdio.h>

int sum_pos(int *niza,int indeks,int n){
    int sum=0;
    if (indeks>n) return 0;
    for(int i=indeks;i<n;i++){
        sum+=*(niza+i);
    }
    return sum;
}

int main(){
    int n,ind;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",niza+i);
    }
    scanf("%d",&ind);
    printf("%d",sum_pos(niza,ind,n));
    return 0;
}