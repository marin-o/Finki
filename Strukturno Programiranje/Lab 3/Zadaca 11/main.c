#include <stdio.h>
int main()
{
    
    int broj;
    scanf("%d", &broj);
    printf("0%d/%03d-%03d",broj/1000000,broj%1000000/1000,broj%1000);
    if(broj/1000000==70 || broj/1000000==71||broj/1000000==72)
    printf(" T-mobile");
    else if(broj/1000000==75||broj/1000000==76||broj/1000000==77||broj/1000000==78)
    printf(" A1");
    else printf(" LycaMobile");
    
    return 0;
}