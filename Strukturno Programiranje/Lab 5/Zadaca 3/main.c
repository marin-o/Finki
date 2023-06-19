#include <stdio.h>
int main(){
    int a,brojac=1,joker=0;;
    scanf("%d",&a);
    while(a<91 && a>0){
        if(brojac<=7){
            joker=joker*10+a%10;
        }
        else joker+=a;
        brojac++;
        scanf("%d",&a);
    }
    printf("%d",joker);
    return 0;
}