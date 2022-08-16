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

    /**
     * Default constructor
     */
    public gestionar_estantes() {
        this.objControl = new ControlEstantes();

        this.scan = new Scanner(System.in);
    }

    // Variables
    private final ControlEstantes objControl;

    private final Scanner scan;

    public void menu() {
        // Variables
        int seleccion;

        do {
            System.out.println("\nManejo de Estantes\n\nDigita la opción que deseas utilizar.\n\n\t1. Crear nuevo estante.\n\t2. Mostrar información de un estante.\n\t3. Mostrar todos los estantes.\n\t4. Agregar libro a estante.\n");
            System.out.print("Tu elección: ");
            seleccion = scan.nextInt(10);
            seleccionarOpcion(seleccion);
        } while (seleccion > 0 && seleccion < 6);
    }

    public void seleccionarOpcion(int opcion) {
        switch (opcion) {
            // Situation: Crear un nuevo estante
            case 1 -> {
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
                System.out.print("\n\n\t¿A qué sección pertenecerá? (Si no existe la sección deseada puedes agregar una nueva): ");
                // Action: Agregar nombre de la sección
                seccion.setNombre(scan.next());
                // Action: Agregar el registro de la sección seleccionada
                idSeccion = String.valueOf(objControl.comprobarSeccion(seccion.getNombre()));
                // Action: Asignar el ID de la sección registrada
                seccion.setId(idSeccion);
                // Action: Crear el estante con la información de su sección correspondiente
                objControl.crearEstante(estante.getNombre(), Integer.parseInt(seccion.getId()));
                System.out.println("\n\n\nNuevo estante asignado\n\n\tEstante: " + estante.getNombre() + "\n\tSección: " + seccion.getNombre() + "\n\tId Sección: " + seccion.getId() + "\n\n");
            }

            // Situation: Mostrar información de un estante
            case 2 -> {
                // Variables
                String estante;

                System.out.print("\n\nBuscar información del estante\n\n");
                System.out.print("Escribe la nomenclatura del estante (Ejemplo: A-23): ");
                estante = scan.next();
                Estante objEstante = objControl.mostrarEstante(estante);
                System.out.println("\n\n---------------------------------");
                System.out.println("\t\tEstante: " + objEstante.getNombre());
                System.out.println("\t\tSección: " + objEstante.getSeccion());
                System.out.println("---------------------------------");

            }

            // Situation: Mostrar información de todos los estantes
            case 3 -> {
                System.out.print("\n\nEstantes actuales\n\n");
                mostrarSEstantes(objControl.mostrarEstantes());

            }

            // Situation: Agregar libro al estante
            case 4 -> {
                // Variables
                Control_Libros objControlLibros = new Control_Libros();
                Libro libro;
                String isbn;
                String respuesta;

                System.out.print("\n\nIngrese el ISBN del libro: ");
                isbn = scan.next();
                libro = objControlLibros.mostrarLibroPorISBN(isbn);
                System.out.print("Información del libro:\n\n" + libro.toString() + "\n\n\t¿Deseas asignar este libro a un nuevo estante? [S/N]: ");
                respuesta = scan.next();
                System.out.println("Respuesta: " + respuesta);
                while (!"s".equalsIgnoreCase(respuesta) && !"n".equalsIgnoreCase(respuesta)) {
                    System.out.print("Respuesta no admitida, intente nuevamente [S/N]: ");
                    respuesta = scan.next().toLowerCase();
                }
                if (respuesta.equalsIgnoreCase("S")) {
                    System.out.print("\n\n\t¿A qué estante quieres agregarlo?: ");
                    respuesta = scan.next();
                    libro.addEstante(respuesta);
                    objControlLibros.actualizarLibro(libro, libro.getIdLibro());
                    System.out.println("\n\n\tEstante agregado al libro");
                    System.out.println(libro);
                }

            }
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
}