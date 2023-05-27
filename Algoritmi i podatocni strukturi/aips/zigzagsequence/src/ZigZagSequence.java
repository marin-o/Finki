import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        Stack<Integer> zigZagStack = new Stack<>();
        zigZagStack.push(a[0]);
        int longestSubStack=0;
        for(int i=1;i<a.length;i++){
            if((zigZagStack.peek() ^ a[i]) < 0 && a[i]!=0)
                zigZagStack.push(a[i]);
            else{
                zigZagStack.clear();
                if(a[i]==0 && i<a.length-1)
                    zigZagStack.push(a[++i]);
                else zigZagStack.push(a[i]);
            }
            if(longestSubStack<zigZagStack.size())
                longestSubStack=zigZagStack.size();
        }

        return longestSubStack;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
