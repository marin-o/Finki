#include <stdio.h>
int main(){
    char notExcl;
    int sum=0,tempSum=0;
    do{
        scanf("%c",&notExcl);
        if(notExcl>='0' && notExcl<='9'){
        switch(notExcl){
            case '0': tempSum=tempSum*10+0;break;
            case '1': tempSum=tempSum*10+1;break;
            case '2': tempSum=tempSum*10+2;break;
            case '3': tempSum=tempSum*10+3;break;
            case '4': tempSum=tempSum*10+4;break;
            case '5': tempSum=tempSum*10+5;break;
            case '6': tempSum=tempSum*10+6;break;
            case '7': tempSum=tempSum*10+7;break;
            case '8': tempSum=tempSum*10+8;break;
            case '9': tempSum=tempSum*10+9;break;
        }
        
        
        }
        else {sum+=tempSum;tempSum=0;}
    }while(notExcl!='!');
    printf("%d",sum);
        
        
    
    return 0;
}