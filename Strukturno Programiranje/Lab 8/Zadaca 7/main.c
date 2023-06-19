#include <stdio.h>
int sumOfDigits(int number){
    if (number==0){
        return 0;
    }
    return 0+number%10+sumOfDigits(number/10);
}
int main()
{
    int n, i;
    scanf("%d", &n);
    int sum = 0;
    for(i = 0; i < n; ++i){

        int current;
        scanf("%d", &current);
        sum += sumOfDigits(current);
        printf("%d\n", sumOfDigits(current));
    }
    printf("%d", sum);
    return 0;
}