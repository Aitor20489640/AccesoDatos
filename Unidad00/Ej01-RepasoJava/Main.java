import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        ej1();
        System.out.println(ej2());
        System.out.println(ej3());
        System.out.println(ej4());
        System.out.println((ej5() ? "Es un palíndromo primo" : "No es un palíndromo primo"));
        ej6();
        ej7();
        ej8();

    }

    public static void ej1() {
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.print("Dime tu nombre: ");
        nombre = sc.nextLine();

        for (int i = 0; i < 5; i++) {
            System.out.println("Hola, " + nombre);
        }
    }

    public static String ej2() {
        Scanner sc = new Scanner(System.in);
        String primo = "";
        int num, prime = 2, cont = 0;

        System.out.print("Dime un numero entero: ");
        num = sc.nextInt();
        primo = num + " = ";

        while (prime <= num) {
            if (num % prime == 0 && cont == 0) {
                primo += prime;
                num /= prime;
                cont++;
            }
            else if (num % prime == 0) {
                primo += " * " + prime;
                num /= prime;
            }
            else {
                prime++;
            }
        }
        return primo;
    }

    public static String ej3() {
        Scanner sc = new Scanner(System.in);
        int num;
        String mes = "";
        boolean ok;

        do {
            System.out.print("Dime un número del 1 al 12: ");
            num = sc.nextInt();
            ok = true;
            switch (num) {
                case 1 -> mes = "Enero";
                case 2 -> mes = "Febrero";
                case 3 -> mes = "Marzo";
                case 4 -> mes = "Abril";
                case 5 -> mes = "Mayo";
                case 6 -> mes = "Junio";
                case 7 -> mes = "Julio";
                case 8 -> mes = "Agosto";
                case 9 -> mes = "Septiembre";
                case 10 -> mes = "Octubre";
                case 11 -> mes = "Noviembre";
                case 12 -> mes = "Diciembre";
                default -> {
                    mes = "No hay un mes " + num;
                    ok = false;
                }
            }
        } while (!ok);

        return mes;
    }

    public static String ej4() {
        Scanner sc = new Scanner(System.in);
        String[] meses = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int num;
        String mes = "";

        System.out.print("Dime un número del 1 al 12: ");
        num = sc.nextInt();

        if (num > 12 || num < 1) {
            mes = "No hay un mes " + num;
        }
        else {
            mes = meses[num];
        }

        return mes;
    }

    public static boolean ej5() {
        Scanner sc = new Scanner(System.in);
        int num;


        System.out.print("Dime un numero entero: ");
        num = sc.nextInt();

        return esPalindromo(num) && esPrimo(num);
    }

    public static void ej6() {
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

    public static void ej7() {
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

    public static void ej8() {
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


    public static boolean esPrimo(int num) {
        int cont = 0;

        if (num <= 1) {
            return false;
        }

        for (int i = (int) Math.sqrt(num); i > 1; i--) {
            if (num % i == 0) {
                cont++;
            }
        }

        return cont < 1;
    }

    public static boolean esPalindromo(int num) {
        int rem, sum = 0, number = num;

        while (number > 0) {
            rem = number % 10;
            sum = (sum * 10) + rem;
            number /= 10;
        }

        return num == sum;


    }
}