package archivos;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LeerTodo {
    public static void main(String[] args) {
        String nombreArchivo = "mi_archivo.txt";
        File archivo = new File(nombreArchivo);
        try
        {
            //leer todas las lineas
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            System.out.println("¡Contenido del archivo: ");
            for(String linea : lineas){
                System.out.println(linea);
            }

        }
        catch (Exception e)
        {
            System.out.println("Error al leer las lineas");
        }
    }
}
