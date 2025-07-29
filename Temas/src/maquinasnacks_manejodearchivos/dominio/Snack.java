package maquinasnacks_manejodearchivos.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {

    private static int contadorSnack = 0;
    private int idsnack;
    private String nombre;
    private double precio;

    public Snack()
    {
        this.idsnack = ++Snack.contadorSnack;
    }

    public Snack(String nombre, double precio){
        this();
        this.nombre = nombre;
        this.precio = precio;
    }



    @Override
    public String toString() {
        return "Snack{" +
                "idsnack=" + idsnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String escribirSnack()
    {
        return idsnack +","+ nombre +","+precio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idsnack == snack.idsnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsnack, nombre, precio);
    }

    public static int getContadorSnack() {
        return contadorSnack;
    }

    public int getIdsnack() {
        return idsnack;
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
}
