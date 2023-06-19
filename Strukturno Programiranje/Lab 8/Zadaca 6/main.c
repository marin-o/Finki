#include <stdio.h>
int funkcija(int a, int b){
    if(a==b){
        return a;
    }
    else if(a>b){
        return funkcija(a-b,b);
    }
    else return funkcija(a,b-a);
}
int main()
{
    int a,b;
    scanf("%d%d",&a,&b);
    printf("%d",funkcija(a,b));
    return 0;
}