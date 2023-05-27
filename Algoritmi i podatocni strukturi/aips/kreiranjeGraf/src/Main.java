import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    public Graph(int num_nodes, E[] nodes) {
        this.num_nodes = num_nodes;
        this.nodes = nodes;
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    int adjacent(int x,int y)
    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }

    void addEdge(int x,int y)
    {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }

    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }

    // Moze i ne mora?
    E get_node_value(int x)
    {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, E a)
    {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x]=a;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);

        for (int i = 0; i < this.num_nodes; i++) {
            if(adjacent(node, i)==1){
                if (!visited[i])
                    dfsRecursive(i, visited);
            }
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        int pom;

        while (!s.isEmpty()) {
            pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom,i)==1){
                    pom1 = i;
                    if(!visited[i])
                        break;
                }
            }
            if(!visited[pom1]){
                visited[pom1] = true;
                System.out.println(pom1 + ": " + nodes[pom1]);
                s.push(pom1);
            }
            else
                s.pop();
        }

    }

    void bfs(int node){
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + nodes[node]);
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        int pom;

        while(!q.isEmpty()){
            pom = q.dequeue();
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom, i)==1){
                    if (!visited[i]){
                        visited[i] = true;
                        System.out.println(i+": " + nodes[i]);
                        q.enqueue(i);
                    }

                }
            }


        }

    }

    public void printAdjMat(){
        for ( int i=0; i < num_nodes; i++ ) {
            for ( int j=0; j < num_nodes; j++ ) {
                if(j+1==num_nodes)
                    System.out.println(adjMat[i][j]);
                else
                    System.out.print(adjMat[i][j]+" ");
            }
        }
    }

    @Override
    public String toString() {
        String ret="  ";
        for(int i=0;i<num_nodes;i++)
            ret+=nodes[i]+" ";
        ret+="\n";
        for(int i=0;i<num_nodes;i++){
            ret+=nodes[i]+" ";
            for(int j=0;j<num_nodes;j++)
                ret+=adjMat[i][j]+" ";
            ret+="\n";
        }
        return ret;
    }


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
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


public class Main {
    public static void main( String[] args ) throws IOException {
        Graph<Character> graph=new Graph<>(0);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int commands = Integer.parseInt(br.readLine());
        while(commands!=0) {
            String[] command = br.readLine().split("\\s+");
            if(command[0].equals("CREATE")){
                Character[] values =new Character[Integer.parseInt(command[1])];
                for ( int i=0; i < values.length; i++ ) {
                    values[i]=(char)('A'+i);
                }
                graph = new Graph<>(Integer.parseInt(command[1]),values);

            }
            if(command[0].equals("ADDEDGE")){
                int e1 = Integer.parseInt(command[1]);
                int e2 = Integer.parseInt(command[2]);
                graph.addEdge(e1,e2);
            }
            if(command[0].equals("DELETEEDGE")){
                int e1 = Integer.parseInt(command[1]);
                int e2 = Integer.parseInt(command[2]);
                graph.deleteEdge(e1,e2);
            }
            if(command[0].equals("ADJACENT")){
                int e1 = Integer.parseInt(command[1]);
                int e2 = Integer.parseInt(command[2]);
                System.out.println(graph.adjacent(e1,e2));
            }
            if(command[0].equals("PRINTMATRIX")){
                graph.printAdjMat();
            }
            if(command[0].equals("PRINTNODE")){
                System.out.println(graph.nodes[Integer.parseInt(command[1])]);
            }
            commands--;
        }
    }
}