#include <stdio.h>
int main(){
    int a,b; 
    int temp;
    int flag;
    scanf("%d%d",&a,&b);
    while(a){
        temp=b;
        while(temp){
            flag=0;
            if(a%10==temp%10){
                flag=1;
                break;
            }
            else {
                temp/=10;
            }
        }
        if(flag==0) {printf("NE"); return 0;}
        a/=10;
    }
    if(flag) printf("DA"); 
    //else printf("NE");
    return 0;
}