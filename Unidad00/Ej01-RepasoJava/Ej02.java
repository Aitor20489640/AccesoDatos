import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ej02 {
    public static void main(String[] args) {
        List<Integer> primeFactor = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int num, factor;

        System.out.print("Dime un numero entero: ");
        num = sc.nextInt();
        factor = num;

        // Save the number of 2s that divide factor
        while (factor % 2 == 0) {
            primeFactor.add(2);
            factor /= 2;
        }

        // factor must be odd at this point.
        // So we can skip one element (Note i = i +2)
        for (int i = 3; i * i <= factor; i += 2) {
            // While i divides factor, print i and divide factor
            while (factor % i == 0) {
                primeFactor.add(i);
                factor /= i;
            }
        }

        // This condition is to handle the case whe factor is a prime number greater than 2
        if (factor > 2)
            primeFactor.add(factor);

        // Print the number and it's factors
        System.out.println(num + " = " + primeFactor.stream().map(Objects::toString).collect(Collectors.joining(" * ")));

    }
}