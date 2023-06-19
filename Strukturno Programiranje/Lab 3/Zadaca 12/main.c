#include <stdio.h>

int main()
{
    int N;
    scanf("%d",&N);
    if(N%3==0 && N%5==0)
    printf("Tik - Tak");
    else if(N%3==0)
    printf("Tik");
    else if(N%5==0)
    printf("Tak");
    else printf("Losh Broj");
    
    return 0;
}