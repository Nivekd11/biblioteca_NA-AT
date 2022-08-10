package boundaries;

import control.ControlEstantes;

import java.util.Scanner;

/**
 *
 */
public class gestionar_estantes {

    /**
     * Default constructor
     */
    public gestionar_estantes() {
        this.objControl = new ControlEstantes();

        this.scan = new Scanner(System.in);
    }

    // Variables
    private ControlEstantes objControl;
    private Scanner scan;

    public void menu() {
        // Variables
        int seleccion;

        System.out.flush();
        do {
            System.out.println("\nManejo de Estantes\n\nDigita la opción que deseas utilizar.\n\n1.Crear nuevo estante.\n2. Mostrar información de un estante.\n3. Mostrar todos los estantes.\n");
            seleccion = scan.nextInt(10);
        } while (seleccion > 0 && seleccion <6);
    }

}