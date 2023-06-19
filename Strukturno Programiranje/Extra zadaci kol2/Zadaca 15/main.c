#include <stdio.h>

int poramnet(int a){
    int poramnuvanje=0;
    if(a==0){
        return 0;
    }
    if(a%10==9){
        return poramnet(a/10)*10+7;
    }
    else {
        return poramnet(a/10)*10+a%10;
    }
}

int main(){
    int n;
    int niza[100];
    int brojac=0;
    while(scanf("%d",&n)){
        brojac++;
        niza[brojac-1]=poramnet(n);
    }
    for(int i=0;i<brojac;i++){
        for(int j=i+1;j<brojac;j++){
            if(niza[j]<niza[i]){
                int temp=niza[i];
                niza[i]=niza[j];
                niza[j]=temp;
            }
        }
    }
    int broevi=5;
    if(brojac<5){
        broevi=brojac;
    }
    for(int i=0;i<broevi;i++){
        printf("%d ",niza[i]);
    }
}