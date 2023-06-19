#include <stdio.h>
int main(){
    
    int n;
    scanf("%d",&n);
    if(n>2)
    {
        for(int i=1;i<=n;i++){
            for(int j=n;j>=1;j--){
                if(j==1 || j==i || i==n)
                    printf("*");
                else printf(" ");
            }   
            printf("\n");
        }
    }
    else printf("Nevaliden vlez");
    
    return 0;
}