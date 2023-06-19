#include <stdio.h>

int main(){
    
    int n,obraten,temp,cifri=0;
    scanf("%d",&n);
    if(n<9) printf("Brojot ne e validen");
    else{
        for(int i=n-1;i>=9;i--)
        {
            temp=i;
            obraten=0;
            cifri=0;
            while (temp){  
                obraten=obraten*10+temp%10;
                temp/=10;
                cifri++;
            }
            if(obraten%cifri==0)
            {
                printf("%d",i);
                
                break;
            }
            
        }
        
        
        
        
    }
    
    return 0;
}