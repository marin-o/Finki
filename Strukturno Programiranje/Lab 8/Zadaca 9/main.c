#include <stdio.h>
void printNumber(int n){
    if(n>0){
        printNumber(n-1);
        printf("%d",n);
    }
}
void triagolnik(int n){
    if(n>0){
        triagolnik(n-1);
        printNumber(n);
        printf("\n");
    }
}

int main(){
    int n;
    scanf("%d",&n);
    triagolnik(n);
    return 0;
}