import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import boundaries.gestionar_libros;
import boundaries.gestionar_usuarios;
import control.ConexionBD;
import control.Control_Libros;
import entities.Socio;

public class App {
    public static void main(String[] args) throws Exception {

        gestionar_usuarios vistaUsuarios = new gestionar_usuarios();
        gestionar_libros vistaLibros = new gestionar_libros();

        System.out.println("*******MENU BIBLIOTECA*******");
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Digite la opcion que desea.\n1. Menu socios.\n2. Menu libros.");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    vistaUsuarios.manejarMenu();
                    break;
                case 2:
                    vistaLibros.manejarMenu();
            }
        } while (opcion == 1 || opcion == 2);

    }

}
