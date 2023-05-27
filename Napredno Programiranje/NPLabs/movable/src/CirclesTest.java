import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class ObjectCanNotBeMovedException extends Exception{
    Movable m;
    public ObjectCanNotBeMovedException(Movable m){
        this.m=m;
    }

    @Override
    public String getMessage(){
        return "Point (" + m.getCurrentXPosition() + "," + m.getCurrentYPosition() + ")  is out of bounds";
    }
}
class MovableObjectNotFittableException extends Exception{
    Movable m;
    public MovableObjectNotFittableException(Movable m){
        this.m=m;
    }

    @Override
    public String getMessage(){
        return m.toString().replace("center coordinates ", "") + " can not be fitted into the collection";
    }
}
interface Movable{
    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    int getCurrentXPosition();
    int getCurrentYPosition();

    TYPE getType();
}

class MovablePoint implements Movable{
    int x, y;
    int xSpeed, ySpeed;

    public MovablePoint( int x, int y, int xSpeed, int ySpeed ) {
        this.x=x;
        this.y=y;
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
    }

    public MovablePoint( int x, int y ) {
        this.x=x;
        this.y=y;
    }


    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if(y+ySpeed > MovablesCollection.Y_MAX || y+ySpeed<0)
            throw new ObjectCanNotBeMovedException(new MovablePoint(x,y+ySpeed));
        y+=ySpeed;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if(y-ySpeed < 0 || y-ySpeed >MovablesCollection.Y_MAX)
            throw new ObjectCanNotBeMovedException(new MovablePoint(x,y-ySpeed));
        //y-=ySpeed;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if(x-xSpeed<0 || x-xSpeed>MovablesCollection.X_MAX)
            throw new ObjectCanNotBeMovedException(new MovablePoint(x-xSpeed,y));
        x-=xSpeed;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if(x+xSpeed>MovablesCollection.X_MAX || x+xSpeed <0)
            throw new ObjectCanNotBeMovedException(new MovablePoint(x+xSpeed,y));
        x+=xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return 0;
    }

    @Override
    public int getCurrentYPosition() {
        return 0;
    }

    @Override
    public TYPE getType() {
        return TYPE.POINT;
    }

    @Override
    public String toString(){
        return "Movable point with coordinates (" + x + ',' + y + ')';
    }

}

class MovableCircle implements Movable{
    int radius;
    MovablePoint center;

    public MovableCircle( int radius, MovablePoint center ) {
        this.radius=radius;
        this.center=center;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        center.moveDown();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        center.moveRight();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public TYPE getType() {
        return TYPE.CIRCLE;
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates ("
                + center.getCurrentXPosition()
                + ','
                + center.getCurrentYPosition()
                + ") and radius " + radius;
    }

    public int getRadius() {
        return radius;
    }
}

class MovablesCollection{
    Movable [] movable;
    static int X_MAX=0;
    static int Y_MAX=0;

    public static int getxMax() {
        return X_MAX;
    }

    public static int getyMax() {
        return Y_MAX;
    }

    public static void setxMax( int xMax ) {
        X_MAX=xMax;
    }

    public static void setyMax( int yMax ) {
        Y_MAX=yMax;
    }

    public MovablesCollection( int x_MAX, int y_MAX) {
        setxMax(x_MAX);
        setyMax(y_MAX);
        movable=new Movable[0];
    }

    private boolean isPointNotFittable(Movable m){
        return m.getCurrentXPosition()<0
                || m.getCurrentXPosition() > X_MAX
                || m.getCurrentYPosition() <0
                || m.getCurrentYPosition()>Y_MAX;
    }

    private boolean isCircleNotFittable( MovableCircle m ) {
        MovablePoint plusRadius = new MovablePoint(m.getRadius()+m.getCurrentXPosition(),m.getRadius()+m.getCurrentYPosition());
        MovablePoint minusRadius = new MovablePoint(m.getCurrentXPosition()-m.getRadius(),m.getCurrentYPosition()-m.getRadius());
        MovablePoint circleCenter = new MovablePoint(m.getCurrentXPosition(),m.getCurrentYPosition());
        return isPointNotFittable(plusRadius) || isPointNotFittable(minusRadius) || isPointNotFittable(circleCenter);
    }
    public void addMovableObject(Movable m) throws MovableObjectNotFittableException{
        if(m.getType()==TYPE.POINT){
            if(isPointNotFittable(m)){
                throw new MovableObjectNotFittableException(m);
            }
        }
        else if(isPointNotFittable(m) || isCircleNotFittable((MovableCircle)m)){
            throw new MovableObjectNotFittableException(m);
        }
        movable = Arrays.copyOf(movable,movable.length+1);
        movable[movable.length-1]=m;

    }




    void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction){
        for (Movable m: movable){
            if(m.getType() == type){
                try{
                    if(direction == DIRECTION.UP){
                        m.moveUp();
                    }
                    else if(direction == DIRECTION.DOWN){
                        m.moveDown();
                    }
                    else if(direction == DIRECTION.RIGHT){
                        m.moveRight();
                    }
                    else if(direction == DIRECTION.LEFT){
                        m.moveLeft();
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Collection of movable objects with size " + movable.length + ":\n");
        for ( Movable m: movable ) {
            sb.append(m.toString()+'\n');
        }
        return sb.toString();
    }
}

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            try{
                if ( Integer.parseInt(parts[0]) == 0 ) { //point
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } else { //circle
                    int radius=Integer.parseInt(parts[5]);
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }


}
