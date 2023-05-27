import java.util.*;

class InvalidArchiveOpenException extends Exception{

    public InvalidArchiveOpenException(String msg){
        super(msg);
    }

}

abstract class Archive{
    int id;
    Date dateArchived;

    public Archive( int id, Date dateArchived ) {
        this.id=id;
        this.dateArchived=dateArchived;
    }

    public  void archive(Date date){
        dateArchived=date;
    }

    public abstract Date open(Date date)throws InvalidArchiveOpenException;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        Archive archive = (Archive) obj;
        return this.id == archive.id;
    }

}

class LockedArchive extends Archive{

    private Date dateToOpen;

    public LockedArchive(int id, Date dateToOpen){
        super(id,null);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public Date open( Date date ) throws InvalidArchiveOpenException {
        if(date.before(dateToOpen))
            throw new InvalidArchiveOpenException(String.format("Item %d cannot be opened before %s", id, dateToOpen));
        return date;
    }
}

class SpecialArchive extends Archive{
    private int countOpened;
    private int maxOpen;

    public SpecialArchive(int id,int maxOpen){
        super(id,null);
        countOpened=0;
        this.maxOpen=maxOpen;
    }

    @Override
    public Date open(Date date) throws InvalidArchiveOpenException {
        if(countOpened>=maxOpen)
            throw new InvalidArchiveOpenException(String.format(
                    "Item %d cannot be opened more than %d times", id, maxOpen));
        countOpened++;
        return date;
    }
}

class ArchiveStore{
    List<Archive> archives;
    StringBuilder log;
    public ArchiveStore(){
        archives = new LinkedList<>();
    }
    public void archiveItem(Archive item,Date date){
        item.archive(date);
        archives.add(item);
        log.append("Item ").append(item.getId()).append(" archived at ").append(date);
    }

    public String getLog() {
        return log.toString();
    }

    void openItem( int id, Date date) throws NonExistingItemException {
        for ( Archive item: archives ) {
            if(item.getId()==id){
                try{
                    item.open(date);
                } catch(InvalidArchiveOpenException e){
                    log.append(e.getMessage()).append("\n");
                }
                log.append(String.format("Item %d opened at %s\n", item.getId(), date));
            }
        }
        throw new NonExistingItemException(id);
    }
}

class NonExistingItemException extends Exception {

    public NonExistingItemException(int id){
        super(String.format("Item with id %d doesn't exist",id));
    }
}

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}

// вашиот код овде


