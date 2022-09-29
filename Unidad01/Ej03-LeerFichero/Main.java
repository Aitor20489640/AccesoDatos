import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path csv = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej03-LeerFichero/", "Ej03-LeerFichero.csv");
        List<Product> listaProducts = null;
        int menu;

        try (Stream<String> lineas = Files.lines(csv).skip(1)) {
            listaProducts = lineas.map(Main::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        do {
            System.out.println("""
                    1 Imprimir la lista de productos
                    2 Imprimir los nombres de los productos
                    3 Imprimir los nombres de los productos cuyas unidades en stock sean menor que 10
                    4 Imprimir el nombre de los productos con unidades en stock mayor de 10 ordenados por unidad de stock de forma descendente
                    5 Imprimir el número de productos agrupados por proveedor
                    6 Imprimir el producto con el precio unitario más alto
                    7 Imprimir el promedio de existencias en almacén
                    0 Salir""");
            System.out.print("Elige la opción: ");
            menu = sc.nextInt();
            System.out.println();

            switch (menu) {
                case 0 -> System.out.println("Gracias por utilizar el programa.");
                case 1 -> mostrarProductos(listaProducts);
                case 2 -> mostrarNombres(listaProducts);
                case 3 -> mostrarNombresMenos10Stock(listaProducts);
            }

        }while(menu != 0);

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

    public static void mostrarNombres(List<Product> lista) {
        for (Product prod: lista) {
            System.out.println(prod.getName());
        }
        System.out.println();
    }

    public static void mostrarNombresMenos10Stock(List<Product> lista) {

        lista.stream().filter(p -> p.getUnitsInStock() < 10).forEach(System.out::println);
    }
}
