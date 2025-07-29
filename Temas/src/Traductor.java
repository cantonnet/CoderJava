public interface Traductor {
    // public y abstract
    void traducir();

    //metodos con implementacion por default
    default void iniciarTraduccion(){
        System.out.println("iniciando traduccion...");
    }
}


class Ingles implements Traductor{
    @Override
    public void traducir(){
        System.out.println("Traducir a ingles");
    }
}
