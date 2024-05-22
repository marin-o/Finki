package homework;

import org.junit.jupiter.api.Test;

import static homework.Conference.*;
import static homework.Conference.TICKET_PRICE;
import static homework.Course.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;



public class ConferenceTest {

    // TODO: Add tests here!



    @Test
    public void totalPricePaidEMT(){
        Student s = new Student("ime","prezime",EMT, 21);
        Conference c = new Conference(1);
        c.addAttendeeToConference(s);
        double price = c.calculateTotalPricePaid();
        assertEquals((1 - EMT_DISCOUNT) * TICKET_PRICE,price);
    }

    @Test
    public void totalPricePaidWEB(){
        Student s = new Student("ime","prezime",WEB, 21);
        Conference c = new Conference(1);
        c.addAttendeeToConference(s);
        double price = c.calculateTotalPricePaid();
        assertEquals((1 - WEB_DISCOUNT) * TICKET_PRICE,price);
    }

    @Test
    public void totalPricePaidOther(){
        Student s = new Student("ime","prezime", OTHER, 21);
        Conference c = new Conference(1);
        c.addAttendeeToConference(s);
        double price = c.calculateTotalPricePaid();
        assertEquals(TICKET_PRICE,price);
    }

    @Test
    public void addAttendeeToConferenceMoreThanCapacity() {
        Student s = new Student("ime", "prezime", EMT, 21);
        Conference c = new Conference(1);
        c.addAttendeeToConference(s);
        c.addAttendeeToConference(s);
        assertEquals(2, c.getAttendees().size());
    }


    @Test
    public void addAttendeeToConferenceEnoughSpace(){
        Student s = new Student("ime","prezime",EMT,21);
        Conference c = new Conference(5);

        assertTrue(c.addAttendeeToConference(s));
    }

    @Test
    public void addAttendeeToConferenceCannotIncreaseCapacity(){
        Student s = new Student("ime","prezime",EMT,21);
        Conference c = new Conference(10000);
        for (int i = 0; i < 10000; i++) {
            c.addAttendeeToConference(s);
        }
        assertFalse(c.addAttendeeToConference(s));
    }

    @Test
    public void tripleCapacityMultiplicatioSwapped() {
        Student s = new Student("ime", "prezime", EMT, 21);
        int capacity = 3;
        Conference c = new Conference(capacity);
        c.addAttendeeToConference(s);
        c.addAttendeeToConference(s);
        c.addAttendeeToConference(s);
        c.addAttendeeToConference(s);
        assertEquals(capacity*3,c.getCapacity());
    }


    @Test
    public void getAttendeesAndGetCapacity(){
        Student s = new Student("ime","prezime", OTHER, 21);
        Conference c = new Conference(1);
        c.addAttendeeToConference(s);
        assertEquals(c.getCapacity(), c.getAttendees().size());
    }


}