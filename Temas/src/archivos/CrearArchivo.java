package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrearArchivo {
    public static void main(String[] args) {
        var nombreArchivo = "mi_archivo.txt";
        var archivo = new File(nombreArchivo);

        try {
            if(archivo.exists())
            {
                System.out.println("el archivo ya existe");
            }
            else {
                //crear el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se a creado el archivo: "+archivo);

            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: "+ e.getMessage());;
        }


    }
}
