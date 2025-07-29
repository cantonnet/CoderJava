package ejerciciochatgptgestiondeproductos.presentacion;

import ejerciciochatgptgestiondeproductos.servicio.Inventario;

import java.util.Scanner;

public class Programa {

    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("**** Programa de Gestion de Productos ****");
        menu();


    }


    static void menu(){
        boolean salir = false;
        while (!salir)
        {
            System.out.println("1.Agregar producto\n" +
                    "2.Listar productos\n" +
                    "3. Buscar producto por nombre\n" +
                    "4. Eliminar producto por nombre\n" +
                    "5. Salir\n" +
                    "\tSeleccionar la opcion deceada");
            try{
                var opcion = Integer.parseInt(console.nextLine());
                switch (opcion){
                    case 1:
                        System.out.print("\ningresar el nombre del producto: ");
                        var nombre = console.nextLine();
                        System.out.print("\ningresar precio del producto: ");
                        var precio = Double.parseDouble(console.nextLine());
                        System.out.println("Ingresar cantidad de stock: ");
                        var cantidad = Integer.parseInt(console.nextLine());
                        agregarProductos(nombre, precio, cantidad);
                        break;
                    case 2:
                        listarproducto();
                        break;
                    case 3:
                        System.out.println("Ingresar nombre del producto: ");
                        var nombreProducto = console.nextLine().toUpperCase().strip();
                        buscarproducto(nombreProducto);
                        break;
                    case 4:
                        System.out.println("Que producto deseas eliminar?: ");
                        nombreProducto = console.nextLine().toUpperCase().strip();
                        eliminarproducto(nombreProducto);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("A seleccionado "+opcion+" por favor\n"+"Seleccione una opcion valida");
                        break;
                }
            }catch (Exception e)
            {
                System.out.println("Error en el typado");
            }
        }
    }

    private static void eliminarproducto(String nombreProducto) {
        Inventario.eliminarProducto(nombreProducto);
    }

    private static void buscarproducto(String nombreProducto) {
        Inventario.buscarPorNombre(nombreProducto);
    }

    private static void listarproducto() {
        Inventario.MostrarInventario();
    }

    private static void agregarProductos(String nombre, double precio, int cantidad) {
        Inventario.agregarProducto(nombre, precio, cantidad);
        System.out.println("Producto "+ nombre +" agregado");
    }

}
