public class ClaseAbstracta {
    public static void main(String[] args) {

        var triangulo = new Triangulo();
        FiguraGeometrica rectangulo = new Rectangulo();
        rectangulo.dibujar();
    }
}


abstract class FiguraGeometrica{ //No se puede instanciar (crear objetos)
    public abstract void dibujar();
}

class Rectangulo extends FiguraGeometrica{
    @Override
    public void dibujar() {
        System.out.println("Se dibuja un rectangulo");
    }
}

class Triangulo extends FiguraGeometrica{
    @Override
    public void dibujar() {
        System.out.println("Se dibuja un triangulo");
    }
}