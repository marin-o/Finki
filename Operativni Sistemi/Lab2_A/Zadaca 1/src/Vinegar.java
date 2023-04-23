import java.util.HashSet;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Vinegar {

    static Semaphore carbon = new Semaphore(2);
    static Semaphore oxygen = new Semaphore(2);
    static Semaphore hydrogen = new Semaphore(4);

    static int atoms = 0;
    static Lock atomsLock = new ReentrantLock();

    static Semaphore cHere = new Semaphore(0);
    static Semaphore oHere = new Semaphore(0);

    static Semaphore bond = new Semaphore(0);
    static Semaphore done = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        HashSet<Thread> threads = new HashSet<>();

        for (int i = 0; i < 20; i++) {

            threads.add(new C());

            threads.add(new H());

            threads.add(new H());

            threads.add(new O());

        }

        // run all threads in background

        threads.forEach(Thread::start);

        // after all of them are started, wait each of them to finish for maximum 2_000 ms

        Thread.sleep(2000);

        // for each thread, terminate it if it is not finished

        boolean deadlock = false;

        for ( Thread thread: threads ) {
            if ( thread.isAlive() ) {
                thread.interrupt();
                deadlock=true;
            }
        }

        if(deadlock)
            System.out.println("Possible deadlock!");
        else
            System.out.println("Process finished.");
    }


    static class C extends Thread{

        @Override
        public void run(){
            try {
                execute();
            } catch ( InterruptedException e ) {
                //
            }
        }
        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel
            carbon.acquire();
            System.out.println("C here.");
            cHere.release();

            // after all atoms are present, they should start with the bonding process together
            bond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                done.release(8);
                atoms = 0;
            }
            atomsLock.unlock();

            done.acquire();
            System.out.println("C done.");


            // only one atom should print the next line, representing that the molecule is created
            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                System.out.println("Molecule created.");
                atoms = 0;
            }
            atomsLock.unlock();

            carbon.release();

        }

    }


    static class H extends Thread{

        @Override
        public void run(){
            try {
                execute();
            } catch ( InterruptedException e ) {
                //
            }
        }

        public void execute() throws InterruptedException {

            // at most 4 atoms should print this in parallel
            hydrogen.acquire();
            System.out.println("H here.");

            atomsLock.lock();
            atoms++;
            if(atoms == 4){
                cHere.acquire(2);
                oHere.acquire(2);
                bond.release(8);
            }
            atomsLock.unlock();
            // after all atoms are present, they should start with the bonding process together
            bond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                done.release(8);
                atoms = 0;
            }
            atomsLock.unlock();
            done.acquire();
            System.out.println("H done.");

            // only one atom should print the next line, representing that the molecule is created
            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                System.out.println("Molecule created.");
                atoms = 0;
            }
            atomsLock.unlock();

            hydrogen.release();

        }

    }


    static class O extends Thread{

        @Override
        public void run(){
            try {
                execute();
            } catch ( InterruptedException e ) {
                //
            }
        }

        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel
            oxygen.acquire();
            System.out.println("O here.");
            oHere.release();

            // after all atoms are present, they should start with the bonding process together
            bond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                done.release(8);
                atoms = 0;
            }
            atomsLock.unlock();

            done.acquire();
            System.out.println("O done.");


            // only one atom should print the next line, representing that the molecule is created
            atomsLock.lock();
            atoms++;
            if(atoms == 8){
                System.out.println("Molecule created.");
                atoms = 0;
            }
            atomsLock.unlock();

            oxygen.release();

        }

    }


}


