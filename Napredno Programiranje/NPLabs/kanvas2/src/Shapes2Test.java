import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

class IrregularCanvasException extends Exception{
    public IrregularCanvasException( String message ) {
        super(message);
    }

}

class Shape{
    int size;
    char type;

    public Shape( char type, int size ) {
        this.type=type;
        this.size=size;
    }

    public double getArea(){
        return type == 'c' ? Math.PI*Math.pow(size,2) : Math.pow(size,2);
    }

    public char getType() {
        return type;
    }

}

class Canvas implements Comparable<Canvas>{

    LinkedList<Shape> shapes;
    String id;

    public Canvas( String id, LinkedList<Shape> shapes ) {
        this.shapes=shapes;
        this.id=id;
    }

    public double totalArea(){
        double total=0;
        for ( Shape shape: shapes ) {
            total+=shape.getArea();
        }
        return total;
    }

    public void addShape(int size, char type){
        shapes.add(new Shape(type,size));
    }

    public int totalShapes(){
        return shapes.size();
    }

    public int totalCircles(){
        return (int)shapes.stream().filter(shape -> shape.getType() == 'C').count();
    }
    public int totalSquares(){
        return (int)shapes.stream().filter(shape -> shape.getType() == 'S').count();
    }

    public double getMinArea(){
        int ind=0;
        for(int i=1; i<shapes.size();i++){
            if(shapes.get(i).getArea()<shapes.get(ind).getArea())
                ind = i;
        }
        return shapes.get(ind).getArea();
    }

    public double getMaxArea(){
        int ind=0;
        for(int i=1; i<shapes.size();i++){
            if(shapes.get(i).getArea()>shapes.get(ind).getArea())
                ind = i;
        }
        return shapes.get(ind).getArea();
    }

    public double getAverageArea(){
        return shapes.stream().mapToDouble(Shape::getArea).sum()/totalShapes();
    }

    @Override
    public int compareTo( Canvas o ) {
        double area1= this.totalArea();
        double area2= o.totalArea();

        if(area1==area2) return 0;
        return area1<area2 ? -1 : 1;
    }

    @Override
    public String toString(){
        return String.format("%s %d %d %d %.2f %.2f %.2f\n",
                id,
                totalShapes(),
                totalCircles(),
                totalSquares(),
                getMinArea(),
                getMaxArea(),
                getAverageArea());
    }

}

class ShapesApplication{
    double maxArea;
    LinkedList<Canvas> canvases;

    public ShapesApplication( double maxArea ) {
        this.maxArea=maxArea;
        canvases = new LinkedList<>();
    }

    public void readCanvases( InputStream inputStream ) throws IrregularCanvasException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        for ( String line: reader.lines().collect(Collectors.toList()) ) {
            String[] parts = line.split("\\s+");
            String id = parts[0];
            LinkedList<Shape> shapes = new LinkedList<>();
            for(int i=1;i< parts.length;i++){
                char type = parts[i++].charAt(0);
                int size = Integer.parseInt(parts[i]);
                Shape newShape = new Shape(type,size);
                if(newShape.getArea()>maxArea) throw new IrregularCanvasException("Canvas " + id + " has a shape with area largen than " + maxArea);
                shapes.add(newShape);
            }
            canvases.add(new Canvas(id, shapes));
            canvases.sort(Comparator.reverseOrder());
        }
    }

    public void printCanvases( OutputStream outputStream ){
        PrintWriter pw = new PrintWriter(outputStream);

        for ( Canvas canvas: canvases ) {
            pw.write(canvas.toString());
        }

        pw.flush();
        pw.close();
    }
}

public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        try {
            shapesApplication.readCanvases(System.in);
        } catch ( IrregularCanvasException e ) {
            e.getMessage();
        }

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}