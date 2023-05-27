import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

abstract class Contact{
    String date;


    public Contact( String date){
        this.date=date;
    }

    public String getDate() {
        return date;
    }
    public boolean isNewerThan(Contact c) {
        return date.compareTo(c.date) > 0;
    }

    @Override
    public abstract String toString();
    abstract String getType();
}

class EmailContact extends Contact{
    String email;

    public EmailContact( String date, String email ) {
        super(date);
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "\"" + email + '\"';
    }

    @Override
    String getType() {
        return "Email";
    }
}
enum Operator{VIP,ONE,TMOBILE;}

class PhoneContact extends Contact{
    String phone;
    Operator operator;

    public PhoneContact( String date, String phone ) {
        super(date);
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public Operator getOperator(){
        char operator = phone.charAt(2);
        switch ( operator ) {
            case '0':
            case '1':
            case '2':
                return Operator.TMOBILE;
            case '5':
            case '6':
                return Operator.ONE;
            case '7':
            case '8':
                return Operator.VIP;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "\"" + phone + '\"';
    }

    @Override
    String getType() {
        return "Phone";
    }
}

class Student{
    Contact[] contacts;
    String firstName,lastName;
    String city;
    int age;
    long index;

    public Student( String firstName, String lastName, String city, int age, long index ) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.city=city;
        this.age=age;
        this.index=index;
        this.contacts=new Contact[0];
    }

    public String getCity() {
        return city;
    }

    public long getIndex() {
        return index;
    }

    public String getFullName(){
        return firstName.toUpperCase()+" "+lastName.toUpperCase();
    }

    public void addEmailContact(String date,String email){
        ArrayList<Contact> contacts1 =
                new ArrayList<Contact>(Arrays.asList(contacts));
        contacts1.add(new EmailContact(date,email));
        contacts=contacts1.toArray(contacts);
    }
    public void addPhoneContact(String date,String phone){
        ArrayList<Contact> contacts1 =
                new ArrayList<Contact>(Arrays.asList(contacts));
        contacts1.add(new PhoneContact(date,phone));
        contacts=contacts1.toArray(contacts);
    }

    public Contact[] getEmailContacts() {
        ArrayList<Contact> emailContacts= new ArrayList<>();
        for ( Contact c: contacts ) {
            if(c.getType().equals("Email")) emailContacts.add(c);
        }
        return emailContacts.toArray(new Contact[emailContacts.size()]);
    }
    public Contact[] getPhoneContacts() {
        ArrayList<Contact> phoneContacts= new ArrayList<>();
        for ( Contact c: contacts ) {
            if(c.getType().equals("Phone")) phoneContacts.add(c);
        }
        return  phoneContacts.toArray(new Contact[phoneContacts.size()]);
    }

    public Contact getLatestContact() {
        Contact c=contacts[0];
        for ( Contact contact: contacts ) {
            if ( contact.isNewerThan(c) )
                c=contact;
        }
        return c;
    }

    public int getContactsNumber(){
        return contacts.length;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ime\":\"" + firstName + '\"' +
                ", \"prezime\":\"" + lastName + '\"' +
                ", \"vozrast\":" + age +
                ", \"grad\":\"" + city + '\"' +
                ", \"indeks\":" + index +
                ", \"telefonskiKontakti\":" + Arrays.toString(getPhoneContacts()) +
                ", \"emailKontakti\":" + Arrays.toString(getEmailContacts()) +
                '}';
    }
}

class Faculty{
    String name;
    Student students[];

    public Faculty( String name, Student[] students ) {
        this.name=name;
        this.students=students;
    }

    public int countStudentsFromCity(String cityName){
        int count=0;
        for ( Student student: students ) {
            if(cityName.equals(student.getCity())) count++;
        }
        return count;
    }

    public Student getStudent(long index){
        for ( Student student: students ) {
            if(index==student.getIndex()) return student;
        }
        return null;
    }

    public double getAverageNumberOfContacts(){
        int sum=0;
        for ( Student student: students ) {
            sum+=student.getContactsNumber();
        }
        return (double)sum/(double)students.length;
    }

    public Student getStudentWithMostContacts(){
        int najmn=0;
        for(int i=0;i<students.length;i++){
            if(students[i].getContactsNumber()>students[najmn].getContactsNumber())
                najmn=i;
            else if(students[i].getContactsNumber()==students[najmn].getContactsNumber())
                najmn=students[i].getIndex() > students[najmn].getIndex() ? i : najmn;
        }

        return students[najmn];
    }

    @Override
    public String toString() {
        return "{" +
                "\"fakultet\":\"" + name + '\"' +
                ", \"studenti\":" + Arrays.toString(students) +
                "}";
    }
}

public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}
