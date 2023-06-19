#include <stdio.h>
int main(){
    
    int balance;
    int a,b,c,d,e;
    scanf("%d%d%d%d%d%d", &balance,&a,&b,&c,&d,&e);
    printf("%d", (balance+a+b+c+d+e)>0);
    
    return 0;
}