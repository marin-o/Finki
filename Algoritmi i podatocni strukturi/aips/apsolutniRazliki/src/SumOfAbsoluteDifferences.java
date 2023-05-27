import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    private int findNextSmallest(int numbers[],int index){
        int newIndex=index;

        for(int i=index+1;i<numbers.length;i++){
            if(numbers[newIndex]>numbers[i])
                newIndex=i;
        }

        return newIndex;
    }

    private int findNextLargest(int numbers[],int index){
        int newIndex=index;

        for(int i=index+1;i<numbers.length;i++){
            if(numbers[newIndex]<numbers[i])
                newIndex=i;
        }

        return newIndex;
    }
    static int solve(int numbers[], int N, int K) {
        int sum=0;
        ArrayList<Integer> subArray= new ArrayList<>();
        boolean next=true; //true==findNextSmallest; false==findNextLargest
        for(int i=0;i<K;i++){

        }


        return sum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}