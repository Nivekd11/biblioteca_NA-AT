import java.time.LocalDate;
import java.time.Month;

import boundaries.gestionar_libros;
import boundaries.gestionar_usuarios;
import control.ConexionBD;
import control.Control_Libros;
import entities.Socio;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World!");

        // gestionar_usuarios vistaUsuarios = new gestionar_usuarios();
        // vistaUsuarios.manejarMenu();
        // Socio socio = new Socio();
        // socio.setFechaNacimiento(java.sql.Date.valueOf(LocalDate.of(1999,
        // Month.of(8), 19)));
        // System.out.println( "Fecha de mi cumpleaños: " +
        // socio.getFechaNacimiento().toString() );

        gestionar_libros vistaLibros = new gestionar_libros();
        vistaLibros.manejarMenu();

    }

}
