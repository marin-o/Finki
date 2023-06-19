

#include <stdio.h>
int main(){
    
    int maticen;
    scanf("%d", &maticen);
    int username,den,mesec,godina;
    den=maticen/100000;
    mesec=maticen/1000%100;
    godina=maticen%1000+1000;
    username=den*100+mesec*1000+godina;
    if(username>=10000)
    printf("%d",username);
    else if(godina<=1960)
    {username=70000+username;
    printf("%d",username);}
    else if(godina>1960 && godina<1980)
    {username=80000+username;
    printf("%d",username);}
    else if(godina>1980 && godina<2000)
    {username=90000+username;
    printf("%d",username);}
    
    
    return 0;
}