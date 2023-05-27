import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        try {
            f1Race.readResults(System.in);
        } catch ( InterruptedException | IOException e ) {
            throw new RuntimeException(e);
        }
        f1Race.printSorted(System.out);
    }

}

class Driver{
    String name;
    String[] laps;

    public Driver( String name, String[] laps ) {
        this.name=name;
        this.laps=laps;
    }

    @Override
    public String toString(){
        return String.format("%-10s%10s",name,laps[0]);
    }
}
class F1Race {
    List<Driver> drivers;

    public F1Race() {
        this.drivers = new LinkedList<>();
    }

    public void createDriver(String name, String[] laps){
        Arrays.sort(laps);
        drivers.add(new Driver(name,laps));
    }

    public void readResults( InputStream inputStream ) throws InterruptedException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = reader.lines().collect(Collectors.toList());
        for ( String line: lines ) {
            String[] parts = line.split("\\s+");
            String name = parts[0];
            String[] laps;
            laps =Arrays.stream(parts).skip(1).toArray(String[]::new);
            createDriver(name,laps);
        }
        drivers.sort(Comparator.comparing(driver -> driver.laps[0]));
        reader.close();
    }

    public void printSorted( OutputStream outputStream ){
        PrintWriter pw = new PrintWriter(outputStream);
        int it=1;
        for ( Driver driver: drivers ) {
            pw.write(it++ + ". " + driver.toString() + '\n');
        }
        pw.flush();
        pw.close();
    }
}