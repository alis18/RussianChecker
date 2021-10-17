package Model;

import static org.junit.jupiter.api.Assertions.*;

class TailTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        Tail tail=new Tail("6");
        assertEquals("6",tail.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Tail tail=new Tail("6");
        Tail tail1=new Tail("7");
        assertEquals(false,tail.equals(tail1));
        Tail tail2=new Tail("6");
        assertEquals(true,tail.equals(tail2));
    }

    @org.junit.jupiter.api.Test
    void getWhatIs() {
        Tail tail=new Tail("6");
        assertEquals("6",tail.getWhatIs());
    }

    @org.junit.jupiter.api.Test
    void setWhatIs() {
        Tail tail=new Tail("6");
        tail.setWhatIs("7");
        assertEquals("7",tail.getWhatIs());
    }
}