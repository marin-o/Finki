package homework;

import java.util.ArrayList;
import java.util.List;

public class Conference {

    public static double TICKET_PRICE = 10.90;
    public static double EMT_DISCOUNT = 0.5;
    public static double WEB_DISCOUNT = 0.3;

    private List<Student> attendees;
    private int capacity;

    public Conference(int capacity) {
        this.attendees = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public double calculateTotalPricePaid() {
        double price = 0;

        for (Student p : attendees) {
            if (p.getCourse().name().equals("EMT"))
                price += (1 - EMT_DISCOUNT) * TICKET_PRICE;
            else if (p.getCourse().name().equals("WEB"))
                price += (1 - WEB_DISCOUNT) * TICKET_PRICE;
            else
                price += TICKET_PRICE;
        }
        return price;
    }

    public boolean addAttendeeToConference(Student student) {
        if ((this.attendees.size() < capacity) || tripleCapacity()) {
            this.attendees.add(student);
            return true;
        } else return false;
    }

    public boolean tripleCapacity() {
        if (this.capacity * 3 > 10000)
            return false;
        this.capacity *= 3;
        return true;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public int getCapacity() {
        return capacity;
    }
}
