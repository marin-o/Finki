#include <stdio.h>

#include <stdlib.h>

#include <sys/wait.h>

#include <sys/types.h>

int main (int argc, char *argv[])

{

pid_t pid;
if ((pid = fork ()) == 0)

{    
      /* child */
printf ("proces dete\n");
        
int number;
        
printf ("vnesete broj: ");
        
scanf ("%d", &number);
        
printf ("vnesovte broj: %d. ", number);
        
while (number != 0){
            
printf ("vnesete broj: ");
            
scanf ("%d", &number);
            
printf ("vnesovte %d. ", number);
            
}
        
}

else

{
        
if (pid > 0)

{

printf ("proces tatko\n");
            
printf ("cekam da zavrsi deteto\n");
            
wait (NULL);

printf ("\nse budam\npovtorno proces tatko");

}

}

}

