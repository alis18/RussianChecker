package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {

    @Test
    void testEquals() {
        Step step=new Step(1,2);
        assertEquals(true,step.equals(new Step(1,2)));
        assertEquals(false,step.equals(new Step(2,2)));
    }

    @Test
    void testToString() {
        Step step=new Step(1,2);
        assertEquals("[1, 2]",step.toString());
        Step step1=new Step(4,2);
        assertEquals("[4, 2]",step1.toString());
    }

    @Test
    void getX() {
        Step step=new Step(1,2);
        assertEquals(1,step.getX());
        Step step1=new Step(4,2);
        assertEquals(4,step1.getX());
    }

    @Test
    void setX() {
        Step step=new Step(1,2);
        step.setX(5);
        assertEquals(5,step.getX());
        step.setX(15);
        assertEquals(15,step.getX());
    }

    @Test
    void getY() {
        Step step=new Step(1,2);
        assertEquals(2,step.getY());
        Step step1=new Step(4,2);
        assertEquals(2,step1.getY());
    }

    @Test
    void setY() {
        Step step=new Step(1,2);
        step.setY(5);
        assertEquals(5,step.getY());
        step.setY(55);
        assertEquals(55,step.getY());
    }
}