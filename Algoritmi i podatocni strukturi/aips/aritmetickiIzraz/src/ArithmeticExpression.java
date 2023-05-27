import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r



    static int presmetaj(char c[], int l, int r) {
        for(int i=l;i<=r;i++){
            if(Character.isDigit(c[i])){

            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
