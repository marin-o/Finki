import org.w3c.dom.Node;

import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class DLLVojska {

    public static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {


        DLLNode<Integer> temp1=lista.getFirst();


        DLLNode<Integer> firstIntervalStart = null;
        DLLNode<Integer> firstIntervalEnd = null;

        DLLNode<Integer> secondIntervalStart = null;
        DLLNode<Integer> secondIntervalEnd = null;


        while(temp1!=null){
            if ( temp1.element.equals(a) ) {
                firstIntervalStart=temp1;
            }
            if ( temp1.element.equals(b) ) {
                firstIntervalEnd=temp1;
            }
            if ( temp1.element.equals(c) ) {
                secondIntervalStart=temp1;
            }
            if ( temp1.element.equals(d) ) {
                secondIntervalEnd=temp1;
            }
            temp1=temp1.succ;
        }


        //if first interval starts at the first listnode, make new first the start of 2nd interval
        if(firstIntervalStart == lista.getFirst())
            lista.setFirst(secondIntervalStart);

        //if second interval ends at last listnode, make new last node the end of first interval
        if(secondIntervalEnd == lista.getLast())
            lista.setLast(firstIntervalEnd);

        //swapping
        DLLNode<Integer> temp;

        //first swap the ends/successors of each interval
        temp=firstIntervalEnd.succ;
        firstIntervalEnd.succ=secondIntervalEnd.succ;
        secondIntervalEnd.succ=temp;

        //if first interval's new successor is not null,
        //we need to tell it that its new predecessor is the end of the first interval
        if(firstIntervalEnd.succ != null)
            firstIntervalEnd.succ.pred = firstIntervalEnd;

        //same thing but for 2nd interval
        if(secondIntervalEnd.succ != null)
            secondIntervalEnd.succ.pred = secondIntervalEnd;

        //then swap the starts/predecessors of each interval
        temp = firstIntervalStart.pred;
        firstIntervalStart.pred=secondIntervalStart.pred;
        secondIntervalStart.pred=temp;

        //if the first interval's new predecessor is not null,
        //we need to tell that its new successor is the beginning of the first interval
        if(firstIntervalStart.pred != null)
            firstIntervalStart.pred.succ = firstIntervalStart;

        //same but for 2nd interval
        if(secondIntervalStart.pred != null)
            secondIntervalStart.pred.succ = secondIntervalStart;


        return lista;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> lista = new DLL<>();
        for(int i=0;i<n;i++) {
            lista.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        int c = input.nextInt();
        int d = input.nextInt();

        DLL<Integer> result = vojska(lista, a, b, c, d);

        System.out.println(result);

    }
}