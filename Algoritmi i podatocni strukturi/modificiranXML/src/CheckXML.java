import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class CheckXML {

    public static int valid(String[] redovi){
        int valid=1;
        Stack<String> XMLStack = new Stack<>();
        for ( String tag: redovi ) {
            //tag.replace("/","");
            if(tag.charAt(0) == '[' && tag.charAt(1)!='/')
                XMLStack.push(tag);
            else if(tag.charAt(0) == '[' && tag.charAt(1) == '/'){
                if(!XMLStack.pop().equals(tag.replace("/",""))){
                    valid = 0;
                    break;
                }
            }
        }
        return valid;
    }
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid;

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        System.out.println(valid(redovi));

        br.close();
    }
}