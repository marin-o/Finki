import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        int result=0;
        String[] summationSplit=expression.split("[+]");
        //System.err.println(Arrays.toString(summationSplit));
        for ( String operand: summationSplit ) {
            String[] multiplicationString=operand.split("[*]");
            if(multiplicationString.length==1){
                result+=Integer.parseInt(multiplicationString[0]);
            }
            else {
                int mult=1;
                for ( String operand1: multiplicationString ) {
                    mult*=Integer.parseInt(operand1);
                }
                result+=mult;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}