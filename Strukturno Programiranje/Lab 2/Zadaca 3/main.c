#include <stdio.h>
int main(){
    
    int x,y,z;
    scanf("%d%d%d", &x,&y,&z);
    printf("%d", ++x - --y + z++ + (x>z));
    printf(" %d", ++x - --y + z++ + x>z);
    return 0;
}