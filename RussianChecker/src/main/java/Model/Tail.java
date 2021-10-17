package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tail {
    private String whatIs;

    @Override
    public String toString() {
        return  whatIs ;
    }

    public Tail(String whatIs) {
        this.whatIs = whatIs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tail tail = (Tail) o;
        return Objects.equals(whatIs, tail.whatIs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whatIs);
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }
}
