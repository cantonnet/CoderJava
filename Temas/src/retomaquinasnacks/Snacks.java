package retomaquinasnacks;

import java.util.ArrayList;
import java.util.List;

public class Snacks {

    private static final List<Snack> snacks;

    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Quesoritos", 50));
        snacks.add(new Snack("Sanguche", 90));
    }

    public static void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public static void mostrarSnack(){
        String inventarioSnack = " ";
        for (Snack snacks : snacks)
        {
            inventarioSnack += snacks.toString() + "\n";
        }
        System.out.println("***MostrarSnacks***");
        System.out.println(inventarioSnack);
    }

    public static List<Snack> getSnacks(){
        return snacks;
    }

}
