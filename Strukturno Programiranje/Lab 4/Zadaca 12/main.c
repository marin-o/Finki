#include <stdio.h>
int main(){
    
    int n;
    scanf("%d",&n);
    if(n>2)
    {
        for(int i=n;i>=1;i--){
            for(int j=i;j>=1;j--)
            printf("*");
            printf("\n");
        }
    }
    else printf("Nevaliden vlez");
    
    return 0;
}