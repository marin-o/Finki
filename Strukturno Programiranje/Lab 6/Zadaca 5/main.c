#include <stdio.h>

int main()
{
   int money,help;
    scanf("%d",&money);
    int _5000, _1000,_500, _100, _50, _10, _5, _2, _1;
    _5000=(money-money%1000)/5000;
    _1000=(money-(_5000*5000))/1000;
    help=money%1000;
    _500=help/500;
    _100=(help-(_500*500))/100;
    _50=help%100/50;
    _10=(help%100-_50*50)/10;
    _5=help%10/5;
    _2=(help%10-_5*5)/2;
    _1=(help%10-_5*5-_2*2)%10;
    printf("%d*5000\n%d*1000\n%d*500\n%d*100\n%d*50\n%d*10\n%d*5\n%d*2\n%d*1",_5000,_1000,_500,_100,_50,_10,_5,_2,_1);
    
    return 0;
}