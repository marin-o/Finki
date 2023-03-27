import java.util.*;
import java.util.concurrent.Semaphore;

public class CountThree {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na elementot 3
     */
    int count = 0;
    private Semaphore semaphore;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    public void increment(int localCount){
        count += localCount;
    }



    public void init() {
        semaphore = new Semaphore(1);
    }

    class Counter extends Thread {

        public void count(int[] data) throws InterruptedException {
            int localCount=0;
            for ( Integer integ:
            data ){
                if(integ == 3)
                    localCount++;
            }
            semaphore.acquire();
            increment(localCount);
            semaphore.release();
        }
        private int[] data;

        public Counter(int[] data) {
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
            CountThree environment = new CountThree();
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
            int[] data = new int[total];
            for (int j = 0; j < total; j++) {
                data[j] =random.nextInt(10);
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
        System.out.println(count);


    }
}