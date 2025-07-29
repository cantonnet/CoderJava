import java.util.ArrayList;
import java.util.List;

public class Listas {
    public static void main(String[] args) {
        List<String> milista = new ArrayList<>();
        milista.add("Objeto");
        milista.add("lunes");
        milista.add("jueves");

        for(Object elemento : milista)
        {
            System.out.println(elemento);
        }

        for(String elemento : milista)
        {
            System.out.println(elemento);
        }

        //metodo funcion lamda
        milista.forEach(elemento -> {
            System.out.println("elemento "+elemento);
        });

        //metodo de referencia
        milista.forEach(System.out::println);
    }
}
