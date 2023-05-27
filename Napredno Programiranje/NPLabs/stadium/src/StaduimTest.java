import java.util.*;

class Sector {
    String code;
    int seats;
    HashSet<Integer> taken=new HashSet<>();
    HashSet<Integer> types=new HashSet<>();

    public Sector( String code, int seats ) {
        this.code=code;
        this.seats=seats;

    }

    public boolean isTaken(int seat) throws SeatTakenException {
        return taken.contains(seat);
    }
    public void buyTicket( int seat, int type ) throws SeatNotAllowedException {
        if(type==1){
            if(types.contains(2))
                throw new SeatNotAllowedException();
        }
        if(type==2){
            if(types.contains(1))
                throw new SeatNotAllowedException();
        }
        taken.add(seat);
        types.add(type);
    }

    public int freeSeats(){
        return seats - taken.size();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        //System.out.println("tostruibng\n" + taken.toString());
        return String.format("%s\t%d/%d\t%.1f%%",code,freeSeats(),seats,(seats-freeSeats())*100.0/seats);
    }
}

class Stadium{
    String name;
    Map<String,Sector> sectors = new HashMap<>();

    public Stadium( String name ) {
        this.name=name;
    }

    public void createSectors(String[] sectorNames, int[] sizes){
        for ( int i=0; i < sectorNames.length; i++ ) {
            Sector newSector = sectors.put(sectorNames[i],new Sector(sectorNames[i],sizes[i]));
        }
    }
    public void buyTicket(String sectorName, int seat, int type) throws SeatNotAllowedException, SeatTakenException {
        Sector sector = sectors.get(sectorName);
        if(sector.isTaken(seat))
                throw new SeatTakenException();
        sector.buyTicket(seat,type);
    }

    public void showSectors(){
        sectors.values()
                .stream()
                .sorted(Comparator.comparing(Sector::freeSeats)
                        .reversed()
                        .thenComparing(Sector::getCode))
                .forEach(System.out::println);

    }
}
public class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}

class SeatTakenException extends Exception{

}
class SeatNotAllowedException extends Exception{

}