import java.util.ArrayList;
import java.util.List;

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

class Song{
    String title,artist;

    public Song( String title, String artist ) {
        this.title=title;
        this.artist=artist;
    }

    @Override
    public String toString(){
        return "Song{title="
                +title
                +", artist="
                +artist
                +"}";
    }
}
class Tuple<K,V>{
    K key;
    V value;

    public Tuple( K key, V value ) {
        this.key=key;
        this.value=value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
class MP3Player{
    List<Song> songs;
    Tuple<Song,Integer> currentSong;
    private boolean STOPPED=true;
    private boolean PLAYING=false;
    private boolean ALREADYSTOPPED=false;
    public MP3Player( List<Song> songs ) {
        this.songs=songs;
        currentSong=new Tuple<>(songs.get(0),0);
    }

    public void pressPlay(){
        if(!PLAYING) {
            System.out.printf("Song %d is playing\n", songs.indexOf(currentSong.getKey()));
            PLAYING=true;
            STOPPED=false;
        }
        else
            System.out.println("Song is already playing");
    }
    public void pressStop(){
        if(!STOPPED){
            System.out.printf("Song %d is paused\n",songs.indexOf(currentSong.getKey()));
            STOPPED=true;
            ALREADYSTOPPED=false;
            PLAYING=false;
        }
        else if(!ALREADYSTOPPED){
            ALREADYSTOPPED=true;
            System.out.println("Songs are stopped");
            currentSong=new Tuple<>(songs.get(0),0);
        }
        else {
            System.out.println("Songs are already stopped\n");
        }
    }
    public void pressFWD(){
        System.out.println("Forward...");
        int index=songs.indexOf(currentSong.getKey());
        if(index==songs.size()-1)
            currentSong=new Tuple<>(songs.get(0),0);
        else
            currentSong=new Tuple<>(songs.get(index+1),index+1);
        STOPPED=false;
        PLAYING=true;
    }
    public void pressREW(){
        System.out.println("Reward...");
        int index=currentSong.getValue();
        if(index==0)
            currentSong=new Tuple<>(songs.get(songs.size()-1),songs.size()-1);
        else
            currentSong=new Tuple<>(songs.get(index-1),index-1);
        STOPPED=false;
        PLAYING=true;
    }
    public void printCurrentSong(){
        System.out.println(currentSong.getKey().toString());
    }

    @Override
    public String toString(){
        return "MP3Player{currentSong = " + currentSong.getValue() + ", songList = " + songs + "}";
    }
}