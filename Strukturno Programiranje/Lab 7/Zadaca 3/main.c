#include <stdio.h>

void read(int m, int n,int a[m][n]){
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            scanf("%d",&a[i][j]);
}

int main()
{
   int m,n;
   scanf("%d%d",&m,&n);
   int matrix[m][n];
   read(m,n,matrix);
   
   int sum;
   int minSum=2147483647;
   int index;
   
   for(int j=0;j<n;j++){
       sum=0;
       for(int i=0;i<m;i++){
           sum+=matrix[i][j];
       }
       if(sum<minSum){
           minSum=sum;
           index=j;
       }
   }
   printf("%d",index);
}