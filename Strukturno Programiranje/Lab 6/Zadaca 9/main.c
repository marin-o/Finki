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
    
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
        if(niza[i]>niza[j]){
        int temp=niza[i];
        niza[i]=niza[j];
        niza[j]=temp;
        }
    }
    }
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    return 0;
}