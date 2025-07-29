package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AgregarContenidoArchivo {

    public static void main(String[] args) {
        boolean anexar = false;
        String nombreArchivo = "mi_archivo.txt";
        File archivo = new File(nombreArchivo);
        try {
            //Verificar si existe
            anexar = archivo.exists();
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
            String nuevoContenido = "nuevo\nContenido";
            salida.println(nuevoContenido);
            salida.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
