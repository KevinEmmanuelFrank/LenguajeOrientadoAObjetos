package lenguajeorientadoaobjetos;

import javax.swing.JOptionPane;

public class Inicializador {

    public boolean iniciar() {
        String[] opciones = {"Registrarse", "Iniciar sesión", "Salir"};
        Usuario usuario = new Usuario();
        boolean flag = true;
        boolean login = false;
        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué acción desea realizar?",
                    "Biblioteca",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    "Registrarse"
            );
            switch (eleccion) {
                case 0:
                    Registro registro = new Registro();
                    if(!(null==( usuario = registro.registrar()))){
                       
                    login = true;
                    flag = false;
                    } break;
                case 1:
                    Ingreso ingreso = new Ingreso();
                    if(!(null==(usuario = ingreso.ingreso()))){
                    login = true;
                    flag = false;}
                    break;

                case 2:
                    System.exit(0);
                    flag = false;
                    break;

                default:
                    flag = false;
                    break;
            }
        }

        if (login) {
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.abrirBiblioteca(usuario);
        }

        return flag;
    }

}
