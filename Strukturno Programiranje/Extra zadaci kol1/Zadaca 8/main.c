#include <stdio.h>


int main()
{
    int n, x,tempi,tempx,flag;
    scanf("%d%d", &n, &x);
    for(int i=n-1;i>=0;i--){
        for(tempi=i;tempi>=0;tempi/=10){
            flag=0;
            for(tempx=x;tempx>0;tempx/=10){
                if(tempi%10==tempx%10){
                    flag=1;
                    break;
                }
            }
            if(flag || !tempi){
                break;
            }
        }
        if(!flag || !i){
            printf("%d",i);
            break;
        }
    }
    return 0;
}