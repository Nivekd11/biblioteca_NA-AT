package boundaries;

import control.ControlEstantes;
import control.Control_Libros;
import entities.Estante;
import entities.Libro;
import entities.Seccion;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class gestionar_estantes {

    // Variables
    private final ControlEstantes objControl;
    private final Scanner scan;
    private Scanner entrada;

    /**
     * Default constructor
     */
    public gestionar_estantes() {
        this.objControl = new ControlEstantes();

        this.scan = new Scanner(System.in);
        this.entrada = new Scanner(System.in);
    }

    public void menu() {
        // Variables
        int seleccion;

        do {
            limpiarPantalla();
            System.out.println(
                    "\nManejo de Estantes\n\nDigita la opción que deseas utilizar.\n\n\t1. Crear nuevo estante.\n\t2. Mostrar información de un estante.\n\t3. Mostrar todos los estantes.\n\t4. Agregar libro a estante.\n\n\t5. Regresar al menú principal\n\n");
            System.out.print("Ingresa tu elección (Ejemplo: 1): ");
            seleccion = scan.nextInt(10);
            if (objControl.validarOpcionMenu(seleccion)) {
                seleccionarOpcion(seleccion);
            } else {
                while (!objControl.validarOpcionMenu(seleccion)) {
                    System.out.println("Ingresa un formato válido");
                    System.out.print("La opción que seleccionaste no es válida, intenta nuevamente (Ejemplo: 3): ");
                    seleccion = scan.nextInt();
                }
                limpiarPantalla();
                seleccionarOpcion(seleccion);
            }

        } while (seleccion > 0 && seleccion < 5);
    }

    public void seleccionarOpcion(int opcion) {
        switch (opcion) {
            // Situation: Crear un nuevo estante
            case 1:
                // Variables
                String idSeccion;
                Estante estante = new Estante();
                Seccion seccion = new Seccion();

                System.out.print("Escribe la nomenclatura del nuevo estante (Ejemplo: A-23): ");
                // Action: Agregar nombre del estante.
                estante.setNombre(scan.next());
                System.out.println("\n\n\tSecciones existentes\n\n");
                // Action: Mostrar la sección a la que podría pertenecer.
                mostrarSecciones(objControl.mostrarSecciones());
                System.out.print(
                        "\n\n\t¿A qué sección pertenecerá? (Si no existe la sección deseada puedes agregar una nueva): ");
                // Action: Agregar nombre de la sección
                seccion.setNombre(scan.next());
                // Action: Agregar el registro de la sección seleccionada
                idSeccion = String.valueOf(objControl.comprobarSeccion(seccion.getNombre()));
                // Action: Asignar el ID de la sección registrada
                seccion.setId(idSeccion);
                // Action: Crear el estante con la información de su sección correspondiente
                objControl.crearEstante(estante.getNombre(), Integer.parseInt(seccion.getId()));
                System.out.println("\n\nPresione enter para continuar...");
                entrada.nextLine();
                entrada.nextLine();
                limpiarPantalla();
                System.out.println("\n\n\nNuevo estante asignado\n\n\tEstante: " + estante.getNombre() + "\n\tSección: "
                        + seccion.getNombre() + "\n\tId Sección: " + seccion.getId() + "\n\n");
                break;

            // Situation: Mostrar información de un estante
            case 2:
                // Variables
                Estante objEstante;
                String scanEstante;

                limpiarPantalla();
                System.out.print("\n\nBuscar información del estante\n\n");
                // Action: Pide al usuario información válida del estante
                scanEstante = scanEstante();
                objEstante = objControl.mostrarEstante(scanEstante);
                while (objEstante == null) {
                    System.out.println("No hay ningún estante con esta nomenclatura, ingrese los datos nuevamente:");
                    scanEstante = scanEstante();
                    objEstante = objControl.mostrarEstante(scanEstante);
                }
                // limpiarPantalla();
                System.out.println("\n\n---------------------------------");
                System.out.println("\t\tEstante: " + objEstante.getNombre());
                System.out.println("\t\tSección: " + objEstante.getSeccion());
                System.out.println("---------------------------------");
                System.out.println("\n\nPresione enter para continuar...");
                entrada.nextLine();
                entrada.nextLine();
                break;

            // Situation: Mostrar información de todos los estantes
            case 3:
                limpiarPantalla();
                System.out.print("\n\nEstantes actuales\n\n");
                mostrarSEstantes(objControl.mostrarEstantes());
                System.out.println("\n\nPresione enter para continuar...");
                entrada.nextLine();
                entrada.nextLine();
                break;

            // Situation: Agregar libro al estante
            case 4:
                // Variables
                Control_Libros objControlLibros = new Control_Libros();
                Libro libro;
                String isbn;
                String respuesta;

                System.out.print("\n\nIngrese el ISBN del libro: ");
                isbn = scan.next();
                libro = objControlLibros.mostrarLibroPorISBN(isbn);
                while (libro.getIsbn() == null) {
                    System.out.print("No hay ningún libro con este ISBN, ingrese los datos nuevamente: ");
                    isbn = scan.next();
                    libro = objControlLibros.mostrarLibroPorISBN(isbn);
                }
                System.out.print("Información del libro:\n\n" + libro.toString()
                        + "\n\n\t¿Deseas asignar este libro a un nuevo estante? [S/N]: ");
                respuesta = scan.next();
                while (!"s".equalsIgnoreCase(respuesta) && !"n".equalsIgnoreCase(respuesta)) {
                    System.out.print("Respuesta no admitida, intente nuevamente [S/N]: ");
                    respuesta = scan.next().toLowerCase();
                }
                if (respuesta.equalsIgnoreCase("S")) {
                    respuesta = scanEstante();
                    objControl.agregarLibroAEstante(libro.getIdLibro(), objControl.buscarIdEstante(respuesta));
                    System.out.println("\n\n\tLibro agregado al estante " + respuesta);
                }
                System.out.println("\n\nPresione enter para continuar...");
                entrada.nextLine();
                entrada.nextLine();
                break;
        }
    }

    public void mostrarSecciones(List<Seccion> secciones) {
        for (Seccion seccion : secciones) {
            System.out.println("---------------------------------");
            System.out.println(seccion.getNombre());
            System.out.println("---------------------------------");
        }
        secciones.clear();
    }

    public void mostrarSEstantes(List<Estante> estantes) {
        for (Estante estante : estantes) {
            System.out.println("\t---------------------------------");
            System.out.println("\t\tEstante: " + estante.getNombre());
            System.out.println("\t\tSección: " + estante.getSeccion());
            System.out.println("\t---------------------------------");
        }
        estantes.clear();
    }

    private String scanEstante() {
        // Variables
        String estantes;

        System.out.print("Escribe la nomenclatura del estante (Ejemplo: A-23): ");
        estantes = scan.next();
        // Situation: El usuario no ingresa datos válidos referentes a una sección
        while (!objControl.validarSeccion(estantes)) {
            System.out.println("Que ingreses un formato válido");
            System.out.print("Escribe la nomenclatura del estante (Ejemplo: A-239): ");
            estantes = scan.next();
        }

        return estantes;
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
