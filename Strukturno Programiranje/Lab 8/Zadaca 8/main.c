#include <stdio.h>
void printNumber(int n, int c){
    if(c==0){
        printf("%d\n",n);
        return;
    }
    printf("%d ",n-c);
    printNumber(n,c-1);
}
void triagolnik(int n){
    int c=n-1;
    printNumber(n,c);
    if(c==0){
        return;
    }
    triagolnik(n-1);
}

int main(){
    int n;
    scanf("%d",&n);
    triagolnik(n);
    return 0;
}