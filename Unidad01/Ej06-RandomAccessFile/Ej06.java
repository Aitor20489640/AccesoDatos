import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ej06 {
    public static void main(String[] args) {
        //Pagina para el codigo de RandomAccessFile
        //http://puntocomnoesunlenguaje.blogspot.com/2013/06/java-ficheros-acceso-aleatorio.html
        Scanner sc = new Scanner(System.in);
        String ruta = "/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej06-RandomAccessFile/Ej06-EscribirProd.csv";
        Path rutaCsv = Path.of(ruta);
        String palabra, cadena;
        StringBuilder auxBuilder;
        long pos = 0;
        int indice;
        /*List<Product> listaProducts = new ArrayList<>();

        try (Stream<String> lineas = Files.lines(rutaCsv).skip(1)) {
            listaProducts = lineas.map(Ej06::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mostrarProductos(listaProducts);*/

        System.out.print("Introduce palabra: ");
        palabra = sc.nextLine();

        try (RandomAccessFile fichero = new RandomAccessFile(rutaCsv.toFile(), "rw");){
            cadena = fichero.readLine();
            while (cadena!= null) {
                indice = cadena.indexOf(palabra); //buscamos la palabra en la línea leída
                while(indice!=-1){ //mientras la línea contenga esa palabra (por si está repetida)

                    //paso la línea a un StringBuilder para modificarlo
                    auxBuilder = new StringBuilder(cadena);
                    auxBuilder.replace(indice, indice+palabra.length(), palabra.toUpperCase());
                    cadena = auxBuilder.toString();

                    //nos posicionamos al principio de la línea actual y se sobrescribe la
                    //línea completa
                    //La posición donde empieza la línea actual la estoy guardando
                    //en la variable pos
                    fichero.seek(pos);
                    fichero.writeBytes(cadena);

                    //compruebo si se repite la misma palabra en la línea
                    indice = cadena.indexOf(palabra);
                }
                pos = fichero.getFilePointer(); //posición de la línea actual que voy a leer
                cadena = fichero.readLine();    //lectura de la línea

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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


