#include <stdio.h>
int main(){
    int z,counter1=0,counter=0;
    int a,b;
    scanf("%d%d%d",&z,&a,&b);
    while(a!=0 || b!=0){
        counter++;
        if(z==a+b)
        counter1++;
        scanf("%d%d",&a,&b);
    }
    printf("Vnesovte %d parovi od broevi chij zbir e %d\nProcentot na parovi so zbir %d e %.2f%%",counter1,z,z,(float)counter1/counter*100);    
        
        
    
    return 0;
}