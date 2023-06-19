#include <stdio.h>
#include <ctype.h>
#include <string.h>

void writeToFile() {
  FILE *f = fopen("text.txt", "w");
  char c;
  while((c = getchar()) != '#') {
    fputc(c, f);
  }
  fclose(f);
}

int main() {
    
  writeToFile();
  int counter=0;
  char c;
  FILE *fp=fopen("text.txt","r");
  char prevL=fgetc(fp);
  while((c=fgetc(fp))!=EOF){
      prevL=tolower(prevL);
      c=tolower(c);
      if((prevL=='a' || prevL=='e' || prevL=='i' || prevL=='o' || prevL=='u') && (c=='a' || c=='e' || c=='i' || c=='o' || c=='u')){
          counter++;
          printf("%c%c\n",prevL,c);
      }
      prevL=c;
  }
  fclose(fp);
  printf("%d",counter);


  return 0;
}
