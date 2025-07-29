package pruebamaquinadepremios;

public class MaquinaPremios {

    public static void main(String[] args) {

        System.out.println("****Maquina de premios****");
        Premios.mostrarPremios();
        System.out.println();
        Premios.agregarPremio("Dinosaurio", 20);
        Premios.mostrarPremios();

    }
}
