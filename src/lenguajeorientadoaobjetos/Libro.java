package lenguajeorientadoaobjetos;

public class Libro {

    private String titulo;
    private String autor;
    private boolean estado;

    public Libro(String titulo, String autor, boolean estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }

    public Libro() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo
                + ", Autor: " + autor;
    }

}
