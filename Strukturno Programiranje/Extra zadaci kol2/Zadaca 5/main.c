#include <stdio.h>
#include <string.h>
#include <ctype.h>

// ne menuvaj ovde
void wf() {
    FILE *f = fopen("livce.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wf();
	FILE *fp=fopen("livce.txt","r");
	double dobivka;
	char shifra[9],maxShifra[9];
	int tip,maxtip;
	double koef,maxKoef=0; 
	fscanf(fp,"%lf",&dobivka);
	while(fscanf(fp,"%s",shifra)!=EOF){
	    fscanf(fp,"%d %lf",&tip,&koef);
	    if(koef>maxKoef){
	        maxKoef=koef;
	        strcpy(maxShifra,shifra);
	        maxtip=tip;
	    }
	    dobivka*=koef;
	}
	fclose(fp);
    printf("%s %d %.2lf\n%.2lf",maxShifra,maxtip,maxKoef,dobivka);
	return 0;    

}