import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Libro> libros = new ArrayList<>();

        libros.add(new Libro("El Señor de los Anillos", 800, "J.R.R. Tolkien"));
        libros.add(new Libro("El Hobbit", 350, "J.R.R. Tolkien"));
        libros.add(new Libro("Cabo Trafalgar", 320, "Arturo Pérez Reverte"));
        libros.add(new Libro("El corazón de la piedra", 560, "José María García López"));
        libros.add(new Libro("Salmos de vísperas", 95, "Esteban Hernández Castelló"));
        libros.add(new Libro("La música en las catedrales españolas del Siglo de Oro", 600, "Robert Stevenson"));
        libros.add(new Libro("Luces de bohemia", 296, "Ramón del Valle-Inclán"));
        libros.add(new Libro("Contando atardecere", 528, "La vecina rubia"));
        libros.add(new Libro("Master - Roger Federer", 456, "Christopher Clarey"));
        libros.add(new Libro("La teoría de los archipiélagos", 300, "Alice Kellen"));
        libros.add(new Libro("Esperando al diluvio", 576, "Dolores Redondo"));
        libros.add(new Libro("El italiano", 400, "Arturo Pérez Reverte"));
        libros.add(new Libro("Línea de fuego", 688, "Arturo Pérez Reverte"));

        //Parte 1
        List<Libro> mas500pags = libros.stream().filter(l -> l.getNumPaginas() > 500).toList();

        System.out.println("Este es el numero de libros con mas de 500 paginas: " + mas500pags.size());

        System.out.println("---------------------------");

        //Parte 2
        List<Libro> menos300pags = libros.stream().filter(l -> l.getNumPaginas() < 300).toList();

        System.out.println("Este es el numero de libros con menos de 300 paginas: " + menos300pags.size());

        System.out.println("---------------------------");

        //Parte 3
        for (Libro libro : mas500pags) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("---------------------------");

        //Parte 4
        List<Libro> max3Pag = libros.stream()
                .sorted((l1, l2) -> Integer.compare(l2.getNumPaginas(), l1.getNumPaginas()))
                .limit(3)
                .toList();


        for (Libro libro : max3Pag) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("---------------------------");

        //Parte 5
        int sumPages = libros.stream()
                .map(l -> l.getNumPaginas())
                .reduce(0, (a,b) -> a + b);

        System.out.println("Este es el numero total de paginas: " + sumPages);

        System.out.println("---------------------------");

        //Parte 6
        int avgPages = sumPages / libros.size();

        List<Libro> masPagAvg = libros.stream()
                .filter(l -> l.getNumPaginas() > avgPages)
                .toList();

        for (Libro libro : masPagAvg) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("---------------------------");

        //Parte 7

        String autoresDistinc = libros.stream()
                .map(l -> l.getAutor())
                .distinct()
                .collect(Collectors.joining(",", "Autores: ", ""));

        System.out.println(autoresDistinc);

        System.out.println("---------------------------");

        //Parte 8
        libros.stream()
                .map(a -> a.getAutor())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(l -> l.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        System.out.println("---------------------------");


        //Parte 9
        List<Libro> maxPagLibroList = libros.stream()
                .sorted((l1, l2) -> Integer.compare(l2.getNumPaginas(), l1.getNumPaginas()))
                .limit(1)
                .toList();
        String maxPagLibro = maxPagLibroList.get(0).getTitulo();
        System.out.println("Este es el libro con mas páginas: " + maxPagLibro);

        System.out.println("---------------------------");

        //Parte 10

        List<String> allTitulos = libros.stream()
                .map(l -> l.getTitulo())
                .toList();

        System.out.println(allTitulos);

        System.out.println("---------------------------");
    }
}