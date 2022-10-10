import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ej05_1 {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej05-SerializacionObj/", "personas.dat");
        List<Persona> agenda = new ArrayList<>();
        try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            agenda.add(new Persona("Aitor", "Aitor.Gallardo.14@gmail.com", "2001-10-29"));
            agenda.add(new Persona("Atriog", "numver096@gmail.com", "2000-10-20"));
            agenda.add(new Persona("Flavius", "Flavius.dam@gmail.com", "1999-05-10"));

            for (Persona person: agenda) {
                oos.writeObject(person);
            }

        }
        catch (IOException | FormatException ex) {
            ex.printStackTrace();
        }


    }
}
