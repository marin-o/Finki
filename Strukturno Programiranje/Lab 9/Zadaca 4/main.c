#include<stdio.h>
#include<string.h>
#include<ctype.h>

char *revertString(char *a, char *b, char *comparator){
    for(int i=0;i<strlen(comparator);i++){
        comparator[i]=tolower((char)comparator[i]);
    }
    //obraten
    for(int i=0;i<strlen(a)/2;i++){
        char temp=*(a+i);
        *(a+i)=*(a+(strlen(a)-i-1));
        *(a+(strlen(a)-i-1))=temp;
    }
    for(int i=0;i<strlen(b)/2;i++){
        char temp=*(b+i);
        *(b+i)=*(b+(strlen(b)-i-1));
        *(b+(strlen(b)-i-1))=temp;
    }
    //!obraten
    if(strcmp(comparator,"pogolem")==0){
        if(strcmp(a,b)>0){
            return a;
        }
        else return b;
        
    }
    else if(strcmp(comparator,"pomal")==0){
        if(strcmp(a,b)<0){
            return a;
        }
        else return b;
    }
    else return "Invalid comparator";
}

int main () {

    int n;
    char a[1000],b[1000],comparator[10];
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        
        /*fgets(a, sizeof(a), stdin);
        fgets(b, sizeof(b), stdin);
        fgets(comparator, sizeof(comparator), stdin);
        */
        scanf("%s%s%s", &a, &b, &comparator);
        printf("%s\n", revertString(a, b, comparator));
        //puts(revertString(a,b,comparator));
    }
    return 0;

}