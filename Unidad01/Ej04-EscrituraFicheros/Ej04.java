import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ej04 {
    public static void main(String[] args) {

        Product prod = new Product(78, "name", 1, 1, 10, 20);
        String ruta = "/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej04-EscrituraFicheros/Ej04-EscribirProd.csv";
        Path rutaCsv = Path.of(ruta);
        List<Product> listaProducts = new ArrayList<>();

        prod.writeFile(ruta);

        try (Stream<String> lineas = Files.lines(rutaCsv).skip(1)) {
            listaProducts = lineas.map(Ej04::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mostrarProductos(listaProducts);
    }

    public static Product crearProduct(String line) {

        String[] arr = line.split(",");
        return new Product(
                Integer.parseInt(arr[0]),
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]),
                Double.parseDouble(arr[5]),
                Integer.parseInt(arr[6]));
    }

    public static void mostrarProductos(List<Product> lista) {
        for (Product prod : lista) {
            System.out.println(prod.toString());
        }
        System.out.println();
    }
}
