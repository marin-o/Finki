import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;

class NoSuchRoomException extends Exception {
    public NoSuchRoomException( String roomName ) {
        super(roomName);
    }
}
class NoSuchUserException extends Exception {
    public NoSuchUserException( String username ) {
        super(username);
    }
}


class ChatRoom{
    String name;
    TreeSet<String> usernames;

    public ChatRoom( String name ) {
        this.name=name;
        usernames = new TreeSet<>();
    }

    public void addUser(String username){
        usernames.add(username);
    }
    public void removeUser(String username){
        usernames.remove(username);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(name+"\n");
        if(usernames.isEmpty())
            return sb.append("EMPTY\n").toString();
        for ( String username: usernames ) {
            sb.append(username+"\n");
        }
        return sb.toString();
    }
    public boolean hasUser(String username){
        return usernames.contains(username);
    }
    public int numUsers(){
        return usernames.size();
    }
}

class ChatSystem{
    int chatRoomId;
    TreeSet<String> usernames = new TreeSet<>();

    TreeMap<String,ChatRoom> chatrooms = new TreeMap<>();
    public ChatSystem(){
        this.chatRoomId=0;
    }
    public ChatSystem( int chatRoomId ) {
        this.chatRoomId=chatRoomId;
    }
    public void addRoom(String roomName){
        chatrooms.put(roomName,new ChatRoom(roomName));
    }
    public void removeRoom(String roomName){
        chatrooms.remove(roomName);
    }
    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        ChatRoom gottenRoom = chatrooms.get(roomName);
        if(gottenRoom == null)
            throw new NoSuchRoomException(roomName);
        return gottenRoom;
    }

    public void register(String userName){
        usernames.add(userName);
        int min = Integer.MAX_VALUE;
        LinkedList<ChatRoom> minRooms = new LinkedList<ChatRoom>();
        for ( ChatRoom room: chatrooms.values() ) {
            if(room.usernames.size()<min){
                minRooms = new LinkedList<ChatRoom>();
                min = room.usernames.size();
            }
            if(room.usernames.size()==min) minRooms.add(room);
        }
        if(minRooms.isEmpty()) return;
         minRooms.getFirst().addUser(userName);
    }
    public void registerAndJoin(String username,String roomName) throws NoSuchRoomException, NoSuchUserException {
        usernames.add(username);
        joinRoom(username,roomName);
    }
    public void joinRoom(String username,String roomName) throws NoSuchRoomException, NoSuchUserException {
        if(!chatrooms.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }
        if(!usernames.contains(username)){
            throw new NoSuchUserException(username);
        }
        getRoom(roomName).addUser(username);
    }
    public void leaveRoom(String username,String roomName) throws NoSuchRoomException, NoSuchUserException {
        if(!usernames.contains(username)){
            throw new NoSuchUserException(username);
        }
        getRoom(roomName).removeUser(username);
    }
    public void followFriend(String username,String friend) throws NoSuchUserException {
        if(!usernames.contains(username)){
            throw new NoSuchUserException(username);
        }
        if(!usernames.contains(friend)){
            throw new NoSuchUserException(username);
        }
        List<ChatRoom> friendRooms = chatrooms.values().stream().filter(room -> room.hasUser(friend)).collect(Collectors.toList());
        for ( ChatRoom room: friendRooms ) {
            room.addUser(username);
        }

    }
}

public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }

}
