package junit;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public static boolean isTriangle( int side1, int side2, int side3 ) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }
}
