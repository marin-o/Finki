#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,counter=0,longest=0;
    scanf("%d",&n);
    int mat[n][n];
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            scanf("%d",&mat[i][j]);
        }
    for(int i=0;i<n;i++){
        counter=0;
        for(int j=1;j<n;j++){
            if(mat[i][j]>mat[i][j-1]) counter++;
        }
        if(longest<counter+1) longest=counter+1;
    }
    printf("%d",longest);
    return 0;
}