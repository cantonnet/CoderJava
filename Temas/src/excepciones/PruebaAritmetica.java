package excepciones;

public class PruebaAritmetica {
    public static void main(String[] args) {

       try {
           System.out.println(Aritmetica.division(2, 0));

       } catch (Exception e) {
           System.out.println("Error cachado"+e);
       }
       finally {
           System.out.println("finally siempre se ejecuta independientemente de si da error o no");
       }
    }
}
