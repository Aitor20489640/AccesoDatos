import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Persona implements Serializable {

    @Serial
    private static final long serialVersionUID = 100L;
    private String nombre;
    private String email;
    private LocalDate dateOfBirth;

    public Persona (String nombre, String email, String dateOfBirth) throws FormatException {
        this.nombre = nombre;
        if (!validateEmail(email))
            throw new FormatException("Correo con formato incorrecto");
        else
            this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        return pattern.matcher(email).matches();
    }

    public void mostrar(){
        System.out.println("Persona -> nombre: " + nombre + ", email: " + email + ", fecha de nacimiento: " + dateOfBirth);
    }
}
