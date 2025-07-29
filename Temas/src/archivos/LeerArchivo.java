package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerArchivo {
    public static void main(String[] args) {
        String nombreArchivo = "mi_archivo.txt";
        File archivo = new File(nombreArchivo);

        try
        {
            System.out.println("Contenido del archivo");
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            System.out.println(linea);
            while (linea != null)
            {
                System.out.println(linea);
                linea = entrada.readLine();
            }
            entrada.close();
        }
        catch (Exception e)
        {
            System.out.println("error al leer el archivo");
        }
    }
}
