#include <stdio.h>
void elka(int n){
    for(int i=0;i<=n/2;i++){
        for(int j=0;j<n/2-i;j++) printf(" ");
        for(int k=0;k<2*i+1;k++) printf("*");
        printf("\n");
    }
    
}
int main()
{
    int n;
    scanf("%d",&n);
    elka(n);
    return 0;
}