#include <stdio.h>
int main(){
    int a;
    int temp,flag; 
    int alternator;
    
    while(scanf("%d",&a)){
        if(a<10) continue;
        else{
            temp=a;
            flag=1;
            if(temp%10<5 && temp/10%10>=5) {alternator=0; temp/=10;}
            else if(temp%10>=5 && temp/10%10<5) {alternator=1; temp/=10;}
            else continue;
            while(temp){
                
                if(alternator==0){
                   if(temp<10){flag=1;break;}
                    else if(temp%10>=5 && temp/10%10<5) {alternator=1;temp/=10; continue;}
                    else {flag=0;break;}
                }
                if(alternator==1){
                    if(temp<10){flag=1;break;}
                    else if(temp%10<5 && temp/10%10>=5) {alternator=0;temp/=10;continue;}
                    else{flag=0;break;}
                    
                }
            }
            if(flag){
                printf("%d\n",a);
            }
        }
    }
    return 0;
}