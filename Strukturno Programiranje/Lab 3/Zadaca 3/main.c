#include <stdio.h>

int main(){

    int n,count5=0,kolega=0,i=4,j=1000;
    scanf("%4d",&n);
    int temp;
    temp=n;
    while(temp!=0)
    {
        if(temp%10==5)
        count5++;
        temp/=10;
    };
    temp=n;
    if(count5>=2)
    {
        while(i!=0){
        if(temp/j==5)
        kolega=kolega*10+(temp/j+1);
        else kolega=kolega*10+temp/j;
        temp%=j;
        i--;
        j/=10;
        };
        float razlika=(n-kolega)*-1;
        if(n>kolega)
        printf("%.4f%%",razlika/n*100);
        else if(kolega>n)
        printf("%.4f%%",razlika/n*100);
    }
    else printf("Error");


    return 0;
}
