#include <stdio.h>
int main(){
    int n,m,cifri=0,divv=1;
    int temp;
    scanf("%d%d",&n,&m);
    if(n<=0 || m<=0)
    printf("Invalid input");
    else if(n<m)
    {
        temp=n;
        n=m;
        m=temp;
        temp=n;
        while(temp){
            cifri+=temp/10%10*divv;
            temp/=100;
            divv*=10;
        }
        if(cifri==m) printf("PAREN");
        else printf("NE");
    }
    else{
        temp=n;
        while(temp){
            cifri+=temp/10%10*divv;
            temp/=100;
            divv*=10;
        }
        if(cifri==m) printf("PAREN");
        else printf("NE");
    }
        
        
    
    return 0;
}