import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
class InsufficientElementsException extends Exception{
    public InsufficientElementsException(String msg){
        super(msg);
    }
}

class InvalidRowNumberException extends Exception{
    public InvalidRowNumberException( String msg ){
        super(msg);
    }
}

class InvalidColumnNumberException extends Exception{
    public InvalidColumnNumberException( String msg ){
        super(msg);
    }
}

final class DoubleMatrix{
    double matrix[][];
    int rows,cols;

    public DoubleMatrix(double a[],int rows,int cols) throws InsufficientElementsException {
        this.rows=rows;
        this.cols=cols;
        if(a.length<rows*cols)
            throw new InsufficientElementsException("Insufficient number of elements");
        this.matrix=new double [rows][cols];

        if(a.length>rows*cols){
            for ( int i=0, nizaBrojac=a.length-rows*cols;i<rows && nizaBrojac<a.length;i++ ){
                for(int j=0;j<cols;j++,nizaBrojac++) {
                    matrix[i][j]=a[nizaBrojac];
                }
            }
        }
        else {
            for ( int i=0, nizaBrojac=0;i<rows && nizaBrojac<a.length;i++ ){
                for(int j=0;j<cols;j++,nizaBrojac++) {
                    matrix[i][j]=a[nizaBrojac];
                }
            }
        }


    }

    public String getDimensions(){
        return "[" + rows + " x " + cols + "]";
    }

    public int rows(){
        return rows;
    }
    public int columns(){
        return cols;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        double max=-1000000;
        if(row > rows || row <1) throw new InvalidRowNumberException("Invalid row number");
        for(int i=0;i<cols;i++){
            if ( max<matrix[row-1][i] )
                max=matrix[row-1][i];
        }
        return max;
    }
    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if(column > cols || column < 1) throw new InvalidColumnNumberException("Invalid column number");
        double max=matrix[0][column-1];
        for(int i=1;i<rows;i++){
            if ( max<matrix[i][column-1])
                max=matrix[i][column-1];
        }
        return max;
    }

    public double[] toSortedArray(){
        double[] arrayified;
        int brojac=0;
        arrayified=toArray(matrix);
        Arrays.sort(arrayified);
        for(int i=0;i<arrayified.length/2;i++){
            double temp = arrayified[i];
            arrayified[i] = arrayified[arrayified.length - i - 1];
            arrayified[arrayified.length - i - 1] = temp;
        }

        return arrayified;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        DecimalFormat df=new DecimalFormat("0.00");
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(j==cols-1)
                    sb.append(df.format(matrix[i][j]));
                else
                    sb.append(df.format(matrix[i][j])+ "\t");
            }
            if(i!=rows-1)
                sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        DoubleMatrix that=(DoubleMatrix) o;
        return Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    public double sum() {
        return Arrays.stream(toArray(matrix)).sum();
    }

    public static double[] toArray(double[][] toBeArray){
        double[] arrayified=new double[toBeArray.length*toBeArray[0].length];
        int brojac=0;
        for(int i=0;i<toBeArray.length;i++)
            for(int j=0;j<toBeArray[0].length;j++)
                arrayified[brojac++]=toBeArray[i][j];

        return arrayified;
    }
}

class MatrixReader{
    public static DoubleMatrix read( InputStream input) throws InsufficientElementsException {
        Scanner in = new Scanner(input);
        int m=in.nextInt();
        int n=in.nextInt();
        double[][] matrix = new double[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                matrix[i][j]=in.nextDouble();
        return new DoubleMatrix(DoubleMatrix.toArray(matrix),m,n);
    }
}

public class DoubleMatrixTester {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}
