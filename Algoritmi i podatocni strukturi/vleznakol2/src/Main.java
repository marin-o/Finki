import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void print( Hashtable<?, ?> table, Map<?, ?> map ){
        map.values()
                .forEach(value -> System.out.println(table.get(value)));
    }
    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Hashtable<String,String> table = new Hashtable<>();
        for ( int i=0; i < n; i++ ) {
            String[] line = br.readLine().split("\\s+",2);
            table.put(line[0],line[1]);
        }
        int speedLimit = Integer.parseInt(br.readLine());
        String[] report = br.readLine().split("\\s+");
        int iterator = 0;
        Map<LocalTime,String> speeders = new TreeMap<>();
        for ( int i=0; i < report.length / 3; i++ ) {
            String reg = report[iterator++];
            int speed = Integer.parseInt(report[iterator++]);
            LocalTime time = LocalTime.parse(report[iterator++]);
            //System.out.println(time);
            if(speed>speedLimit){
                speeders.put(time,reg);
            }
        }
        print(table,speeders);
    }
}