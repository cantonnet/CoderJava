package ejerciciochatgptgestiondeproductos.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {

    private static int contadorProducto;
    private final int idProducto;
    private String nombre;
    private double precio;
    private int cantidadStock;

    Producto(){
        this.idProducto = ++Producto.contadorProducto;
    }

    public Producto(String nombre, double precio, int cantidadStock){
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    public static int getContadorProducto() {
        return contadorProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return idProducto == producto.idProducto && Double.compare(precio, producto.precio) == 0 && cantidadStock == producto.cantidadStock && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombre, precio, cantidadStock);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadStock=" + cantidadStock +
                '}';
    }
}
