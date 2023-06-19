#include <stdio.h>

int main()
{
    int broj;
    int temp,temp1;
    scanf("%d",&broj);
    if(broj<0) broj-=broj;
    int i=0;
    temp1=broj;
    while(temp1!=0)
    {
        i++;
        temp1/=10;
    };
    if(i==5){
    temp=broj;
    int palindrom=0;
    for(temp;temp!=0;temp/=10)
    {
        palindrom=palindrom*10+temp%10;
    }
    if(palindrom==broj)
    printf("Palindrom");
    else printf("Ne e palindrom");}
    else printf("Nevaliden vlez");
    return 0;
}