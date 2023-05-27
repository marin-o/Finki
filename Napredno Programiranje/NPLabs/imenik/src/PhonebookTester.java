import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
class InvalidNameException extends Exception{
    String why;
    public InvalidNameException(String why){
        this.why = why;
    }
}

class InvalidNumberException extends Exception{
    String why;
    public InvalidNumberException( String why ) {
        this.why = why;
    }
}

class MaximumSizeExceededException extends Exception{
    String why;
    public MaximumSizeExceededException(String why){
        this.why = why;
    }
}

class Contact{
    String name;
    String[] phoneNumbers;

    public Contact( String name, String[] phoneNumbers ) throws InvalidNameException, InvalidNumberException, MaximumSizeExceededException {
        if(name.length()<4 || name.length()>10)
            throw new InvalidNameException("length");
        if(!name.matches("/^[A-Za-z0-9]*$/"))
            throw new InvalidNameException("illegalCharacters");
        if(phoneNumbers.length>5)
            throw new MaximumSizeExceededException("tooMany");
        for ( String number: phoneNumbers ) {
            if( number.length() != 9 )
                throw new InvalidNumberException("length");
            if(!number.startsWith("070") || !number.startsWith("071") || !number.startsWith("072") || !number.startsWith("075") || !number.startsWith("076") || !number.startsWith("077") || !number.startsWith("078"))
                throw new InvalidNumberException("startsWith");
        }

        this.name=name;
        this.phoneNumbers=(String[]) Arrays.stream(phoneNumbers).sorted().toArray();
    }

    public Contact( String name ) {
        this.name = name;
        this.phoneNumbers = new String[0];
    }

    public String getName(){
        return name;
    }

    public String[] getPhoneNumbers() {
        return Arrays.copyOf(phoneNumbers,phoneNumbers.length);
    }

    public void addNumber(String phonenumber) throws MaximumSizeExceededException, InvalidNumberException {
        if(phoneNumbers.length+1>5) throw new MaximumSizeExceededException("tooMany");
        if( phonenumber.length() != 9 )
            throw new InvalidNumberException("length");
        if(!phonenumber.startsWith("070") || !phonenumber.startsWith("071") || !phonenumber.startsWith("072") || !phonenumber.startsWith("075") || !phonenumber.startsWith("076") || !phonenumber.startsWith("077") || !phonenumber.startsWith("078"))
            throw new InvalidNumberException("startsWith");
        this.phoneNumbers = Arrays.copyOf(phoneNumbers,phoneNumbers.length+1);
        this.phoneNumbers[phoneNumbers.length-1] = phonenumber;
        this.phoneNumbers=(String[]) Arrays.stream(phoneNumbers).sorted().toArray();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(name + "\n");
        sb.append(phoneNumbers.length + "\n");
        for ( String number: phoneNumbers ) {
            sb.append(number.toString() + "\n");
        }

        return sb.toString();
    }

    public static Contact valueOf(String s) throws MaximumSizeExceededException, InvalidNameException, InvalidNumberException {
        System.err.println(s);
        //TODO valueOf
        return new Contact("","haha haha haha".split(" "));
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Contact contact=(Contact) o;
        return Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        int result=Objects.hash(name);
        result=31 * result + Arrays.hashCode(phoneNumbers);
        return result;
    }
}

class PhoneBook{
    Contact[] contacts;

    public PhoneBook(){
        contacts = new Contact[0];
    }

    public void addContact(Contact contact) throws MaximumSizeExceededException, InvalidNameException {
        if(contacts.length+1>250)
            throw new MaximumSizeExceededException("tooManyContacts");
        if( Arrays.asList(contacts).contains(contact) )
            throw new InvalidNameException("contactExists");
        contacts = Arrays.copyOf(contacts,contacts.length+1);
        contacts[contacts.length-1] = contact;
    }

    public Contact getContactForName(String name){
        return Arrays.stream(contacts)
                .filter(data -> data.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public int numberOfContacts(){
        return contacts.length;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public boolean removeContact(String name) throws MaximumSizeExceededException, InvalidNameException, InvalidNumberException {
        return Arrays.asList(contacts).remove(new Contact(name));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String[] iminja = new String[contacts.length];
        for ( int i=0; i<contacts.length; i++){
            iminja[i]=contacts[i].getName();
        }
        Arrays.sort(iminja);
        for ( String contact: iminja ) {
            sb.append(contact + " ");
        }
        return sb.toString();
    }

    public boolean saveAsTextFile(PhoneBook phonebook,String path) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(path));
        } catch ( IOException e ) {
            return false;
        }
        for ( Contact contact: contacts ) {
            try {
                writer.write(contact.toString());
            } catch ( IOException e ) {
                return false;
            }
        }
        return true;
    }

