#include <stdio.h>
int main(){
    int m;
    scanf("%d",&m);
    for(int i=1;i<=m;i++){
        for(int j=1;j<=m;j++){
        if(j==1||j==m)
        printf("%%");
        else if((j>1 && j<m) && (i==1 || i==m))
        printf("@");
        else printf(".");
        }
    printf("\n");
    }
    return 0;
}