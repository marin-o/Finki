#include <stdio.h>
#include <string.h>
#include <ctype.h>
// ne menuvaj ovde
void wtf() {
    FILE *f = fopen("dat.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wtf();
	int mindex=0,maxdex=0;
	int najgolemaRazlika=0;
	char najgolemaNiza[100];
	char momentalnaNiza[100];
	FILE *fp=fopen("dat.txt","r");
	while(fgets(momentalnaNiza,100,fp)){
	    for(int i=0;i<strlen(momentalnaNiza);i++){
	        if(isdigit(momentalnaNiza[i])){
	            mindex=i;
	            break;
	        } 
	    }
	    for(int i=strlen(momentalnaNiza)-1;i>=0;i--){
	        if(isdigit(momentalnaNiza[i])){
	            maxdex=i;
	            break;
	        } 
	    }
	    
	    if(maxdex-mindex>=najgolemaRazlika){
	        najgolemaRazlika=maxdex-mindex;
	        int brojac=0;
	        for(int i=mindex;i<=maxdex;i++){
	            najgolemaNiza[brojac]=momentalnaNiza[i];
	            brojac++;
	        }
	        najgolemaNiza[brojac]='\0';
	    }
	}
	puts(najgolemaNiza);
    
    

}