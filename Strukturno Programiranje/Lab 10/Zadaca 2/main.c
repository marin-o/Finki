#include <stdio.h>

void writeToFile() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
    
}

void printFile() {

    FILE *f=fopen("output.txt","r");
    char line[100];
    while(!feof(f)){
        fgets(line,100,f);
        if (feof(f))
            break;
        printf("%s",line);
    }
    fclose(f);
}

int main() {
    writeToFile();
    FILE *datI=fopen("input.txt","r");
    int n;
    fscanf(datI,"%d",&n);
    
    int zbir=0;
    int a[n][n];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            fscanf(datI, "%d",&a[i][j]);
            if(i==j){
            zbir+=a[i][j];
            }
        }
        
    }
    fclose(datI);
    FILE *datO=fopen("output.txt","w");
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(j>i){
                fprintf(datO,"%03d ",zbir);
            }
            else fprintf(datO, "    ");
        }
        fprintf(datO,"\n");
    }
    fclose(datO);
    printFile();
    return 0;
}