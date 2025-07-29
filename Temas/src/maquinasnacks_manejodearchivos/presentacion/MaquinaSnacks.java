package maquinasnacks_manejodearchivos.presentacion;

import maquinasnacks_manejodearchivos.dominio.Snack;
import maquinasnacks_manejodearchivos.servicio.IServicioSnacks;
import maquinasnacks_manejodearchivos.servicio.ServicioSnacksArchivo;
import maquinasnacks_manejodearchivos.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {

    public static void main(String[] args) {
        maquinaSnacks();
    }

    private static void maquinaSnacks() {
        var salir = false;
        var console = new Scanner(System.in);
        //Creamos ahora un objeto para obtener el servicio de snacks (lista)
        //IServicioSnacks servicioSnacks = new ServicioSnacksLista();
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivo();
        //Crear la lista de productos tipo snack ---> puede ser tambien "var productos = new ArrayList<Snack>();"
        List<Snack> productos = new ArrayList<>();
        //Titulo app
        System.out.println("***** Maquina de Snacks *****");
        servicioSnacks.mostrarSnack(); // Mostrar inventario de snacks
        while (!salir){
            try {
                var opcion = mostrarMenu(console);
                salir = ejecutarOpciones(opcion, console, productos, servicioSnacks);

            }catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            finally {
                System.out.println();// Imprime un salto de linea con cada iteracion
            }
        }
    }

    private static boolean ejecutarOpciones(int opcion, Scanner console, List<Snack> productos, IServicioSnacks servicioSnacks) {

        var salir = false;
        switch (opcion){
            case 1:
                comprarSnack(console, productos, servicioSnacks);
                break;
            case 2:
                mostrarTicket(productos);
                break;
            case 3:
                agregarSnack(console, servicioSnacks);
                break;
            case 4:
                System.out.println("Saliendo del programa");
                salir = true;
                break;
            default:
                System.out.println("Seleccionar una opcion valida");
                break;
        }
        return salir;
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = "*** Ticket de venta ***";
        var total = 0.0;
        for(Snack producto :productos)
        {
            ticket += "\n\t-" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);

    }

    private static int mostrarMenu(Scanner console){
        System.out.print("Menu:\n" +
                "1. Comprar Snack\n" +
                "2. Mostrar ticket\n" +
                "3. Agregar Nuevo Snack\n" +
                "4. Salir\n" +
                "Seleccionar opcion:\s");
        return Integer.parseInt(console.nextLine());
    }

    private static void comprarSnack(Scanner console, List<Snack> productos, IServicioSnacks servicioSnacks){
        System.out.println("Que snack quieres comprar id: ");
        var idSnack = Integer.parseInt(console.nextLine());
        //validar
        var snackEncontrado = false;
        for ( var snack: servicioSnacks.getSnacks())
        {
            if (idSnack == snack.getIdsnack()){
                //agregar a la lista de productos a comprar
                productos.add(snack);
                System.out.println("Snack agregado: "+ snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idSnack);
        }

    }

    private static void agregarSnack(Scanner console, IServicioSnacks servicioSnacks)
    {
        System.out.println("Nombre del snack: ");
        var nombre = console.nextLine();
        System.out.println("¨recop del snack: ");
        double precio = Double.parseDouble(console.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Tu snack se ha agregado correctamente");
        servicioSnacks.mostrarSnack();
    }



}
