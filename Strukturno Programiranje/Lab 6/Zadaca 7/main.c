#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,e,k;
    scanf("%d",&n);
    for(int nizi=1;nizi<=n;nizi++){
        scanf("%d",&e);
        int niza[e];
        for(int i=0;i<e;i++){
            scanf("%d",&niza[i]);
        }
        k=0;
        for(int i=0;i<=(e-1)/2;i++){
            if(niza[i]==niza[e-i-1]) k+=2;
        }
        if(e%2==1) --k;
        printf("%.2f%%\n",(float)k/e*100);
    }
    return 0;
}