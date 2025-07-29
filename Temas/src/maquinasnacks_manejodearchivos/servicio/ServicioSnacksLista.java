package maquinasnacks_manejodearchivos.servicio;

import maquinasnacks_manejodearchivos.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks {

    private static final List<Snack> snacks;

    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Quesoritos", 50));
        snacks.add(new Snack("Sanguche", 90));
    }

    public  void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public  void mostrarSnack(){
        String inventarioSnack = " ";
        for (Snack snacks : snacks)
        {
            inventarioSnack += snacks.toString() + "\n";
        }
        System.out.println("***MostrarSnacks***");
        System.out.println(inventarioSnack);
    }

    public  List<Snack> getSnacks(){
        return snacks;
    }

}
