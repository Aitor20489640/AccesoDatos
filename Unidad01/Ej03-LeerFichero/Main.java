import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path csv = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej03-LeerFichero/", "Ej03-LeerFichero.csv");
        List<Product> listaProducts;
        Pattern pattern = Pattern.compile(",");
        try (Stream<String> lineasCsv = Files.lines(csv)) {
            Stream<String> lineas  = lineasCsv.skip(1);
            listaProducts = lineas.map(pl -> {String[] arr = pattern.split(pl);
                return new Product(
                        Integer.parseInt(arr[0]),
                        arr[1],
                        Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]),
                        Double.parseDouble(arr[5]),
                        Integer.parseInt(arr[6]));
            }).toList();

            for(Product prod : listaProducts) {
                System.out.println(prod.toString());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
