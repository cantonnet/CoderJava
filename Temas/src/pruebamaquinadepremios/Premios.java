package pruebamaquinadepremios;

import java.util.ArrayList;
import java.util.List;

public class Premios {

    private static final List<Premio> premios;

    static{
        premios = new ArrayList<>();
        premios.add(new Premio("Osito", 14));
    }

    public static void agregarPremio(String nombre, double chance){
        premios.add(new Premio(nombre, chance));
    }

    public static void mostrarPremios(){
        String inventarioPremios = " ";
        for(var premio:premios){
            inventarioPremios += premio.toString()+"\n";
        }
        System.out.println(inventarioPremios);
    }

    public static List<Premio> getPremios(){
        return premios;
    }

}
