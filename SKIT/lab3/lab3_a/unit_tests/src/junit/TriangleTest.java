package junit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class TriangleTest {

    private int side1, side2, side3;

    @BeforeEach
    public void setUp() {
        //set up all sides to be a triangle
        side1 = 3;
        side2 = 4;
        side3 = 5;
    }

    private void makeValidTriangle(){
        setUp();
    }
    private void makeInvalidTriangleA(){
        side1 = 1;
        side2 = 2;
    }
    private void makeInvalidTriangleB(){
        side1=1;
        side3=2;
    }
    private void makeInvalidTriangleC(){
        side2=1;
        side3=2;
    }

    @Test
    public void test1() {
        makeValidTriangle();
        assertTrue(Triangle.isTriangle(side1, side2, side3));
    }

    @Test
    public void test2(){
        makeInvalidTriangleC();
        assertFalse(Triangle.isTriangle(side1, side2, side3));
    }

    @Test
    public void test3(){
        makeInvalidTriangleB();
        assertFalse(Triangle.isTriangle(side1, side2, side3));
    }

    @Test
    public void test5(){
        makeInvalidTriangleA();
        assertFalse(Triangle.isTriangle(side1, side2, side3));
    }
}
