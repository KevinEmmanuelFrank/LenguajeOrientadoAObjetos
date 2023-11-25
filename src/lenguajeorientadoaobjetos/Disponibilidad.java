package lenguajeorientadoaobjetos;

import java.util.List;

public class Disponibilidad extends Notificaciones {

    public Disponibilidad() {

    }

    public Disponibilidad(String mensaje) {
        super(mensaje);
    }

    @Override
    public String error() {
        return ("Hubo un error, intente nuevamente");
    }

    @Override
    public String informar(List<Libro> libros) {
        String dispo;
        String mensaje = "";
        for (Libro libro : libros) {
            if (libro.getEstado()) {
                dispo = "Disponible";
            } else {
                dispo = "No Disponible";
            }
            mensaje = mensaje + (libro.getTitulo() + " por " + libro.getAutor() + ". Estado: " + dispo + "\n");

        }
        return (mensaje);
    }

    @Override
    public String confirmar() {
        return ("Se realizó la operacion correctamente");
    }

}
