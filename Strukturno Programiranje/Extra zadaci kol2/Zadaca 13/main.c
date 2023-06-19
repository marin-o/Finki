#include <stdio.h>

int main(){
    int x,m,n,sum;
    scanf("%d%d%d",&x,&m,&n);
    int matrica[m][n];
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&matrica[i][j]);
        }
    }
    for(int i=0;i<m;i++){
        sum=0;
        for(int j=0;j<n;j++){
            
            sum+=matrica[i][j];
        }
        if(sum>x)
        for(int j=0;j<n;j++){
            matrica[i][j]=1;
        }
        else if(sum==x)
        for(int j=0;j<n;j++){
            matrica[i][j]=0;
        }
        else if(sum<x)
        for(int j=0;j<n;j++){
            matrica[i][j]=-1;
        }
    }
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            printf("%d ",matrica[i][j]);
        }
        printf("\n");
    }
}