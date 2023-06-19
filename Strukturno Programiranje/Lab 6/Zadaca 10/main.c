#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    int k;
    scanf("%d",&k);
    for(int i=0;i<n;i++){
        for(int j=0;j<n-1;j++){
            if(niza[j+1]<k && niza[j]>=k){
                int temp=niza[j];
                niza[j]=niza[j+1];
                niza[j+1]=temp;
            }
        }
    }
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    return 0;
}