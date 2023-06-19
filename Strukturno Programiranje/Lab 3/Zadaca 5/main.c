#include <stdio.h>
#include <math.h>
int
main ()
{
  int x1, y1, x2, y2, x3, y3;
  float AB, BC, AC;
  scanf ("%d%d", &x1, &y1);
  scanf ("%d%d", &x2, &y2);
  scanf ("%d%d", &x3, &y3);

  AB = sqrt(pow(x2-x1,2)+pow(y2-y1,2));
  BC = sqrt(pow(x3-x2,2)+pow(y3-y2,2));
  AC = sqrt(pow(x3-x1,2)+pow(y3-y1,2));

  if (AB > BC && AB > AC)
    printf ("AB");
  else if (BC > AB && BC > AC)
    printf ("BC");
  else if (AC > AB && AC > BC)
    printf ("AC");

  return 0;
}
