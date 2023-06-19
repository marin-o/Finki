#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,dif1=0,dif2=0;
    scanf("%d",&n);
    int mat[n][n];
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            scanf("%d",&mat[i][j]);
        }
    for(int i=0;i<n;i++){
        dif1+=mat[i][i];
        dif2+=mat[i][n-i-1];
    }
    printf("%d\n",dif1-dif2);
    dif1=dif2=0;
    for(int i=0;i<n;i++){
        dif1+=mat[i][0];
        dif2+=mat[i][n-1];
    }
    printf("%d\n",dif1-dif2);
    return 0;
}