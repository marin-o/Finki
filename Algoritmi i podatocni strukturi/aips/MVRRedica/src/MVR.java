import java.util.*;

class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ){
        this.element = elem;
        this.succ = succ;
    }
}

interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.
    public int size();
    // Ja vrakja dolzinata na redicata.
    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
    // Metodi za transformacija:
    public void clear();
    // Ja prazni redicata.
    public void enqueue(E x);
    // Go dodava x na kraj od redicata.
    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class LinkedQueue<E> implements Queue<E> {
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;
    // Konstruktor ...
    public LinkedQueue () {
        clear();
    }
    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }
    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }
    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }
    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }
    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }
    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null) rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}
class Gragjanin{
    String imePrezime;
    int lKarta,pasos,vozacka;

    public Gragjanin( String imePrezime, int lKarta, int pasos, int vozacka ) {
        this.imePrezime=imePrezime;
        this.lKarta=lKarta;
        this.pasos=pasos;
        this.vozacka=vozacka;
    }

    public int getlKarta() {
        return lKarta;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozacka() {
        return vozacka;
    }

    public void setlKarta( int lKarta ) {
        this.lKarta=lKarta;
    }

    public void setPasos( int pasos ) {
        this.pasos=pasos;
    }

    public void setVozacka( int vozacka ) {
        this.vozacka=vozacka;
    }

    @Override
    public String toString(){
        return imePrezime;
    }

}



public class MVR {

    public static void main( String[] args ) {

        Scanner br = new Scanner(System.in);

        LinkedQueue<Gragjanin> mvrQueue = new LinkedQueue<>();
        int N = Integer.parseInt(br.nextLine());
        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);
            mvrQueue.enqueue(covek);
        }

        boolean moreId=true,morePass=true,moreLicense=true;

        while( !mvrQueue.isEmpty() ){
            if(mvrQueue.peek().lKarta == 1){
                mvrQueue.peek().lKarta=0;
                mvrQueue.enqueue(mvrQueue.dequeue());
            }
            else if(mvrQueue.peek().pasos == 1) {
                mvrQueue.peek().pasos = 0;
                mvrQueue.enqueue(mvrQueue.dequeue());
            }
            else if ( mvrQueue.peek().vozacka == 1 ) {
                mvrQueue.peek().vozacka=0;
                mvrQueue.enqueue(mvrQueue.dequeue());
            }
            if (mvrQueue.peek().lKarta == 0 && mvrQueue.peek().pasos == 0 && mvrQueue.peek().vozacka==0 )
                System.out.println(mvrQueue.dequeue());
        }


        /*Gragjanin[] gragjaninsPrvi=new Gragjanin[N];
        Gragjanin[] gragjaninsVtori=new Gragjanin[N];
        Gragjanin[] gragjaninsTreti=new Gragjanin[N];
        int prvi,vtori,treti;

        for ( int i=1; i <= N; i++ ) {
            String imePrezime=br.nextLine();
            int lKarta=Integer.parseInt(br.nextLine());
            int pasos=Integer.parseInt(br.nextLine());
            int vozacka=Integer.parseInt(br.nextLine());
            Gragjanin covek=new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            if(lKarta==1&&pasos==0&&vozacka==0)
                gragjaninsPrvi[prvi++]=covek;
            else if ( pasos == 1 && vozacka == 0 ) {
                gragjaninsVtori[vtori++]=covek;
            }
            else gragjaninsTreti[treti++]=covek;
        }

        Queue<Gragjanin> gragjanins = new LinkedList<>();
        for(int i=0;i<prvi;i++){
            gragjanins.offer(gragjaninsPrvi[i]);
        }for(int i=0;i<vtori;i++){
            gragjanins.offer(gragjaninsVtori[i]);
        }for(int i=0;i<treti;i++){
            gragjanins.offer(gragjaninsTreti[i]);
        }
        while(!gragjanins.isEmpty()){
            System.out.println(gragjanins.poll());
        }

         */
    }

}
