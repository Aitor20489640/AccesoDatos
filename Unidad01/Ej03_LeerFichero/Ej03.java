package Ej03_LeerFichero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path csv = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej03-LeerFichero/", "Ej03_LeerFichero/Ej03-LeerFichero.csv");
        List<Product> listaProducts = null;
        int menu;

        try (Stream<String> lineas = Files.lines(csv).skip(1)) {
            listaProducts = lineas.map(Ej03::crearProduct).toList();
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
                case 4 -> mostrarNombresMas10StockDescendente(listaProducts);
                case 5 -> mostrarNumeroProdGroupByProveedor(listaProducts);
                case 6 -> mostrarProdMasCaro(listaProducts);
                case 7 -> mostrarAvgStockStored(listaProducts);
                default -> System.out.println("No esta disponible esta opción.");
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

        List<Product> productList = lista.stream().filter(p -> p.getUnitsInStock() < 10).toList();

        for (Product prod : productList) {
            System.out.println(prod.getName());
        }
        System.out.println();
    }

    public static void mostrarNombresMas10StockDescendente(List<Product> lista) {

        List<Product> productList = lista.stream().filter(p -> p.getUnitsInStock() > 10).sorted(Comparator.comparingInt(Product::getUnitsInStock)).toList();

        for (Product prod : productList) {
            System.out.println(prod.getName());
        }
        System.out.println();
    }

    public static void mostrarNumeroProdGroupByProveedor(List<Product> lista) {

        System.out.println(lista.stream().collect(Collectors.groupingBy(p -> p.getSupplier(), Collectors.counting())));
        System.out.println();
    }

    public static void mostrarProdMasCaro(List<Product> lista) {
        System.out.println(lista.stream().max(Comparator.comparingDouble(Product::getUnitPrice)).get());
    }

    public static void mostrarAvgStockStored(List<Product> lista) {
        System.out.println(lista.stream().mapToInt(Product::getUnitsInStock).average().getAsDouble());
    }
}
