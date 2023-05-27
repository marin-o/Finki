import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        Arrays.sort(coins);
        for(int i=0;i<coins.length/2;i++){
            int temp=coins[i];
            coins[i]=coins[coins.length-i-1];
            coins[coins.length-i-1]=temp;
        }
        int vkupnoMoneti=0;
        for ( int coin: coins ) {
            vkupnoMoneti+=sum / coin;
            sum%=coin;
        }
        return vkupnoMoneti;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}