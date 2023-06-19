#include <stdio.h>
int main(){
    int price, balance;
    float priceWithTax;
    scanf("%d%d",&balance,&price);
    priceWithTax=price+(18/100.00)*price;
    balance=balance-priceWithTax;
    printf("%d",balance>0);
    return 0;
}