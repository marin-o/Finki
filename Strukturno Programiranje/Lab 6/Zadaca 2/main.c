#include <stdio.h>
int main(){
    int letters[26],brojac=0;
    letters[0]='A';
    for(int i=1;i<26;i++)
    letters[i]=letters[i-1]+1;
    
    int c,k;
    scanf("%c %d",&c,&k);
    printf("%c",letters[(c-'A'+k)%26]);
    
}