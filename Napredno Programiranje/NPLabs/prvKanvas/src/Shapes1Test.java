import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Canvas implements Comparable<Canvas>{
    String id;
    int[] squares;

    public Canvas( String id, int[] squares ) {
        this.id=id;
        this.squares=squares;
    }

    public int perimeter(){
        return Arrays.stream(squares).sum()*4;
    }

    @Override
    public String toString(){
        return id + " " + squares.length + " " + perimeter();
    }

    @Override
    public int compareTo( Canvas o ) {
        int p1 = this.perimeter();
        int p2 = o.perimeter();
        if(p1==p2) return 0;
        return p1<p2 ? -1 : 1;
    }
}

class ShapesApplication{

    LinkedList<Canvas> canvases;
    public ShapesApplication() {
        canvases = new LinkedList<Canvas>();
    }

    private void createCanvas(String id,int[] squares){
        canvases.add(new Canvas(id, squares));
    }

    private Canvas maxCanvas(){
        /*int ind=0;
        for(int i=1;i<canvases.size();i++){
            if(canvases.get(i).perimeter()>canvases.get(ind).perimeter())
                ind=i;
        }
        return canvases.get(ind);*/
        return canvases.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    public int readCanvases( InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int count=0;
        for ( String line: reader.lines().collect(Collectors.toList()) ) {
            String[] parts=line.split("\\s+");
            String id = parts[0];
            int[] squares = Arrays.stream(parts)
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            count+=squares.length;
            createCanvas(id,squares);
        }
        reader.close();
        return count;
    }

    public void printLargestCanvasTo(OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(maxCanvas().toString());
        pw.flush();
        pw.close();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        try {
            System.out.println(shapesApplication.readCanvases(System.in));
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}