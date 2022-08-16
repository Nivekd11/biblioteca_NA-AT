import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import boundaries.gestionar_estantes;
import boundaries.gestionar_libros;
import boundaries.gestionar_usuarios;
import control.ConexionBD;
import control.Control_Libros;
import entities.Socio;

public class App {
    public static void main(String[] args) throws Exception {
        gestionar_usuarios vistaUsuarios = new gestionar_usuarios();
        gestionar_libros vistaLibros = new gestionar_libros();
        gestionar_estantes vistaEstantes = new gestionar_estantes();

        System.out.println("*******MENU BIBLIOTECA*******");
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Digite la opción que desea.\n1. Menu socios.\n2. Menu libros.\n3. Menu Estantes\n");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    vistaUsuarios.manejarMenu();
                    break;
                case 2:
                    vistaLibros.manejarMenu();
                case 3:
                    vistaEstantes.menu();
            }
        } while (opcion >= 1 || opcion <= 3);

    }

}
