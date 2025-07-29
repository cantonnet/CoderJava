package maquinasnacks_manejodearchivos.servicio;

import maquinasnacks_manejodearchivos.dominio.Snack;

import java.util.List;

public interface IServicioSnacks {

    void agregarSnack(Snack snack);
    void mostrarSnack();
    List<Snack> getSnacks();
}
