package lenguajeorientadoaobjetos;

import java.util.List;

public abstract class Notificaciones {

    private String mensaje;

    public Notificaciones(String mensaje) {
        this.mensaje = mensaje;
    }

    public Notificaciones() {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public abstract String error();

    public abstract String informar(List<Libro> libros);

    public abstract String confirmar();

}
