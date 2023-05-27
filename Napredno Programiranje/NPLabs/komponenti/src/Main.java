import javax.management.InstanceNotFoundException;
import java.util.*;

class Component implements Comparable<Component>{
    String color;
    int weight;
    Set<Component> components = new TreeSet<>();

    public Component( String color, int weight ) {
        this.color=color;
        this.weight=weight;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public void addComponent( Component component){
        components.add(component);
    }

    @Override
    public int compareTo( Component o ) {
        int w = this.weight - o.weight;
        if(w == 0) return this.color.compareTo(o.color);
        return w;
    }

    public void changeColor(int weight,String color){
        if(this.weight<weight){
            this.color=color;
        }
        for ( Component comp: components ) {
            change(comp, weight, color);
        }
    }

    private void change(Component c,int weight,String color){
        if(c.weight<weight){
            c.color=color;
        }
        for ( Component comp: c.components ) {
            change(comp, weight, color);
        }
    }
    public static void append(StringBuilder sb,int level,Component component){
        for ( int i=0; i < level; i++ ) {
            sb.append("---");

        }
        sb.append(String.format("%d:%s\n",component.weight,component.color));
        for ( Component composite: component.components ) {
            append(sb,level+1,composite);
        }
    }
}

class InvalidPositionException extends Exception {
    public InvalidPositionException(int position) {
        super("Invalid position "+position+", already taken!");
    }
}
class Window{
    String name;
    Map<Integer,Component> components = new TreeMap<>();

    public Window( String name ) {
        this.name=name;
    }

    public void addComponent(int position, Component component) throws InvalidPositionException {
        if(components.containsKey(position))
            throw new InvalidPositionException(position);
        components.put(position,component);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("WINDOW " + name);
        sb.append("\n");
        for( Map.Entry<Integer,Component> entry : components.entrySet()){
            sb.append(String.format("%d:",entry.getKey()));
            Component.append(sb,0,entry.getValue());
        }
        return sb.toString();
    }

    public void changeColor(int weight,String color){
        components.values().forEach(comp -> comp.changeColor(weight,color));
    }

    public void switchComponents(int pos1,int pos2){
        Component temp = components.get(pos1);
        components.put(pos1,components.get(pos2));
        components.put(pos1,temp);
    }
}
public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.switchComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде