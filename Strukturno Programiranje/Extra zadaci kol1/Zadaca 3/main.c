#include <stdio.h>
int main(){
    int n,sumDel=1,maxDel=0,maxDel_broj;
    scanf("%d",&n);
    for(int i=n-1;i>0;i--){
        sumDel=1;
        for(int j=2;j<=i/2;j++){
            if(i%j==0)
            sumDel+=j;
        }
        if(sumDel>maxDel){
            maxDel_broj=i;
            maxDel=sumDel;
        }
        
    }
    printf("%d",maxDel_broj);
    return 0;
}