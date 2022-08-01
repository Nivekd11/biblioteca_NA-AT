import control.Control_Socios;
import entities.Conexion;
import entities.Libro;
import entities.Socio;

import java.util.ArrayList;
import java.util.List;

import boundaries.gestionar_libros;
import boundaries.gestionar_usuarios;
import control.ConexionBD;
import control.Control_Libros;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World!");

        gestionar_usuarios vistaUsuarios = new gestionar_usuarios();
        vistaUsuarios.manejarMenu();

        // gestionar_libros vistaLibros = new gestionar_libros();
        // vistaLibros.manejarMenu();

    }

}
