
#include <stdio.h>

int main()
{
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    printf("\n");
    
    //************************ flip_1 
    
    for(int i=0;i<n/2;i++){
        int temp=niza[i];
        niza[i]=niza[n-i-1];
        niza[n-i-1]=temp;
    }
    
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    printf("\n");
    
    //************************ cut_every_other
    
    for(int i=0;i<n/2;i++){
        for(int j=i+1;j<n;j++)
            niza[j]=niza[j+1];
    }
    
    for(int i=1;i<=n-n/2;i++){
        printf("%d ",niza[i-1]);
    }
    printf("\n");
    
    //************************ flip_2
    
    for(int i=0;i<(n-n/2)/2;i++){
        int temp=niza[i];
        niza[i]=niza[n-n/2-i-1];
        niza[n-n/2-i-1]=temp;
    }
    
    for(int i=1;i<=n-n/2;i++){
        printf("%d ",niza[i-1]);
    }
    printf("\n");
    return 0;
}
