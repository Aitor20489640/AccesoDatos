import java.util.ArrayList;
import java.util.Collections;

public class ListaDeDatos {
    ArrayList<String> lista;

    public ListaDeDatos() {
        this.lista = new ArrayList<>();
    }

    public void incluir(String texto) {
        lista.add(texto);
    }

    public boolean contiene(String texto) {
        return lista.contains(texto);
    }

    public void mostrarDatosOrdenados() {
        ArrayList<String> listaOrd = new ArrayList<>();

        listaOrd.addAll(0, lista);

        Collections.sort(listaOrd);

        for (String texto : listaOrd) {
            System.out.print(texto + " | ");
        }
    }
}
