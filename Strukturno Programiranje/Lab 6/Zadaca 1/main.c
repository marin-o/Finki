#include <stdio.h>
#include <stdlib.h>

int main()
{
    int mat[100][100];
    int mat2[100][100];
    int m,n;
    int i,j;
    scanf("%d%d",&m,&n);
    if(m>1 && n<=100)
    {
        for(i=0;i<m;i++)
        {
            for(j=0;j<n;j++)
            {
                scanf("%d",&mat[i][j]);
                mat2[i][j]=mat[i][j];
            }
        }
    }
    
     for(i=0;i<m;i++)
        {
            for(j=0;j<n;j++)
            {
                if(mat2[i][j]==0)
                {
                for(int a=i-1;a<=i+1;a++)
                {
                    for(int b=j-1;b<=j+1;b++)
                    {
                    if(mat[a][b]==1)
                    {
                    mat2[i][j]+=1;
                    }
                    }
                }
                }
            }
        }

        for(i=0;i<m;i++)
        {
            for(j=0;j<n;j++)
            {
                if(mat[i][j]==1)
                {
                printf("* ");
                }
                else
                {
                printf("%d ",mat2[i][j]);
                }
            }
            printf("\n");
        }
    




    return 0;
}