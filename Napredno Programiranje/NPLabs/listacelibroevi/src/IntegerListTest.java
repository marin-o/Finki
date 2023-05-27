import java.util.*;
import java.util.stream.Collectors;

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    try{
                    System.out.println(list.sumFirst(jin.nextInt()));
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}

class IntegerList{
    List<Integer> list;
    public IntegerList(){
        list = new LinkedList<Integer>();
    }

    public IntegerList( Integer[] numbers) {
        list = new LinkedList<Integer>();
        Collections.addAll(list, numbers);
    }

     public void add(int el,int idx){
        while(idx > list.size())
            list.add(0);
        list.add(idx,el);
     }

     public int remove(int idx){
        return list.remove(idx);
     }

     public void set(int el,int idx){
        list.set(idx,el);
     }

     public int get(int idx){
        return list.get(idx);
     }

     public int size(){
        return list.size();
     }

     public int count(int el){
        return (int)list.stream().filter(t -> t.equals(el)).count();
     }

      public void removeDuplicates(){
         list = (ArrayList<Integer>)list.stream().distinct().collect(Collectors.toList());
      }

      public int sumFirst(int k){
        if(k>list.size()-1)
            throw new ArrayIndexOutOfBoundsException();
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=list.get(i);
        }
        return sum;
      }

      public int sumLast(int k){
        int sum=0;
        for(int i=list.size()-1;i>=k;i--){
            sum+=list.get(i);
        }
        return sum;
      }

      public void shiftRight(int idx,int k){
        shift(idx,k);
      }
      public void shiftLeft(int idx,int k){
        shift(idx,-k);
      }

      private void shift(int idx,int k){
        int new_pos = ((idx+k)%list.size()+list.size())%list.size();
        add(remove(idx),new_pos);
      }

      public IntegerList addValue(int value){
        Integer[] newList = new Integer[list.size()];
          for ( int i=0;i<newList.length;i++ ) {
              newList[i]=list.get(i)+value;
          }
          return new IntegerList(newList);
      }

}