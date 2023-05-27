import java.util.*;

class Participant implements Comparable<Participant>{
    String code,name;
    int age;

    public Participant( String code, String name, int age ) {
        this.code=code;
        this.name=name;
        this.age=age;
    }

    @Override
    public int compareTo(Participant o) {
        int x = name.compareTo(o.name);
        if(x == 0) {
            int y = Integer.compare(age, o.age);
            if(y==0) {
                return code.compareTo(o.code);
            }
            return y;
        }

        return x;
    }
    @Override
    public boolean equals(Object obj) {
        Participant p = (Participant) obj;
        return code.equals(p.code);
    }
    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }
}

class Audition{
    HashMap<String,HashSet<Participant>> participants = new HashMap<>();
    public void addParticipant(String city, String code, String name, int age){
        Participant participant = new Participant(code,name,age);
        HashSet<Participant> set=participants.computeIfAbsent(city, k -> new HashSet<>());
        set.add(participant);
    }

    public void listByCity(String city){
        participants.get(city)
                .stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}
public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}