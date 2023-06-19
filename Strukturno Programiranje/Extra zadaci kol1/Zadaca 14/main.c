#include <stdio.h>
int main(){
    int a;
    int dSum=0;
    int temp;
    scanf("%d",&a);
    int maxPrevDig=0;
    temp=a;
    while(temp){
        dSum+=temp%10;
        if(temp%10>maxPrevDig)
        maxPrevDig=temp%10;
        temp/=10;
    }
    printf("%d: %d\n",a,dSum);
    while(scanf("%d",&a)){
        dSum=0;
        temp=a;
        while(temp){
            dSum+=temp%10;
            temp/=10;
        }
        dSum+=maxPrevDig;
        printf("%d: %d\n",a,dSum);
        maxPrevDig=0;
        temp=a;
        while(temp){
            if(temp%10>maxPrevDig)
                maxPrevDig=temp%10;
            temp/=10;
            }
    }
    return 0;
}