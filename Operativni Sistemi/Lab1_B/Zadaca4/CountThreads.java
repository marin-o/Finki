import java.lang.Thread;
public class CountThreads {


public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new ThreadPair('A'));
    Thread t2 = new Thread(new ThreadPair('0'));
    t1.start();
    t1.join();
    t2.start();
}


}

class ThreadPair implements Runnable{
    char type;

    public ThreadPair(char type){
        this.type=type;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(type+i);
        }
    }
}
