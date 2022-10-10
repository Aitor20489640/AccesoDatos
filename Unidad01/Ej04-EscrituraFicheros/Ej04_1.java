import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ej04_1 {
    public static void imprimirFichero(Path rutaFichero) {
        try (Stream<String> lineas = Files.lines(rutaFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path ruta = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej04-EscrituraFicheros", "frases.txt");
        List<String> stringList = new ArrayList<>();
        String linea;

        System.out.println("Escribe una frase( para dejar de escribir introduce una línea vacía): ");
        do {
            linea = sc.nextLine();
            if (!linea.equals("")) {
                stringList.add(linea);
            }

        }while(!linea.equals(""));

        try {
            Files.write(ruta, String.join("\n", stringList).getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        imprimirFichero(ruta);

    }
}
