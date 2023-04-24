

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Semaphore;

class OSMidterm {

    //TODO: Initialize the semaphores you need
    static Semaphore textLock = new Semaphore(1);
    static int row = 0;
    public static void main(String[] args) throws InterruptedException {

        //STARTING CODE, DON'T MAKE CHANGES
        //-----------------------------------------------------------------------------------------
        String final_text="Bravo!!! Ja resi zadacata :)";
        int m=10, n=100;
        Object[][] data = new Object[m][n];
        Random rand = new Random();
        int k=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int random = rand.nextInt(100);
                if(random%2==0 & k<final_text.length()) {
                    data[i][j] = final_text.charAt(k);
                    k++;
                } else {
                    data[i][j] = rand.nextInt(100);
                }
            }
        }

        DataMatrix matrix = new DataMatrix(m,n, data);
        StatisticsResource statisticsResource = new StatisticsResource();
        //-----------------------------------------------------------------------------------------

        //ONLY TESTING CODE, SO YOU CAN TAKE A LOOK OF THE OUTPUT YOU NEED TO GET AT THE END
        //YOU CAN COMMENT OR DELETE IT AFTER YOU WRITE THE CODE USING THREADS
        //-----------------------------------------------------------------------------------------
//        Concatenation concatenation = new Concatenation(matrix, statisticsResource);
//
//        concatenation.concatenate();
//
//        statisticsResource.printString();
        //-----------------------------------------------------------------------------------------

        //TODO: Run the threads from the Concatenation class

        HashSet<Concatenation> threads = new HashSet<>();

        for ( int i=0; i < m; i++ ) {
            threads.add(new Concatenation(matrix, statisticsResource));
        }

        threads.forEach(Thread::start);

        //TODO: Wait 10seconds for all threads to finish
        Thread.sleep(10000);
        //TODO: Print the string you get, call function printString()

        statisticsResource.printString();

        //TODO: Check if some thread is still alive, if so kill it and print "Possible deadlock"
        boolean deadlock = false;
        for ( Thread thread: threads ) {
            if ( thread.isAlive() ){
                thread.interrupt();
                deadlock = true;
            }
        }
        if(deadlock)
            System.out.println("Possible deadlock");
    }

    // TODO: Make the Concatenation Class  a Thread Class
    static class Concatenation extends Thread{

        private DataMatrix matrix;
        private StatisticsResource statisticsResource;

        public Concatenation(DataMatrix matrix, StatisticsResource statisticsResource) {
            this.matrix = matrix;
            this.statisticsResource = statisticsResource;
        }
        //concatenation function implemented on the whole matrix, so you can take a look of the task's logic
        public void concatenate() {
            for (int i=0;i<this.matrix.getM();i++) {
                for (int j=0;j<this.matrix.getN();j++) {
                    if (this.matrix.isString(i,j)) {
                        this.statisticsResource.concatenateString(this.matrix.getEl(i,j).toString());
                    }
                }
            }
        }

        public void concatenate_by_row() throws InterruptedException {
            //TODO: Implement this function
            // add  arguments in the function if needed
            StringBuilder sb = new StringBuilder();
            textLock.acquire();

                for(int j = 0; j < matrix.n; j++){
                    Character c = !Character.isDigit(matrix.getEl(row,j).toString().charAt(0)) ? matrix.getEl(row,j).toString().charAt(0) : null;
                    if(c != null)
                        sb.append(c);
                }
            statisticsResource.concatenateString(sb.toString());
            row++;
            textLock.release();
        }
        public void execute() throws InterruptedException{
            concatenate_by_row();
        }

        @Override
        public void run(){
            try {
                execute();
            }catch ( InterruptedException e ){
                //
            }
        }

    }

    //-------------------------------------------------------------------------
    //YOU ARE NOT CHANGING THE CODE BELOW
    static class DataMatrix {

        private int m,n;
        private Object[][] data;

        public DataMatrix(int m, int n, Object[][] data) {
            this.m = m;
            this.n = n;
            this.data = data;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }

        public Object[][] getData() {
            return data;
        }

        public Object getEl(int i, int j) {
            return data[i][j];
        }

        public Object[] getRow(int pos) {
            return this.data[pos];
        }

        public Object[] getColumn(int pos) {
            Object[] result = new Object[m];
            for (int i=0;i<m;i++) {
                result[i]=data[i][pos];
            }
            return result;
        }

        public boolean isString(int i, int j) {
            return this.data[i][j] instanceof Character;
        }


    }

    static class StatisticsResource {

        private String concatenatedString;

        public StatisticsResource() {
            this.concatenatedString = "";
        }

        //function for String concatenation
        public void concatenateString(String new_character) {
            concatenatedString+=new_character;
        }

        //function for printing the concatenated string
        public void printString() {
            System.out.println("Here is the phrase from the matrix: " + concatenatedString);
        }

    }



}


