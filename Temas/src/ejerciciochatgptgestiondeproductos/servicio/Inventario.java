package ejerciciochatgptgestiondeproductos.servicio;

import ejerciciochatgptgestiondeproductos.dominio.Producto;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private static final List<Producto> productos;

    static{
        productos = new ArrayList<>();
    }

    public static void MostrarInventario(){
        var inventario = " ";
        for (var producto : productos){
            inventario += producto.toString()+"\n";
        }
        System.out.println(inventario);
    }

    public static void agregarProducto(String nombre, double precio, int cantidad){
        productos.add(new Producto(nombre.toUpperCase(), precio,cantidad));
    }

    public static void eliminarProducto(String nombre){
        boolean busqueda = false;
        for (var i = 0;i<productos.size();i++)
        {
            if (nombre.equals(productos.get(i).getNombre()))
            {
                productos.remove(i);
                System.out.println("Producto "+nombre+" eliminado");
                busqueda = true;
                break;
            }
        }
        if (!busqueda)
        {
            System.out.println(nombre+" no existe");
        }
    }

    public static void buscarPorNombre(String nombre){
        boolean busqueda = false;
        for (var i = 0;i<productos.size();i++)
        {
            System.out.println(nombre+" = "+productos.get(i).getNombre()+ " ?");
           if (nombre.equals(productos.get(i).getNombre()))
           {
               System.out.println("Producto encontrado:");
               System.out.println(productos.get(i).toString());
               busqueda = true;
               break;
           }
           else {
               busqueda = false;
           }
        }
        if (!busqueda)
        {
            System.out.println();
            System.out.println("Producto "+nombre+" no encontrado");
        }
    }

    public static List<Producto> getProductos(){
        return productos;
    }

}
