package Ej06_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class Ej06 {
    public static void main(String[] args) {
        Product product = new Product(78,"Flotemysost",2,1,10.0,20);
        Path ruta = Path.of("Unidad01/Ej06_RandomAccessFile/Ej06-EscribirProd.csv");

        try (RandomAccessFile fichero = new RandomAccessFile(ruta.toFile(), "rw")){
            long pos = product.seekPositionProduct(ruta.toString());//-56
            fichero.seek(pos);
            System.out.println(fichero.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //product.writeFile(ruta.toString());


    }

}
