import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException() {
        super("Invalid extra type");
    }

}
class InvalidPizzaTypeException extends Exception{
    public InvalidPizzaTypeException() {
        super("Invalid pizza type");
    }
}

class ItemOutOfStockException extends Exception{
    Item item;
    public ItemOutOfStockException(Item item){
        this.item = item;
    }
    @Override
    public String getMessage(){
        return item.toString() + " is out of stock";
    }
}

class EmptyOrder extends Exception{
    public EmptyOrder(){super("This order is empty");}
}

class OrderLockedException extends Exception{
    public OrderLockedException(){super("This order is locked");}
}

interface Item{
    int getPrice();
}

class ExtraItem implements Item{

    String type;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if( !type.equals("Coke") && !type.equals("Ketchup") )
            throw new InvalidExtraTypeException();
        this.type=type;
    }

    @Override
    public int getPrice() {
        if( type.equals("Ketchup") ) return 3;
        return 5;
    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ExtraItem extraItem=(ExtraItem) o;
        return Objects.equals(type, extraItem.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

class PizzaItem implements Item{
    String type;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if( !type.equals("Standard") && !type.equals("Pepperoni") && !type.equals("Vegetarian") )
            throw new InvalidPizzaTypeException();
        this.type=type;
    }
    @Override
    public int getPrice() {
        switch(type){
            case "Standard": return 10;
            case "Pepperoni":return 12;
            default: return 8;
        }
    }
    @Override
    public String toString(){
        return type;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        PizzaItem pizzaItem=(PizzaItem) o;
        return Objects.equals(type, pizzaItem.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

class Order{
    ArrayList<Item> items;
    ArrayList<Integer> counts;
    boolean isLocked = false;
    public Order(){
        items = new ArrayList<Item>();
        counts = new ArrayList<Integer>();
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(isLocked){
            throw new OrderLockedException();
        }
        if(count > 10)
            throw new ItemOutOfStockException(item);
        int index = items.indexOf(item);
        if(index==-1){
            items.add(item);
            counts.add(count);
        }
        else{
            counts.set(index,count);
        }

    }

    public int getPrice(){
        int price=0;
        for(int i=0;i<items.size();i++)
            price+=items.get(i).getPrice()*counts.get(i);

        return price;
    }

    public void displayOrder(){
        for ( int i=0;i<items.size();i++ ) {
        System.out.printf("%3d.%-14s x%2d%5d$\n",i+1,items.get(i).toString(),counts.get(i),items.get(i).getPrice()*counts.get(i));
        }
        System.out.printf("%-22s%5d$\n","Total:",getPrice());
    }

    public void removeItem(int idx) throws OrderLockedException {
        if(isLocked){
            throw new OrderLockedException();
        }
        if(!items.remove(items.get(idx))){
            throw new ArrayIndexOutOfBoundsException(idx);
        }
        counts.remove(idx);
    }

    public void lock() throws EmptyOrder {
        if(items.isEmpty()){
            throw new EmptyOrder();
        }
        isLocked=true;
    }
}

public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}