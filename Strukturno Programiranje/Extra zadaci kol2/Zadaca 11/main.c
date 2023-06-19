#include <stdio.h>
#include <ctype.h>
void wtf() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wtf();
    FILE *fp = fopen("input.txt", "r");
    char niza[100];
    int brojki[100];
    
    while(fgets(niza,1000,fp)){
        int counter=0;
        for(int i=0;niza[i]!='\n';i++){
            if(isdigit(niza[i])){
                brojki[counter]=niza[i]-'0';
                counter++;
            }
        }
        for(int i=0;i<counter;i++){
            for(int j=i+1;j<counter;j++){
                if(brojki[i]>brojki[j]){
                    int temp=brojki[i];
                    brojki[i]=brojki[j];
                    brojki[j]=temp;
                }
            }
        }
        printf("%d:",counter);
        for(int i=0;i<counter;i++) {
            printf("%d",brojki[i]);
        }
        printf("\n");
        
    }
    return 0;
}
