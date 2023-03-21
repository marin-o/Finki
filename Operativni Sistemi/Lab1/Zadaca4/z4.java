public class z4 {
    public static class Thread1 extends Thread {
        String arg;
        public Thread1(String arg){
            this.arg = arg;
        }
        public void run() {
            System.out.print(arg);
        }
    }

    public static class ThreadAB extends Thread{
        String arg1, arg2;
        public ThreadAB(String arg1, String arg2){
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
        public void run(){
            Thread t1 = new Thread1(arg1);
            Thread t2 = new Thread1(arg2);
            t1.start();
            try {
                t1.join();
            } catch ( InterruptedException e ) {
                throw new RuntimeException(e);
            }
            t2.start();
        }
    }

    public static void main(String[] args) {
        new ThreadAB("A\nB\n","1\n2").start();
    }

}
