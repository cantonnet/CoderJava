package pruebamaquinadepremios;

import java.io.Serializable;
import java.util.Objects;

public class Premio implements Serializable {

    private final int idPremio;
    private static int contador;
    private String nombre;
    private double chanceSalida;

    Premio(){
        this.idPremio = ++Premio.contador;
    }

    Premio(String nombre, double chanceSalida){
        this();
        this.nombre = nombre;
        this.chanceSalida = chanceSalida;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Premio premio = (Premio) o;
        return idPremio == premio.idPremio && Double.compare(chanceSalida, premio.chanceSalida) == 0 && Objects.equals(nombre, premio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPremio, nombre, chanceSalida);
    }

    @Override
    public String toString() {
        return "Premio{" +
                "idPremio=" + idPremio +
                ", nombre='" + nombre + '\'' +
                ", chanceSalida=" + chanceSalida +
                '}';
    }

    public int getIdPremio() {
        return idPremio;
    }

    public static int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getChanceSalida() {
        return chanceSalida;
    }

    public void setChanceSalida(double chanceSalida) {
        this.chanceSalida = chanceSalida;
    }
}
