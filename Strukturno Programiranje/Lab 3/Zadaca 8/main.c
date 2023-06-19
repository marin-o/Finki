#include <stdio.h>

int main(){
    int den,mesec,godina;
    scanf("%d%d%d",&den,&mesec,&godina);
    if(mesec<0 || mesec>12)
    printf("NE");
    else{
    if (mesec>0 && mesec<13)
        if(mesec==2)
            if(den==29)
                if(godina%400==0 || (godina%4==0&godina%100!=0))
                    printf("DA");
                else printf("NE");
            else if(den>0 && den<29)
                    printf("DA");
                else printf("NE");
        else if(mesec==1 || mesec==3 || mesec==5 || mesec==7 || mesec==8||mesec==10||mesec==12)
             if(den>0 && den<32)
                printf("DA");
                else printf("NE");
            else if(mesec==4 || mesec==6 || mesec==9 ||mesec==1)
                if(den>0 && den<31)
                 printf("DA");
                 else printf("NE");
    
    }
    return 0;
}