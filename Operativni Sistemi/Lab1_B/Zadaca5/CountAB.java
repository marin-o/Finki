import java.util.*;
import java.util.concurrent.Semaphore;

public class CountAB {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na elementot 3
     */
    int countA = 0, countB = 0;
    float average;
    private Semaphore semaphore;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    public void increment(int localCountA,int localCountB){
        countA += localCountA;
        countB += localCountB;
    }



    public void init() {
        semaphore = new Semaphore(1);
    }

    class Counter extends Thread {

        public void count(char[] data) throws InterruptedException {
            int localCountA=0;
            int localCountB=0;
            for ( Character integ : data ){
                if(integ == 'A')
                    localCountA++;
                if(integ == 'B')
                    localCountB++;
                
            }
            
            semaphore.acquire();
            increment(localCountA,localCountB);
            semaphore.release();
        }
        private char[] data;

        public Counter(char[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Main environment = new Main();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        Scanner s = new Scanner(System.in);
        int total=s.nextInt();
        Random random = new Random();
        for (int i = 0; i < NUM_RUNS; i++) {
            char[] data = new char[total];
            for (int j = 0; j < total; j++) {
                data[j] = (char) (random.nextInt(26) + 'A');
            }
            Counter c = new Counter(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        average = (countA+countB)/2;
        System.out.println(average);


    }
}
