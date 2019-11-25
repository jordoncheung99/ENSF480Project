package Server;

public class Name {
    String first;
    String last;
    public Name(String first, String last){
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }
}
