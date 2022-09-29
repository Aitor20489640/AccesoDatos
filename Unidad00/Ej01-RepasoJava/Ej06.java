import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ej06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, numero;
        ArrayList listaNum = new ArrayList();

        do {
            System.out.print("Dime un numero entero: ");
            num = sc.nextInt();

            if (num >= 0) {
                listaNum.add(num);
            }
        }while(num >= 0);

        System.out.println("Fase uno terminada");

        do {
            System.out.print("Dime un numero entero: ");
            num = sc.nextInt();
            if (num >= 0) {
                if (listaNum.contains(num)){
                    System.out.println("El numero " + num + " esta en la lista");
                }
                else {
                    System.out.println("El numero " + num + " no esta en la lista");
                }
            }
        }while (num >= 0);

        Collections.sort(listaNum);

        for (int i = 0; i < listaNum.size(); i++) {
            numero = (int) listaNum.get(i);
            System.out.print(numero + " ");
        }
    }
}
