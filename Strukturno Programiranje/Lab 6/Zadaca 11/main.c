#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    int s1,s2,s3,s4;
    s1=s2=s3=s4;
    scanf("%d",&n);
    int m[n][n];
    int m2[2][2]={0};
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            scanf("%d",&m[i][j]);
    
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(i<n/2&&j<n/2){
                m2[0][0]+=m[i][j];
            }
            else if(i<n/2 && j>n/2){
                m2[0][1]+=m[i][j];
            }
            else if(i>n/2 && j<n/2){
                m2[1][0]+=m[i][j];
            }
            else if(i>n/2 && j>n/2){
                m2[1][1]+=m[i][j];
            }
        }
    }
    for(int i=0;i<2;i++){
        for(int j=0;j<2;j++)
        printf("%d ",m2[i][j]);
        printf("\n");
    }
    
    return 0;
}