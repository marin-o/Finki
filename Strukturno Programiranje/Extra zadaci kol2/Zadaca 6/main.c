#include <stdio.h>

int main(){
    int m,n;
    scanf("%d%d",&m,&n);
    int mat[m][n];
     for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%d",&mat[i][j]);
        }
    }
    
    int f1=0;
    int kecoj;
    int redkol=0;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            int kec1=mat[i][j];
            int kec2=mat[i][j+1];
            int kec3=mat[i][j+2];
            if(j+1>=n || j+2>=n){
                kec1=kec2=kec3=0;
            }
            if(kec1==1 && kec2==1 && kec3==1){
                    redkol++;
                    break;
            }
            else {
                kec1=kec2=kec3=0;
            }
        }
    }
     for(int j=0;j<n;j++){
        for(int i=0;i<m;i++){
            int kec1=mat[i][j];
            int kec2=mat[i+1][j];
            int kec3=mat[i+2][j];
            if(i+1>=m || i+2>=m){
                kec1=kec2=kec3=0;
            }
            if(kec1==1 && kec2==1 && kec3==1){
                    redkol++;
                    break;
            }
            else {
                kec1=kec2=kec3=0;
            }
        }
    }
    printf("%d",redkol);
}