#include <stdio.h>
int main(){
    
    int n,m,sum,maxSum=0;
    char ddvType;
    float ddv=0,price;
    float ddvBack,maxTax=0;
    scanf("%d",&m);
    for(int i=1;i<=m;i++)
    {
        scanf("%d",&n);
        ddv=0;
        sum=0;
        for(int j=1;j<=n;j++){
            scanf("%f %c",&price,&ddvType);
            sum+=price;
            if(ddvType=='A')
                ddv+=price*(18.0/100.0);
            else if(ddvType=='B')
                ddv+=price*(5.0/100.0);
            else if(ddvType=='V')
                ddv+=price*(0.0/100.0);
            
        }
        if(sum>30000){
            printf("Sum %d is bigger than 30000\n",sum);
        }
        else{
            ddvBack=ddv*(15.0/100.0);
            printf("Total tax return is: %.2f\n",ddvBack); 
            if(ddvBack>maxTax){
            maxTax=ddvBack;
            maxSum=sum;
            }
            
        }
    }
    printf("Max amount of reciept: %d. Max tax return for reciepts: %.2f",maxSum,maxTax);
    
    return 0;
}