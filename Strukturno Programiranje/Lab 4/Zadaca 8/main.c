#include <stdio.h>
int main(){
    
    int n;
    scanf("%d",&n);
    if(n>=5){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(j==n-i+1 || j==1 || j==n  || i==1 || i==n)
                printf("*");
                else printf(" ");
            }
            printf("\n");
        }
    }
    else printf("Error");
    return 0;
}