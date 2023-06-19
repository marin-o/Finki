#include <stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int m1[n][n*2];
    int m2[2*n][n];
    for(int i=0;i<n;i++){
        for(int j=0;j<2*n;j++){
            scanf("%d",&m1[i][j]);
        }
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            m2[i][j]=m1[i][j];
            m2[i+n][j]=m1[i][j+n];
        }
    }
    for(int i=0;i<n*2;i++){
        for(int j=0;j<n;j++){
            printf("%d ",m2[i][j]);
        }
        printf("\n");
    }
    
}