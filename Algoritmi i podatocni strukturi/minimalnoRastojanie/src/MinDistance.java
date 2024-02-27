import java.util.Scanner;
import java.awt.geom.Point2D;
public class MinDistance {

    public static float minimalDistance(float points[][]) {
        float minDistance=Float.MAX_VALUE;
        for(int i=0;i < points.length;i++){
            for(int j=i+1;j < points.length;j++){
                float curDistance=(float) Point2D.distance(points[i][0],points[i][1],points[j][0],points[j][1]);
                if(minDistance>curDistance) minDistance=curDistance;
            }

        }
        return minDistance;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}