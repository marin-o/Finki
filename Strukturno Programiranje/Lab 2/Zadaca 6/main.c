#include <stdio.h>
int main(){
    
    int a;
    scanf("%5d",&a);
    printf("%d %d %d",a/10000+a%10,a/1000%10+a%100/10,a/100%10);
    
    return 0;
}