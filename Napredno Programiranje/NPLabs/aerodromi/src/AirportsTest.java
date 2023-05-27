import java.util.*;

class Flight implements Comparable<Flight>{
    String from,to;
    int time,duration;

    public Flight( String from, String to, int time, int duration ) {
        this.from=from;
        this.to=to;
        this.time=time;
        this.duration=duration;
    }

    @Override
    public int compareTo( Flight o ) {
        int x = this.to.compareTo(o.to);
        if(x==0){
            return Integer.compare(this.time,o.time);
        }
        return x;
    }
}

class Airport{
    String name,country,code;
    int passengers;
    Set<Flight> flights = new TreeSet<>();
    public Airport( String name, String country, String code, int passengers ) {
        this.name=name;
        this.country=country;
        this.code=code;
        this.passengers=passengers;
    }

    public void addFlight(String from, String to, int time, int duration){
        flights.add(new Flight(from, to, time, duration));
    }

    public void showFlights(){
        System.out.printf("%s (%s)%n%s%n%d%n",name,code,country,passengers);
        int i = 1;
        for ( Flight flight : flights ) {
            System.out.printf("%d. ",i++);
            showFlight(flight);
            }
    }
    public void showFlight( Flight flight ){
            int untilTime = flight.time + flight.duration;
            boolean nextDay = false;
            if(untilTime>1440) {
                untilTime-=1440;
                nextDay=true;
            }
            String dur = "";
            if(nextDay)
                dur+="+1d ";
            System.out.printf("%s-%s %02d:%02d-%02d:%02d %s%dh%02dm%n",
                    flight.from,
                    flight.to,
                    flight.time/60,
                    flight.time%60,
                    untilTime/60,
                    untilTime%60,
                    dur,
                    flight.duration/60,
                    flight.duration%60
            );
    }
    public void showDirectFlightsFromTo(String from,String to) {
        boolean atLeast1 = false;
        for ( Flight flight: flights ) {
            if ( flight.from.equals(from) && flight.to.equals(to) ) {
                showFlight(flight);
                atLeast1=true;
            }
        }
        if(!atLeast1)
            System.out.printf("No flights from %s to %s%n",from,to);
    }

    public void showDirectFlightsTo( String to ) {
        for ( Flight flight: flights ) {
            if ( flight.to.equals(to) )
                showFlight(flight);
        }
    }
}
class Airports{
    Map<String,Airport> airports = new TreeMap<>();

    public void addAirport(String name, String country, String code, int passengers){
        airports.put(code,new Airport(name,country,code,passengers));
    }
    public void addFlights(String from, String to, int time, int duration){
        Airport airport = airports.get(from);
        airport.addFlight(from,to,time,duration);
    }

    public void showFlightsFromAirport(String code){
        Airport airport = airports.get(code);
        airport.showFlights();
    }

    public void showDirectFlightsFromTo(String from,String to){
        Airport airport = airports.get(from);
        airport.showDirectFlightsFromTo(from,to);
    }
    
    public void showDirectFlightsTo(String to){
        for ( Airport airport: airports.values() ) {
            airport.showDirectFlightsTo(to);
        }
    }
}



public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

