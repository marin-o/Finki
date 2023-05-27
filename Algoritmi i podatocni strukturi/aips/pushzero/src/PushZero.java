import java.util.Scanner;

public class PushZero
{
    static void smallSwap(int array[],int ind1,int ind2){
        int temp=array[ind1];
        array[ind1]=array[ind2];
        array[ind2]=temp;
    }
    static void pushZerosToEnd(int arr[], int n)
    {
        int ind=0;
        boolean found=false;
        for (int i=0;i<n;i++){
            if(arr[i]==0 && !found){
               ind=i;
               found=true;
            }
            if(arr[i]!=0 && found){
                smallSwap(arr,ind,i);
                found=false;
                i=ind;
            }

        }
    }

    public static void main (String[] args)
    {

        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int arr[] = new int[n];
        for (int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        pushZerosToEnd(arr, n);
        System.out.println("Transformiranata niza e:");
        for ( int i: arr ) {
            System.out.printf(i + " ");
        }
    }
}