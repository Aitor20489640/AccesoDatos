import java.util.Scanner;

public class Ej08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto;
        ListaDeDatos listaString = new ListaDeDatos();

        do {
            System.out.print("Introduce una cadena de texto: ");
            texto = sc.nextLine();

            if (!texto.equalsIgnoreCase("fin")) {
                listaString.incluir(texto);
            }
        }while (!texto.equalsIgnoreCase("fin"));

        System.out.println("Fase uno terminada");

        do {
            System.out.print("Introduce una cadena de texto: ");
            texto = sc.nextLine();

            if (listaString.contiene(texto)) {
                System.out.println("La cadena " + texto + " esta en la lista");
            } else {
                System.out.println("La cadena " + texto + " no esta en la lista");
            }
        } while (!texto.equalsIgnoreCase("fin"));

        listaString.mostrarDatosOrdenados();
    }
}
