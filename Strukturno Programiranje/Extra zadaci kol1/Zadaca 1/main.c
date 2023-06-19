#include <stdio.h>

int main(){
    
    int m,n,flag;                               
    scanf("%d%d",&m,&n);
    if(m>n)
    printf("Nevaliden interval");
    else {
    int temp;
    int minblag=n;
    
    for(int i=m;i<=n;i++)
    {
        temp=i;
        for(temp;temp!=0;temp/=10)
        {
            if(temp%10%2!=0)
            {
                flag=0;
            }
            else flag=1;
        }
        if(flag){
            if(i<minblag)
            minblag=i;
            break;
        }
    }
    if(flag)
    printf("%d",minblag);
    else printf("NE");}
    
    return 0;
}