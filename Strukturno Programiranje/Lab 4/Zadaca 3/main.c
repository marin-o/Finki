#include <stdio.h>

int main(){
    
    int a,b;
    int temp;
    int sum,count=0;
    scanf("%d%d", &a,&b);
    for(int i=a;i<=b;i++)
    {
        sum=0;
        temp=i;
        while(temp!=0){
            if(temp%10%2==0)
            sum+=temp%10;
            temp/=10;
        }
        if(sum>0 && sum%4==0)
        {
            count++;
            printf("%d\n",i);
        }
        
    }
    printf("Vkupno: %d",count);
    return 0;
}