#include <stdio.h>
#include <string.h>
#define MAX 100

//ne menuvaj!
void wtf() {
    FILE *f = fopen("broevi.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int main()
{
    wtf();
    FILE *fp=fopen("broevi.txt","r");
    int niza;
    int broj=1;
    int cifra=0;
    int najgolem;
    while(1){
        fscanf(fp,"%d",&niza);
        if(!niza) break;
        cifra=0;
        for(int i=0;i<niza;i++){
            fscanf(fp,"%d",&broj);
            int temp=broj;
            while(temp>9){
                temp/=10;
            }
            if(temp>cifra){
            cifra=temp;
            najgolem=broj;
            }
            
        }
        printf("%d\n",najgolem);
    }
    
	
}