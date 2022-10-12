package Ej04_EscrituraFicheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ej04_2 {
    public static void imprimirFichero(Path rutaFichero) {
        try (Stream<String> lineas = Files.lines(rutaFichero)) {
            lineas.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path ruta = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej04-EscrituraFicheros", "anotaciones.txt");
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
            if (!Files.exists(ruta))
                Files.write(ruta, String.join("\n", stringList).getBytes());
            else {
                Files.writeString(ruta, "\n", StandardOpenOption.APPEND);
                Files.write(ruta, String.join("\n", stringList).getBytes(), StandardOpenOption.APPEND);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        imprimirFichero(ruta);

    }
}
