package Ej05_SerializacionObj;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ej05_2 {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej05-SerializacionObj/", "Ej05_SerializacionObj/personas.dat");
        List<Persona> agenda = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(nombreFichero.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                Persona person = (Persona) ois.readObject();
                agenda.add(person);
            }

            for (Persona p : agenda) {
                p.mostrar();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
