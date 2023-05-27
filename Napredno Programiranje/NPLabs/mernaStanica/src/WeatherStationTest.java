import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Measurement implements Comparable<Measurement>{
    float temperature,wind,humidity,visibility;
    Date date;

    public Measurement( float temperature, float wind, float humidity, float visibility, Date date ) {
        this.temperature=temperature;
        this.wind=wind;
        this.humidity=humidity;
        this.visibility=visibility;
        this.date=date;
    }

    public long getTime(){
        return date.getTime();
    }

    @Override
    public int compareTo( Measurement o ) {
        return this.date.compareTo(o.date);
    }

    public void print(){
        System.out.printf("%.1f %.1f km/h %.1f%% %.1f km %s\n",
                temperature,
                wind,
                humidity,
                visibility,
                date.toString());
    }
}

class WeatherStation{
    int maxDays;
    LinkedList<Measurement> measurements;
    public WeatherStation( int maxDays ) {
        this.maxDays=maxDays;
        measurements = new LinkedList<Measurement>();
    }

    public void addMeasurement(float temperature, float wind, float humidity,float visibility,Date date){
        long newTime = date.getTime();
        measurements.removeIf(measurement -> measurement.getTime() > (long) maxDays *24*60*60*1000);
        measurements.add(new Measurement(temperature,wind,humidity,visibility,date));
        measurements.sort(Comparator.naturalOrder());
    }

    public int total(){
        return measurements.size();
    }

    public void status(Date from,Date to){
        LinkedList<Measurement> filtered = measurements.stream().filter(measurement -> measurement.date.after(from) && measurement.date.before(to)).sorted().collect(Collectors.toCollection(LinkedList::new));
        if(filtered.size() == 0)
            throw new RuntimeException();
        else {
            for ( Measurement measurement: measurements ) {
                measurement.print();
            }
        }
    }
}

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurement(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

// vashiot kod ovde