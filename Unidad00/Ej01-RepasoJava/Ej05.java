import java.util.Scanner;

public class Ej05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;


        System.out.print("Dime un numero entero: ");
        num = sc.nextInt();

        System.out.println((esPalindromo(num) && esPrimo(num)) ? "Es un palíndromo primo" : "No es un palíndromo primo");
    }

    public static boolean esPrimo(int num) {
        if (num <= 1) return false;
        if (num % 2 == 0) return false;

        for ( int i = 3; i * i <= num; i+=2 ) {
            if (num % 2 == 0) return false;
        }

        return true;
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
