#include <stdio.h>
int main(){
    int a,temp,maxDig,posRemember;
    int pos, pos0,pos1,pos2,pos3,pos4;
    pos0=pos1=pos2=pos3=pos4=0;
    while(scanf("%d",&a)){
        if(a>99999) continue;
        else{
            temp=a;
            pos=-1;
            maxDig=temp%a;
            posRemember=pos;
            while(temp){
                if(temp%10>maxDig){
                    maxDig=temp%10;
                    pos++;
                    posRemember=pos;
                    temp/=10;
                }
                else {pos++; temp/=10;}
            }
            switch(posRemember){
                case 0: pos0++; break;
                case 1: pos1++; break;
                case 2: pos2++; break;
                case 3: pos3++; break;
                case 4: pos4++; break;
            }
        }
    }
    printf("0: %d\n1: %d\n2: %d\n3: %d\n4: %d",pos0,pos1,pos2,pos3,pos4);
    return 0;
}