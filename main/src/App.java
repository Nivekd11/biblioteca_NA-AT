
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;

import boundaries.gestionar_estantes;
import boundaries.gestionar_libros;
import boundaries.gestionar_préstamos;
import boundaries.gestionar_usuarios;
import control.ConexionBD;
import control.Control_Libros;
import entities.Socio;

public class App {
    public static void main(String[] args) throws Exception {

        gestionar_usuarios vistaUsuarios = new gestionar_usuarios();
        gestionar_libros vistaLibros = new gestionar_libros();
        gestionar_estantes vistaEstantes = new gestionar_estantes();
        gestionar_préstamos vistaPrestamos = new gestionar_préstamos();

        
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("*******MENU BIBLIOTECA*******");
            System.out.println("Digite la opcion que desea.\n1. Menu socios.\n2. Menu libros. \n3. Menu estantes \n4. Menu prestamos \n5. Salir");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    vistaUsuarios.manejarMenu();
                    break;
                case 2:
                    vistaLibros.manejarMenu();
                    break;
                case 3:
                    vistaEstantes.menu();
                    break;
                case 4:
                    vistaPrestamos.menu();

            }
        } while (opcion < 5);
        // String Dateinicio     = "2022-08-08";
        // SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        
        //     Date fechaInicio      =   new java.util.Date(date.parse(Dateinicio).getTime());
        
        
        // // La fecha actual
        // Date fechaactual = new Date(System.currentTimeMillis());
        // int milisecondsByDay = 86400000;
        // int dias = (int) ((fechaInicio.getTime()-fechaactual.getTime()) / milisecondsByDay);
        
        // System.out.println(dias);


    }

}
