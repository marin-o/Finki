#include <stdio.h>



int main(){
    int n,m;
    int i1,i2;
    scanf("%d%d",&n,&m);
    int mat[n][m];
    int sum1,sum2,sum3,sum4;
    sum1=sum2=sum3=sum4=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            scanf("%d",&mat[i][j]);
        }
    }
    scanf("%d%d",&i1,&i2);
    
    for(int i=0;i<i1;i++){
        for(int j=i2;j<m;j++){
            sum1+=mat[i][j];
        }
    }
    for(int i=0;i<i1;i++){
        for(int j=0;j<i2;j++){
            sum2+=mat[i][j];
        }
    }
    for(int i=i1;i<n;i++){
        for(int j=0;j<i2;j++){
            sum3+=mat[i][j];
        }
    }
    for(int i=i1;i<n;i++){
        for(int j=i2;j<m;j++){
            sum4+=mat[i][j];
        }
    }
    printf("%d %d %d %d",sum1,sum2,sum3,sum4);
    return 0;
}