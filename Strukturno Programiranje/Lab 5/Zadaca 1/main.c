#include <stdio.h>
#include <math.h>
int main(){
    int n,dMin=0;
    scanf("%d",&n);
    int d,trojka[3];
    scanf("%d%d%d",&trojka[0],&trojka[1],&trojka[2]);
    d=abs(trojka[0]-trojka[1])+abs(trojka[1]-trojka[2]);
    dMin=d;
    for(int i=1;i<n;i++){
        scanf("%d%d%d",&trojka[0],&trojka[1],&trojka[2]);
        d=abs(trojka[0]-trojka[1])+abs(trojka[1]-trojka[2]);
        if(d<dMin)
        dMin=d;
    }
    printf("%d",dMin);
    
    return 0;
}