    public PhoneBook loadFromTextFile(String path){

    }
}

public class PhonebookTester {

    public static void main(String[] args) throws Exception {
        Scanner jin = new Scanner(System.in);
        String line = jin.nextLine();
        switch( line ) {
            case "test_contact":
                testContact(jin);
                break;
            case "test_phonebook_exceptions":
                testPhonebookExceptions(jin);
                break;
            case "test_usage":
                testUsage(jin);
                break;
        }
    }

    private static void testFile(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() )
            phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
        String text_file = "phonebook.txt";
        PhoneBook.saveAsTextFile(phonebook,text_file);
        PhoneBook pb = PhoneBook.loadFromTextFile(text_file);
        if ( ! pb.equals(phonebook) ) System.out.println("Your file saving and loading doesn't seem to work right");
        else System.out.println("Your file saving and loading works great. Good job!");
    }

    private static void testUsage(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() ) {
            String command = jin.nextLine();
            switch ( command ) {
                case "add":
                    phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
                    break;
                case "remove":
                    phonebook.removeContact(jin.nextLine());
                    break;
                case "print":
                    System.out.println(phonebook.numberOfContacts());
                    System.out.println(Arrays.toString(phonebook.getContacts()));
                    System.out.println(phonebook.toString());
                    break;
                case "get_name":
                    System.out.println(phonebook.getContactForName(jin.nextLine()));
                    break;
                case "get_number":
                    System.out.println(Arrays.toString(phonebook.getContactsForNumber(jin.nextLine())));
                    break;
            }
        }
    }

    private static void testPhonebookExceptions(Scanner jin) {
        PhoneBook phonebook = new PhoneBook();
        boolean exception_thrown = false;
        try {
            while ( jin.hasNextLine() ) {
                phonebook.addContact(new Contact(jin.nextLine()));
            }
        }
        catch ( InvalidNameException e ) {
            System.out.println(e.name);
            exception_thrown = true;
        }
        catch ( Exception e ) {}
        if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw InvalidNameException");
        /*
		exception_thrown = false;
		try {
		phonebook.addContact(new Contact(jin.nextLine()));
		} catch ( MaximumSizeExceddedException e ) {
			exception_thrown = true;
		}
		catch ( Exception e ) {}
		if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw MaximumSizeExcededException");
        */
    }

    private static void testContact(Scanner jin) throws Exception {
        boolean exception_thrown = true;
        String names_to_test[] = { "And\nrej","asd","AAAAAAAAAAAAAAAAAAAAAA","Ð�Ð½Ð´Ñ€ÐµÑ˜A123213","Andrej#","Andrej<3"};
        for ( String name : names_to_test ) {
            try {
                new Contact(name);
                exception_thrown = false;
            } catch (InvalidNameException e) {
                exception_thrown = true;
            }
            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNameException");
        }
        String numbers_to_test[] = { "+071718028","number","078asdasdasd","070asdqwe","070a56798","07045678a","123456789","074456798","073456798","079456798" };
        for ( String number : numbers_to_test ) {
            try {
                new Contact("Andrej",number);
                exception_thrown = false;
            } catch (InvalidNumberException e) {
                exception_thrown = true;
            }
            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNumberException");
        }
        String nums[] = new String[10];
        for ( int i = 0 ; i < nums.length ; ++i ) nums[i] = getRandomLegitNumber();
        try {
            new Contact("Andrej",nums);
            exception_thrown = false;
        } catch (MaximumSizeExceddedException e) {
            exception_thrown = true;
        }
        if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw a MaximumSizeExceddedException");
        Random rnd = new Random(5);
        Contact contact = new Contact("Andrej",getRandomLegitNumber(rnd),getRandomLegitNumber(rnd),getRandomLegitNumber(rnd));
        System.out.println(contact.getName());
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
    }

    static String[] legit_prefixes = {"070","071","072","075","076","077","078"};
    static Random rnd = new Random();

    private static String getRandomLegitNumber() {
        return getRandomLegitNumber(rnd);
    }

    private static String getRandomLegitNumber(Random rnd) {
        StringBuilder sb = new StringBuilder(legit_prefixes[rnd.nextInt(legit_prefixes.length)]);
        for ( int i = 3 ; i < 9 ; ++i )
            sb.append(rnd.nextInt(10));
        return sb.toString();
    }


}
