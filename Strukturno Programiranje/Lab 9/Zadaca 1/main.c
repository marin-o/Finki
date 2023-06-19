#include<stdio.h>
#include<string.h>
void matematickaOperacija (int a, int b, char operation, int *rezultat){
    switch (operation){
        case '+': *rezultat=a+b; break;
        case '-': *rezultat=a-b; break;
        case '*': *rezultat=a*b; break;
        case '/': *rezultat=a/b; break;
        case '%': *rezultat=a%b; break;
    }
}
int main () {
    
    int a,b,rezultat;
    
    scanf("%d%d", &a, &b);
    
    char *operacii = "+-*/%";
    
    int i;
    for (i=0;i<strlen(operacii);i++) {
        matematickaOperacija(a,b,operacii[i],&rezultat);
        printf("%d %c %d -> %d\n", a, operacii[i], b, rezultat);
    }

	return 0;
}