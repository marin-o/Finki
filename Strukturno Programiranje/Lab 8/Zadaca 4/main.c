#include <stdio.h>
int funkcija(int n){
    if(n!=0){
        return 1*n%10*funkcija(n/100);
    }
    else return 1;
}
int main(){
    
    int n;
    scanf("%d",&n);
    printf("%d",funkcija(n/10));
    
    return 0;
}