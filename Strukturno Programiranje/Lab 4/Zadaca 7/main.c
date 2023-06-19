#include <stdio.h>
#include <math.h>
int main(){
    
    int n,num,max,min;
    scanf("%d",&n);
    scanf("%d",&num);
    max=min=num;
    for(int i=1;i<n;i++)
    {
        scanf("%d",&num);
        if(num>max) max=num;
        if(num<min) min=num;
    }
    printf("Najgolema razlika: %d",max-min);
return 0;   
}