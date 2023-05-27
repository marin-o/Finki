import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        boolean swapped=true;
        int rIndex=n,lIndex=0;
        while(swapped){
            swapped=false;
            for(int j=rIndex-1;j>lIndex;j--){
                if(a[j]<a[j-1]){
                    int temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                    swapped=true;
                }
            }
            System.out.println(Arrays.toString(a).replace("[","").replace("]","").replace(",",""));
            for(int i=0;i<rIndex-1;i++){
                if(a[i]>a[i+1]){
                    int temp=a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp;
                    swapped=true;
                }
            }
            lIndex++;
            rIndex--;
            System.out.println(Arrays.toString(a).replace("[","").replace("]","").replace(",",""));
        }
       // System.out.println(Arrays.toString(a).replace("[","").replace("]","").replace(",",""));
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}