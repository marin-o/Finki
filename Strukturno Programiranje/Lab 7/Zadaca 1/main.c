#include <stdio.h>
void print(int niza[],int n){
    for(int i=0;i<n;i++){
        printf("%d ",niza[i]);
    }
    printf("\n");
}
int main()
{
    int n;
    scanf("%d",&n);
    int niza[n];
    for(int i=0;i<n;i++){
        scanf("%d",&niza[i]);
    }
    
    print(niza,n);
    
    //************************ flip_1 
    
    for(int i=0;i<n/2;i++){
        int temp=niza[i];
        niza[i]=niza[n-i-1];
        niza[n-i-1]=temp;
    }
    
    print(niza,n);
    
    //************************ cut_every_other
    
    for(int i=0;i<n/2;i++){
        for(int j=i+1;j<n;j++)
            niza[j]=niza[j+1];
    }
    
    print(niza,n-n/2);
    
    //************************ flip_2
    
    for(int i=0;i<(n-n/2)/2;i++){
        int temp=niza[i];
        niza[i]=niza[n-n/2-i-1];
        niza[n-n/2-i-1]=temp;
    }
    
    print(niza,n-n/2);
    return 0;
}
