#include <stdio.h>

int main(){
    
    int a,p,counter=0;
    int temp;
    int sum;
    scanf("%d%d",&a,&p);
    for (int i=a;i>0;i--)
    {
        temp=i;
        sum=0;
        while(temp!=0)
        {
            sum+=temp%10;
            temp/=10;
        }
        if(sum==(float)((p/100.0)*i))
        {
            counter++;
            printf("%d\n",i);
        }
        if (counter==5)
        break;
    }
    if(counter==0)
    printf("NEMA");
    

    return 0;
}