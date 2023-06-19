#include <stdio.h>
int main(){
    int n,groupcounter,group1,group2,group3;
    scanf("%d",&n);
    int studenti[n];
    int grupa1[n],grupa2[n],grupa3[n];
    if(n>1000){
        printf("Nevaliden broj na studenti");
    }
    else{
        
        for(int i=0;i<n;i++){
            scanf("%d",&studenti[i]);
        }
        groupcounter=0;
        for(int i=0;i<n;i++){
            if(studenti[i]%10>=0 && studenti[i]%10<=2){
                grupa1[groupcounter]=studenti[i];
                groupcounter++;
            }
        }
        group1=groupcounter;
        groupcounter=0;
        for(int i=0;i<n;i++){
            if(studenti[i]%10>=3 && studenti[i]%10<=5){
                grupa2[groupcounter]=studenti[i];
                groupcounter++;
            }
        }
        group2=groupcounter;
        groupcounter=0;
        for(int i=0;i<n;i++){
            if(studenti[i]%10>=6 && studenti[i]%10<=9){
                grupa3[groupcounter]=studenti[i];
                groupcounter++;
            }
        }
        group3=groupcounter;
    }
    printf("Grupa 1\n");
    for(int i=0;i<group1;i++){
            printf("%d ",grupa1[i]);
    }
    printf("\nGrupa 2\n");
    for(int i=0;i<group2;i++){
            printf("%d ",grupa2[i]);
    }
    printf("\nGrupa 3\n");
    for(int i=0;i<group3;i++){
            printf("%d ",grupa3[i]);
    }
    return 0;
    
}