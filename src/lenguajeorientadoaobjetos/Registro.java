package lenguajeorientadoaobjetos;

import javax.swing.JOptionPane;

public class Registro {

    Usuario usuario = new Usuario();

    public Usuario registrar() {
        boolean band = true;
        while (band) {
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
            String usu = JOptionPane.showInputDialog("Ingrese su nuevo usuario");
            String cont = JOptionPane.showInputDialog("Ingrese la contraseña");
            if (validarDatosIngresados(nombre, apellido, usu, cont)) {
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setNombreUsuario(usu);
                usuario.setPassword(cont);
                if (validarDatos(usuario)) {

                    band = false;
                    JOptionPane.showMessageDialog(null, "Sesión iniciada", "Bienvenido", 2);
                            return usuario;
                }
            }
        }
        return null;
    }

    public boolean validarDatos(Usuario pers) {
        String[] opciones = {"Son Correctos", "Reintentar"};

        boolean flag = true;
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Son correctos los datos ingresados?\n"
                + "Nombre: " + pers.getNombre()
                + "\nApellido: " + pers.getApellido()
                + "\nUsuario: " + pers.getNombreUsuario(),
                "Datos Ingresados",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                "Son Correctos"
        );

        switch (eleccion) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                return false;
        }
    }

    private boolean validarDatosIngresados(String nom, String apel, String dn, String fech) {
        boolean validador = true;

        if (nom == null || nom.equals("") || apel == null || apel.equals("") || dn == null || dn.equals("") || fech == null || fech.equals("")) {
            JOptionPane.showMessageDialog(null, "Algun parámetro quedó vacío, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            validador = false;
        }

        return validador;
    }
}
