import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * I partial exam 2016
 */
public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}

class Day{
    LinkedList<String> temperatures;
    char initialScale;
    int day;

    public Day( LinkedList<String > temperatures, int day, String initialScale ) {
        this.temperatures=temperatures;
        this.day=day;
        this.initialScale=initialScale.charAt(0);
    }



    public double getC(int temp){
        if(initialScale == 'C') return temp;
        return (temp-32.0)*5.0/9.0;
    }

    public double getF(int temp){
        if(initialScale == 'F') return temp;
        return temp*9.0/5.0 + 32.0;
    }
}

class DailyTemperatures{
    LinkedList<Day> days;

    public DailyTemperatures(){
        days = new LinkedList<>();
    }

    private void createDay(String line){
        String[] parts = line.split("\\s+");
        int day = Integer.parseInt(parts[0]);
        String initialScale =String.valueOf(parts[1].charAt(parts[1].length()-1));
        LinkedList<String> temperatures=new LinkedList<>(Arrays.stream(parts)
                .skip(1)
                .map(s -> s.replace(initialScale,"")).collect(Collectors.toList()));


        days.add(new Day(temperatures,day,initialScale));
    }
    public void readTemperatures( InputStream inputStream ) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        reader.lines().forEach(this::createDay);

        reader.close();
    }

    public void writeDailyStats( OutputStream outputStream, char scale ){
        PrintWriter pw = new PrintWriter(outputStream);



        pw.flush();
        pw.close();
    }
}