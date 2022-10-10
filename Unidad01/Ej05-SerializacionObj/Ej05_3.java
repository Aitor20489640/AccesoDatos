import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ej05_3 {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("/media/aitgal/WRXelement/Dam2/GitHub/AccesoDatos/Unidad01/Ej05-SerializacionObj/", "personas.dat");
        List<Persona> agenda = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int op = 0;
        Persona persona;
        boolean ok = true, excepcion = true;

        try (FileInputStream fis = new FileInputStream(nombreFichero.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                Persona p = (Persona) ois.readObject();
                agenda.add(p);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        do {
            System.out.println("""
                    1. Introducir persona en agenda
                    2. Buscar persona por nombre
                    0. Salir
                    """);

           do {
               try {
                   op = sc.nextInt();
                   excepcion = true;
               }
               catch (InputMismatchException ex) {
                   System.out.println("Solo acepta enteros.");
                   excepcion = false;
                   sc.nextLine();
               }
           }while (!excepcion);
            switch (op) {
                case 1 :
                    do {
                        persona = crearPersona();
                        if (persona == null) {
                            System.out.println("Email o fecha en formatos incorrectos.");
                            ok = false;
                        }
                        else {
                            if (existePersona(agenda, persona.getNombre())){
                                removePersona(agenda, persona.getNombre());
                                agenda.add(persona);
                            } else {
                                agenda.add(persona);
                            }

                        }
                    }while(!ok);
                    break;
                case 2:
                    if (!buscarPersona(agenda)) System.out.println("No existe una persona con este nombre");
                    break;
                case 0:
                    try (FileOutputStream fos = new FileOutputStream(nombreFichero.toFile());
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                        for (Persona person: agenda) {
                            oos.writeObject(person);
                        }
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Gracias por utilizar el programa.");
                    break;
                default:
                    System.out.println("Esta opción no existe");
                    break;
            }
        }while(op != 0);
    }

    public static Persona crearPersona() {
        Scanner sc = new Scanner(System.in);
        String nombre, email, date;
        Persona p = null;

        System.out.print("Escribe el nombre de la persona: ");
        nombre = sc.nextLine();
        System.out.print("Escribe el email de la persona: ");
        email = sc.nextLine();
        System.out.print("Escribe la fecha de nacimiento(yyyy-mm-dd): ");
        date = sc.nextLine();

        try {
            p = new Persona(nombre, email, date);
        }
        catch (FormatException e) {
            e.printStackTrace();
        }

        return p;

    }

    public static boolean buscarPersona(List<Persona> agenda) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.println("Dime el nombre de la persona: ");
        nombre = sc.nextLine();

        for (Persona p : agenda) {
            if(p.getNombre().equals(nombre)) {
                p.mostrar();
                ok = true;
            }
        }
        return ok;
    }

    public static boolean existePersona(List<Persona> agenda, String nombre) {
        for (Persona p : agenda) {
            if(p.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static void removePersona(List<Persona> agenda, String nombre) {
        agenda.removeIf(p -> p.getNombre().equals(nombre));
    }
}
