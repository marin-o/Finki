#include <stdio.h>

int main(){
    int x,y,z;
    scanf("%d%d%d", &x,&y,&z);
    y = ++x && (z+5 || --y);
    printf("%d %d %d", x,y,z);
    z = z-- || (x>y) && (x>z);
     printf("\n%d %d %d", x,y,z);
    return 0;
}