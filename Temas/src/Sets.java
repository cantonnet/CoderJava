import java.util.Set;
import java.util.TreeSet;

public class Sets {
    public static void main(String[] args) {
        Set<String> conjunto = new TreeSet<>();
        conjunto.add("Carlos");
        conjunto.add("Carlos");
        conjunto.add("Mario");
        conjunto.add("Karla");

        conjunto.forEach(System.out::println);
        conjunto.remove("Karla");
        conjunto.forEach(System.out::println);
    }
}
