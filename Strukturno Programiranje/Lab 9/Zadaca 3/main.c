#include<stdio.h>

double max (double *niza, int n)
{
    double maks = *(niza);
    for(int i = 0; i < n; i++)
    {
        if(*(niza + i) > maks)
        {
            maks = *(niza + i);
        }

    }
    return maks;
}

double min (double * niza, int n)
{
    double mini = *(niza);
    for(int i = 0; i < n; i++)
    {
        if(*(niza + i) < mini)
        {
            mini = *(niza + i);
        }
    }
    return mini;
}

void normalize (double * niza, int n)
{
    double minN = min(niza, n), maxN = max(niza, n);
    for(int i = 0; i < n; i++)
    {
        double x = (*(niza + i)- minN) / (maxN - minN);
        *(niza + i) = x;
    }
}


int main () {

    double niza [200];
    int i,n;

    scanf("%d", &n);

    for (i=0;i<n;i++) {
        scanf("%lf", &niza[i]);

    }
    printf("MAX -> %.3f\n", max(niza,n));
    printf("MIN -> %.3f\n", min(niza,n));

    normalize(niza,n);

     for (i=0;i<n;i++) {
        printf("%.3f ", niza[i]);

    }

    return 0;

}