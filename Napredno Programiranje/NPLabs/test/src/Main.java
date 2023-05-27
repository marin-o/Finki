
interface ITest<T>{
    public boolean killMe(T a, T b);
}

public class Main {


    public static void main( String[] args ) {
        ITest<Integer> integerITest = (a,b) -> a+b>3;
        System.out.println(integerITest.killMe(3,2));
    }
}