#include <stdio.h>
#include <stdlib.h>
int main(){
    int m,n;
    int sum1,sum2;
    scanf("%d%d",&m,&n);
    int mat[m][n];
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&mat[i][j]);
        }
    }
    for(int i=0;i<m;i++){
        sum1=sum2=0;
        for(int j=0;j<n;j++){
            if(j<n/2){
                sum1+=mat[i][j];
            }
            else if(j>=n-n/2)sum2+=mat[i][j];
        }
        int razlika=abs(sum1-sum2);
        if(n%2==0){
            mat[i][n/2-1]=mat[i][n/2]=razlika;
        }
        else mat[i][n/2]=razlika;
    }
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            printf("%d ",mat[i][j]);
        }
        printf("\n");
    }
}