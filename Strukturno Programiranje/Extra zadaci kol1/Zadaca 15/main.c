#include <stdio.h>
int main(){
    int n,brPad=0,brPol=0,indPad=0,indPol=0;
    float srPad=0,srPol=0;
    scanf("%d",&n);
    if(n>1000){
        printf("Nevaliden broj na studenti");
    }
    else{
        int studenti[n],padnati[n],polozeni[n];
        for(int i=0;i<n;i++){
            scanf("%d",&studenti[i]);
            if(studenti[i]<50){
                padnati[indPad]=studenti[i];
                brPad++;
                indPad++;
            }
            if(studenti[i]>=50){
                polozeni[indPol]=studenti[i];
                brPol++;
                indPol++;
            }
        }
        for(int i=0;i<brPad;i++){
            srPad+=padnati[i];
        }
        for(int i=0;i<brPol;i++){
            srPol+=polozeni[i];
        }
        printf("Sredna vrednost na padnati %.2f\n",srPad/brPad);
        for(int i=0;i<brPad;i++){
            printf("%d ",padnati[i]);
        }
        printf("\nSredna vrednost na polozeni %.2f\n",srPol/brPol);
        for(int i=0;i<brPol;i++){
            printf("%d ",polozeni[i]);
        }
    }
    return 0;
}