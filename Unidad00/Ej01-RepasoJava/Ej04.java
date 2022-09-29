import java.util.Scanner;

public class Ej04 {
    public static void main(String[] args) {
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
            System.out.println(meses[num]);
        }
    }
}
