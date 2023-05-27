import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


class ComplexNumber<R extends Number,I extends Number> implements Comparable<ComplexNumber<? extends Number,? extends Number>>{
    R real;
    I imaginary;

    public ComplexNumber( R real, I imaginary ) {
        this.real=real;
        this.imaginary=imaginary;
    }

    public R getReal() {
        return real;
    }

    public I getImaginary() {
        return imaginary;
    }

    public double modul(){
        return Math.sqrt(Math.pow(real.doubleValue(),2)+Math.pow(imaginary.doubleValue(),2));
    }

    public int compareTo(ComplexNumber<? extends Number,? extends Number> o){
        return Double.compare(this.modul(), o.modul());
    }

    @Override
    public String toString(){
        return imaginary.doubleValue() < 0 ? String.format("%.2f%.2fi",real.doubleValue(),imaginary.doubleValue()) : String.format("%.2f+%.2fi",real.doubleValue(),imaginary.doubleValue());
    }


}

public class ComplexNumberTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test simple functions int
            int r = jin.nextInt();int i = jin.nextInt();
            ComplexNumber<Integer, Integer> c = new ComplexNumber<Integer, Integer>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if ( k == 1 ) { //test simple functions float
            float r = jin.nextFloat();
            float i = jin.nextFloat();
            ComplexNumber<Float, Float> c = new ComplexNumber<Float, Float>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if ( k == 2 ) { //compareTo int
            LinkedList<ComplexNumber<Integer,Integer>> complex = new LinkedList<ComplexNumber<Integer,Integer>>();
            while ( jin.hasNextInt() ) {
                int r = jin.nextInt(); int i = jin.nextInt();
                complex.add(new ComplexNumber<Integer, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if ( k == 3 ) { //compareTo double
            LinkedList<ComplexNumber<Double,Double>> complex = new LinkedList<ComplexNumber<Double,Double>>();
            while ( jin.hasNextDouble() ) {
                double r = jin.nextDouble(); double i = jin.nextDouble();
                complex.add(new ComplexNumber<Double, Double>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if ( k == 4 ) { //compareTo mixed
            LinkedList<ComplexNumber<Double,Integer>> complex = new LinkedList<ComplexNumber<Double,Integer>>();
            while ( jin.hasNextDouble() ) {
                double r = jin.nextDouble(); int i = jin.nextInt();
                complex.add(new ComplexNumber<Double, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
    }
}