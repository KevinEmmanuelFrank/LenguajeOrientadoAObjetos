package lenguajeorientadoaobjetos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Biblioteca {

    public void abrirBiblioteca(Usuario usuario) {
        List<Libro> libros = new ArrayList<>();
        List<Libro> librosAlquilados = new ArrayList<>();
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", true));
        libros.add(new Libro("Harry Potter", "J. K. Rowling", false));
        libros.add(new Libro("El diario de Ana Frank", "Ana Frank", true));

        String[] opciones = {"Ver Todos", "Alquilar Libro", "Devolver Libro", "Agregar Libro", "Eliminar Libro"};
        boolean flag = true;
        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenido " + usuario.getNombre() + ", ¿Qué quieres hacer hoy?",
                    "Biblioteca",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (eleccion) {
                case 0:
                    mostrarTodosLosLibros(libros);
                    break;
                case 1:
                    librosAlquilados = alquilarLibro(libros);
                    break;
                case 2:
                    devolverLibro(librosAlquilados);
                    break;
                case 3:
                    agregarLibro(libros);
                    break;
                case 4:
                    libros = eliminarLibro(libros);
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public void mostrarTodosLosLibros(List<Libro> libros) {
        Disponibilidad dispo = new Disponibilidad();
        JOptionPane.showMessageDialog(null, dispo.informar(libros), "Todos los libros", JOptionPane.INFORMATION_MESSAGE);

    }

    public List<Libro> alquilarLibro(List<Libro> libros) {
        List<Libro> alquilados = new ArrayList<>();
        List<Libro> librosFiltrados = filtrarLibro(libros);
        if (!librosFiltrados.isEmpty()) {
            Libro elegido = new Libro();
            elegido = elegirLibro(librosFiltrados);
            librosFiltrados.remove(elegido);
            alquilados.add(elegido);
        }
        return alquilados;
    }

    public void mostrarCatalogo(List<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getEstado()) {
                System.out.println(libro.getTitulo() + " por " + libro.getAutor());
            }
        }
    }

    public void agregarLibro(List<Libro> libros) {
        Disponibilidad dispo = new Disponibilidad();
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro");
        String autor = JOptionPane.showInputDialog("Ingrese el autor del libro");
        if ((comprobar(titulo))
                && (comprobar(autor))) {
            Libro libro = new Libro(titulo, autor, true);
            libros.add(libro);
            JOptionPane.showMessageDialog(null, "Libro agregado exitosamente", "Nuevo libro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, dispo.error(), "Nuevo libro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean comprobar(String dato) {
        boolean info = true;
        if (dato == null || "".equals(dato)) {
            info = false;
        }
        return info;
    }

    private List<Libro> filtrarLibro(List<Libro> libros) {
        List<Libro> librosFiltrados = new ArrayList<>();
        String[] opciones = {"Por Título", "Por Autor"};
        boolean flag = true;
        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Cómo quiere buscar el libro?",
                    "Alquiler",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (eleccion >= 0 && eleccion < opciones.length) {
                String busqueda = JOptionPane.showInputDialog(null, "Ingrese parte de la palabra a buscar");
                if (busqueda != null) {
                    switch (eleccion) {
                        case 0:
                            librosFiltrados = libros.stream()
                                    .filter(libro -> libro.getTitulo().contains(busqueda)).filter(libro -> libro.getEstado() == true)
                                    .collect(Collectors.toList());
                            flag = false;
                            break;
                        case 1:
                            librosFiltrados = libros.stream()
                                    .filter(libro -> libro.getAutor().contains(busqueda)).filter(libro -> libro.getEstado() == true)
                                    .collect(Collectors.toList());
                            flag = false;
                            break;
                        default:
                            flag = false;
                            break;
                    }
                }

            }
            flag = false;
        }
        return librosFiltrados;
    }

    private Libro elegirLibro(List<Libro> librosFiltrados) {
        Libro libroElegido = new Libro();
        String[] opciones = librosFiltrados.stream()
                .map(libro -> libro.getTitulo() + " por " + libro.getAutor())
                .toArray(String[]::new);
        boolean flag = true;

        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué libro desea alquilar?",
                    "Alquiler",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    null
            );

            if (eleccion >= 0 && eleccion < opciones.length) {

                libroElegido = librosFiltrados.get(eleccion);
                libroElegido.setEstado(false);
                JOptionPane.showMessageDialog(null, "El libro ha sido alquilado");
                flag = false;
            } else {

                flag = false;
            }
        }
        return libroElegido;
    }

    private List<Libro> eliminarLibro(List<Libro> libros) {
        Disponibilidad dispo = new Disponibilidad();

        String[] opciones = libros.stream()
                .filter(libro -> libro.getEstado() == true)
                .map(libro -> libro.getTitulo() + " por " + libro.getAutor())
                .toArray(String[]::new);
        boolean flag = true;

        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué libro desea eliminar?",
                    "Eliminar libro",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    null
            );

            if (eleccion >= 0 && eleccion < opciones.length) {

                String tituloSeleccionado = opciones[eleccion].split(" por ")[0]; // Obtener el título del libro seleccionado
                Libro libroElegid = libros.stream()
                        .filter(libro -> libro.getTitulo().equals(tituloSeleccionado))
                        .findFirst()
                        .orElse(null);

                libros.remove(libroElegid);
                JOptionPane.showMessageDialog(null, "El libro ha sido eliminado correctamente");
                flag = false;
            } else {
                JOptionPane.showMessageDialog(null, dispo.error(), "Eliminar libro", JOptionPane.INFORMATION_MESSAGE);
                flag = false;
            }
        }
        return libros;
    }

    private void devolverLibro(List<Libro> librosAlquilados) {
        Libro libroElegido = new Libro();
        if (!(librosAlquilados.isEmpty())) {
            String[] opciones = librosAlquilados.stream()
                    .map(libro -> libro.getTitulo() + " por " + libro.getAutor())
                    .toArray(String[]::new);
            boolean flag = true;

            while (flag) {
                int eleccion = JOptionPane.showOptionDialog(
                        null,
                        "¿Qué libro desea devolver?",
                        "Devolución",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        null
                );

                if (eleccion >= 0 && eleccion < opciones.length) {

                    libroElegido = librosAlquilados.get(eleccion);
                    libroElegido.setEstado(true);
                    JOptionPane.showMessageDialog(null, "El libro ha sido devuelto");
                    librosAlquilados.remove(libroElegido);
                    flag = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                    flag = false;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No posee libros alquilados", "Error", 1);
        }
    }
}
