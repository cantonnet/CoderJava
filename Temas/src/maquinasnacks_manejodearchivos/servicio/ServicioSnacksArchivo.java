package maquinasnacks_manejodearchivos.servicio;

import maquinasnacks_manejodearchivos.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivo implements IServicioSnacks{
    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Crear lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    //Constructor
    public ServicioSnacksArchivo(){
        //Crear Archivo si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if(existe)
            {
                this.snacks = obtenerSnacks();
            }
            else {// crear el archivo
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se a creado el archivo");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error al crear el archivo"+e.getMessage());
        }
        if (!existe)
            cargarSnacksIniciales();
    }

    private void cargarSnacksIniciales() {
        this.agregarSnack(new Snack("Papas", 70));
        this.agregarSnack(new Snack("Refresco", 50));
        this.agregarSnack(new Snack("Sandwich", 120));
    }

    @Override
    public void agregarSnack(Snack snack) {
        //Agregar el nuevo snack, 1. a la lista en memoria
        this.snacks.add(snack);
        //2. guardamos en el archivo
        this.agregarSnackArchivo(snack);
    }

    public void agregarSnackArchivo(Snack snack)
    {
        boolean anexar = false;
        File archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists();
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();//cerramos para guardar el archivo
        } catch (Exception e) {
            System.out.println("Error al agregar snack al archivo"+e.getMessage());;
        }
    }

    public List<Snack> obtenerSnacks(){
        List<Snack> snacks = new ArrayList<>();
        try
        {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String lines : lineas){
                String[] lineaSnack = lines.split(",");// parseo separado por coma
                String idSnack = lineaSnack[0]; // no se usa
                String nombre = lineaSnack[1];
                double precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack);// agregamos el snack leido a la lista
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return snacks;
    }

    @Override
    public void mostrarSnack() {
        System.out.println("**** Snacks en el inventario *****");
        var inventario = "";
        for (var snack : this.snacks)
        {
            inventario += snack.toString()+"\n";
        }
        System.out.println(inventario);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
