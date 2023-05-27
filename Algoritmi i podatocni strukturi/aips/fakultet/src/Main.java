import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Main {



    public static <E> int getIndex(Graph<E> graph, E info) {
        GraphNode<E>[] adjList = graph.adjList;
        for (GraphNode<E> node : adjList) {
            if (node.getInfo().equals(info)) {
                return node.getIndex();
            }
        }
        return -1;
    }
    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] subjects = new String[n];
        for ( int i=0; i < n; i++ ) {
            subjects[i]=br.readLine();
        }
        Graph<String> graph = new Graph<>(n,subjects);
        n = Integer.parseInt(br.readLine());
        int[] indexesOfRequirements=new int[0];
        for ( int i=0; i < n; i++ ) {
            String[] parts = br.readLine().split("\\s+");
            int indexOfSubject = getIndex(graph,parts[0]);
            indexesOfRequirements = new int[parts.length-1];
            for ( int j=1; j < parts.length; j++ ) {
//              indexesOfRequirements[j-1]=getIndex(graph,parts[j]);
                graph.addEdge(getIndex(graph,parts[j]),indexOfSubject);
            }
        }
        /*int[] niza = graph.topological_sort_dfs();
        System.out.println(graph.adjList[niza[niza.length-1]].getInfo());
        *//*for ( int i=0; i < indexesOfRequirements.length; i++ ) {
            System.out.print(indexesOfRequirements[i]+" ");
        }*/
        String poslednoSlushan = br.readLine();
        Stack<String> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();
        stack.push(poslednoSlushan);
        visited.add(poslednoSlushan);
        while(!stack.isEmpty()){
            String current =stack.pop();
            for ( String neighbor: graph.adjList[getIndex(graph,current)].getNeighbors() ) {
                
            }
        }
    }
}

class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }


    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }


    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+=neighbors.get(i).info+" ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }



}

class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    /************************TOPOLOGICAL SORT*******************************************************************/

    void dfsVisit( Stack<Integer> s, int i, boolean[] visited){
        if(!visited[i]){
            visited[i] = true;
            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
            //System.out.println("dfsVisit: "+i+" Stack="+s);
            while(it.hasNext()){
                dfsVisit(s, it.next().getIndex(), visited);
            }
            s.push(i);
            //System.out.println("--dfsVisit: "+i+" Stack="+s);
        }
    }

    int[] topological_sort_dfs(){
        boolean[] visited= new boolean[num_nodes];
        for(int i=0;i<num_nodes;i++){
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();

        for(int i=0;i<num_nodes;i++){
            dfsVisit(s,i,visited);
        }
        //System.out.println("----Stack="+s);
        /*while(!s.isEmpty()){
            System.out.println(adjList[s.pop()]);
        }*/
        LinkedList<Integer> lista = new LinkedList<>();
        while(!s.isEmpty()){
            lista.push(s.pop());
        }
        int[] niza = new int[lista.size()];
        for ( int i=0; i < niza.length; i++ ) {
            niza[i]=lista.get(i);
        }
        return niza;
    }

    /***********************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }

}
