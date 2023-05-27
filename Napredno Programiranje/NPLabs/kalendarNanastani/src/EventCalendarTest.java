import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

class WrongDateException extends Exception{
    public WrongDateException(String msg){
        super(msg);
    }
}


class Event{
    String name;
    String location;
    Date date;

    public Event( String name, String location, Date date ) {
        this.name=name;
        this.location=location;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }


    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyy HH:mm");
        sdf.format(date);
        return sdf.format(date)
                + " at "
                + location
                + ", "
                + name;

    }
}
class EventCalendar{
    int year;
    Map<Date,Event> eventMap = new LinkedHashMap<>();

    public EventCalendar( int year ) {
        this.year=year;
    }

    public void addEvent(String name,String location,Date date) throws WrongDateException {
        if(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()!=year)
            throw new WrongDateException("Wrong date: " + date.toString());
        eventMap.put(date,new Event(name,location,date));
    }

    public void listEvents(Date date){
        /*List<Event> events = */
        eventMap.values().stream()
                .filter(
                        event -> event.date.getMonth()==date.getMonth()
                && event.date.getDay()==date.getDay())
                .sorted(
                        Comparator.comparing(Event::getDate)
                        //.reversed()
                        .thenComparing(Event::getName))
                .forEach(System.out::println);

    }

    public void listByMonth(){
        for ( int i=0; i < 12; i++ ) {
            int finalI=i;
            System.out.println((i+1)+" : " +
            (int)eventMap.values().stream()
                    .filter(event -> {
                        return event.date/*.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()*/.getMonth() == finalI;
                    }).count()
            );
        }
    }
}

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}

