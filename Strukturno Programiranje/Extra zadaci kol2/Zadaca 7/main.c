#include <stdio.h>
#include <math.h>
int main(){
    int m,n;
    float najR;
    scanf("%d%d",&m,&n);
    int mat[m][n];
    int niza[m];
    float aS;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&mat[i][j]);
        }
    }
    
    for(int i=0;i<m;i++){
        najR=0;
        aS=0.0;
        int j;
        for(j=0;j<n;j++){
            aS+=mat[i][j];
        }
        aS/=j;
        for(j=0;j<n;j++){
            if(j==0){
                najR=fabs(aS-(float)mat[i][j]);
                niza[i]=mat[i][j];
            }
            if(fabs(aS-(float)mat[i][j])>najR){
                najR=fabs(aS-(float)mat[i][j]);
                niza[i]=mat[i][j];
            }
        }
    }
    for(int i=0;i<m;i++){
        printf("%d ",niza[i]);
    }
}