#include <stdio.h>
int main(){
    
    int m,n;
    scanf("%d%d",&m,&n);
    int temp;
    int obraten,counter=0;
    for(int i=m;i<=n;i++)
    {
        temp=i;
        obraten=0;
        while(temp!=0)
        {
            obraten=obraten*10+temp%10;
            temp/=10;
        }
        if(i+obraten<=2*i){
            printf("%d\n",i);
            counter++;
        }
    }
    printf("Vkupno: %d",counter);
    
    return 0;
}