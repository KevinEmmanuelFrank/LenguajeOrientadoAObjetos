package lenguajeorientadoaobjetos;

import javax.swing.JOptionPane;

public class Ingreso {

    public Usuario ingreso() {
        Usuario usuario = new Usuario();
        String usu = JOptionPane.showInputDialog("Ingrese el usuario");
        String cont = JOptionPane.showInputDialog("Ingrese la contraseña");
        if ((!("Correcto".equals(comprobar(usu, cont))))) {
            JOptionPane.showMessageDialog(null, (comprobar(usu, cont)), "Estado", 0);
            return null;
        } else {
            JOptionPane.showMessageDialog(null, "Sesión iniciada", "Bienvenido", 2);
            usuario.setNombre("Lautaro");
            usuario.setApellido("Montaña");
            usuario.setNombreUsuario("Lauta");
            usuario.setPassword("123");
            return usuario;
        }
    }

    public String comprobar(String usuario, String contraseña) {
        String comprobar = "";
        if (!(usuario == null)) {
            if (!(usuario.equals("Lauta"))) {
                comprobar = ("Usuario incorrecto");
            } else {
                if (!(contraseña == null)) {
                    if ((contraseña.equals("123"))) {

                        comprobar = ("Correcto");
                    } else {
                        comprobar = ("Contraseña incorrecta");
                    }
                } else {
                    comprobar = ("Contraseña incorrecta");
                }
            }
        } else {
            comprobar = ("Usuario incorrecto");
        }
        return comprobar;
    }

}
