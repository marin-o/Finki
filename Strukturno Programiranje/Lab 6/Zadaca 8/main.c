#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
        if(niza[i]%2==0) niza[i]++;
        else niza[i]--;
    }
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    return 0;
}