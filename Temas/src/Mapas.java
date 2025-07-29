import java.util.HashMap;
import java.util.Map;

public class Mapas {
    public static void main(String[] args) {
        Map<String, String> persona = new HashMap<>();
        persona.put("nombre", "Diego");
        persona.put("apellido", "Flores");
        persona.put("edad", "31");
        System.out.println("Valores del mapa: ");
        persona.entrySet().forEach(System.out::println);
        persona.forEach((llave, valor) ->{
            System.out.println("Llave: "+llave+"\nValor: "+valor);
        });

    }
}
