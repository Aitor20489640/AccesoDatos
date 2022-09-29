import java.util.Scanner;

public class Ej03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        String mes;
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

        System.out.println(mes);
    }
}
