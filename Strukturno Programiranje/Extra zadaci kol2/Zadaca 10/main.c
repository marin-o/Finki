#include <stdio.h>
#include <ctype.h>


void wtf() {
    FILE *f = fopen("podatoci.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main(){
    wtf();
    char z1,z2,niza[80];
    z1=getchar();
    z1=getchar();
    z2=getchar();
    
    FILE *fp=fopen("podatoci.txt","r");
    while(fgets(niza,80,fp)){
        for(int i=0;niza[i]!='\n';i++){
            if(niza[i]==z1){
                for(i=i+1;niza[i]!=z2;i++){
                    printf("%c",niza[i]);
                }
                break;
            }
        }
        printf("\n");
    }
}