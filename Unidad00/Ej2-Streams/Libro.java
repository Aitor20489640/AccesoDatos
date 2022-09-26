public class Libro {

    private String titulo;
    private int numPaginas;
    private String autor;

    public Libro(String titulo, int numPaginas, String autor)
    {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.autor = autor;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public int getNumPaginas()
    {
        return numPaginas;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumPaginas(int numPaginas)
    {
        this.numPaginas = numPaginas;
    }
}
