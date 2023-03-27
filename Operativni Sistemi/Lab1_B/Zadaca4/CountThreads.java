import java.lang.Thread;
public class CountThreads {


public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new ThreadPair(new ArrayList<Character>(Arrays.asList('A','B','C','D','E','F','G','H','I','J'));
    Thread t2 = new Thread(new ThreadPair(new ArrayList<Character>(Arrays.asList('1','2','3','4','5','6','7','8','9','10'));
    t1.start();
    t1.join();
    t2.start();
}


}

class ThreadPair implements Runnable{
    ArrayList<Character> chars

    public ThreadPair(ArrayList<Character> chars){
        this.chars=chars;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(chars.get(i));
        }
    }
}
