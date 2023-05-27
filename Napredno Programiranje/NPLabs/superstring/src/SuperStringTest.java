import java.util.*;
import java.util.stream.Collectors;

class SuperString{

    /*private LinkedList<String> list=new LinkedList<>();
    private LinkedList<Integer> removeList=new LinkedList<>();

    public void append(String s){
        list.add(s);
        removeList.add(1);
    }

    public void insert(String s){
        list.addFirst(s);
        removeList.add(-1);
    } 
    
    public boolean contains(String s){
        return toString().contains(s);
    }
    
    public void reverse(){
        Collections.reverse(list);
        Collections.reverse(removeList);
        for ( String s: list ) {
            s = new StringBuilder(s).reverse().toString();
        }
    }

    @Override
    public String toString(){
        return String.join("",list);
    }

    public void removeLast(int k){
        while(k!=0){
            if(!list.isEmpty()){
                if(removeList.removeFirst()<0) list.removeFirst();
                else list.removeLast();
            }
            k--;
        }

    }*/
    
    LinkedList<String> superstring;
    LinkedList<Integer> removeKLast;

    public SuperString() {
        this.superstring=new LinkedList<String>();
        this.removeKLast=new LinkedList<Integer>();

    }

    public void append(String s){
        superstring.add(s);
        removeKLast.add(1);
    }
    public void insert(String s){
        superstring.addFirst(s);
        removeKLast.add(-1);
    }

    public boolean contains(String s){
        return toString().contains(s);
    }

    public void reverse(){
        for(int i = 0 ; i<superstring.size();i++){
            String s =superstring.get(i);
            s = new StringBuilder(s).reverse().toString();
            superstring.set(i,s);
        }
        Collections.reverse(superstring);
        Collections.reverse(removeKLast);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for ( String s : superstring )	sb.append(s);
        return sb.toString();
    }

    public void removeLast(int k){
        while(k!=0){
            if(superstring.size()!=0)
                if(removeKLast.removeFirst()<0)
                    superstring.removeLast();
                else superstring.removeFirst();
            k--;
        }
    }

}
public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}
