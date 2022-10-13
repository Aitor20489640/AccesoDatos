package Ej06_RandomAccessFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ej06 {
    public static void main(String[] args) {
        Product product = new Product(78,"name1",2,1,10.0,20);
        Product product2 = new Product(78,"name2",2,1,10.0,20);
        Product product3 = new Product(78,"name3",2,1,10.0,20);
        Product product4 = new Product(78,"name1",5,1,10.0,20);
        List<Product> listaProducts = new ArrayList<>();

        Path ruta = Path.of("Unidad01/Ej06_RandomAccessFile/Ej06-EscribirProd.csv");

        product.writeFile(ruta.toString());

        try (Stream<String> lineas = Files.lines(ruta).skip(1)) {
            listaProducts = lineas.map(Product::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mostrarProductos(listaProducts);

        product2.writeFile(ruta.toString());
        product3.writeFile(ruta.toString());
        product4.writeFile(ruta.toString());

        try (Stream<String> lineas = Files.lines(ruta).skip(1)) {
            listaProducts = lineas.map(Product::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mostrarProductos(listaProducts);


    }

    public static void mostrarProductos(List<Product> lista) {
        for (Product prod : lista) {
            System.out.println(prod.toString());
        }
        System.out.println();
    }

}
