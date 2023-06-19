#include <stdio.h>
int main(){
    
    int n;
    scanf("%d",&n);
    char ddvType;
    float ddv=0,price;
    float ddvBack;
    for(int i=1;i<=n;i++)
    {
        scanf("%f %c",&price,&ddvType);
        if(ddvType=='A')
        ddv+=price*(18.0/100.0);
        else if(ddvType=='B')
        ddv+=price*(5.0/100.0);
        else if(ddvType=='V')
        ddv+=price*(0.0/100.0);
    }
    ddvBack=15.0/100.0*ddv;
    printf("Total tax return is: %.2f",ddvBack);
    return 0;
}