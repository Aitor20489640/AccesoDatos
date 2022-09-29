import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ej07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;
        ArrayList<String> listaString = new ArrayList<>();

        do {
            System.out.print("Introduce una cadena de texto: ");
            cadena = sc.nextLine();

            if (!cadena.equalsIgnoreCase("fin")) {
                listaString.add(cadena);
            }
        }while (!cadena.equalsIgnoreCase("fin"));

        System.out.println("Fase uno terminada");

        do {
            System.out.print("Introduce una cadena de texto: ");
            cadena = sc.nextLine();

            if (listaString.contains(cadena)) {
                System.out.println("La cadena " + cadena + " esta en la lista");
            } else {
                System.out.println("La cadena " + cadena + " no esta en la lista");
            }
        } while (!cadena.equalsIgnoreCase("fin"));

        Collections.sort(listaString);

        for (String texto : listaString) {
            System.out.print(texto + " | ");
        }
    }
}
