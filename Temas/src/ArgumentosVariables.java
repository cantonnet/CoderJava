public class ArgumentosVariables {
    public static void main(String[] args) {
        imprimirnumeros(1,2,3,4,5); // argumentos variables del mismo tipo
        variosparametros("Fulano",10,20,30);
    }

    private static void variosparametros(String string,int... numeros) {
        System.out.println(string);
        for (var i = 0;i<numeros.length;i++)
        {
            System.out.print(numeros[i]+" ");
        }
    }

    private static void imprimirnumeros(int... numeros) {
        for (var i = 0;i<numeros.length;i++)
        {
            System.out.println(numeros[i]+" ");
        }
    }
}
