package boundaries;

import java.util.List;
import java.util.Scanner;

import control.Control_Libros;
import entities.Libro;

/**
 * 
 */
public class gestionar_libros {

    /**
     * Default constructor
     */
    private Control_Libros objControl;
    private Scanner entrada;

    public gestionar_libros() {
        this.objControl = new Control_Libros();
        entrada = new Scanner(System.in);
    }

    public void manejarMenu() {
        System.out.flush();
        int opcion = 0;
        do {
            // System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(
                    "\nManejo de Libros \n\nDigita la opción que deseas utilizar.\n\n1. Buscar un libro\n2. Ver todos los libros\n3. Registrar un nuevo libro \n4. Eliminar el registro de un libro\n5. Actualizar los datos de un libro\n6. Regresar al menu principal\n\nDigita la opción: ");
            opcion = entrada.nextInt(10);
            seleccionarOpcion(opcion);
        } while (opcion > 0);
    }

    public void seleccionarOpcion(int opcion) {
        System.out.flush();
        switch (opcion) {
            case 1: {
                limpiarPantalla();
                System.out.println("Escribe el id  del libro que deseas buscar:");
                int id = entrada.nextInt();
                mostrarLibro(objControl.mostrarLibro(id));
                break;
            }
            case 2: {
                limpiarPantalla();
                mostrarLibros(objControl.mostrarLibros());
                break;
            }
            case 3: {
                limpiarPantalla();
                crearLibro();
                break;
            }
            case 4: {
                limpiarPantalla();
                System.out.println("Escribe el id  del libro que desea eliminar:");
                int id = entrada.nextInt();
                eliminarLibro(id);
                break;
            }
            case 5: {
                limpiarPantalla();
                System.out.println("Escribe el Id del libro que deseas actualizar:");
                int id = entrada.nextInt();
                actualizarLibro(id);
                break;

            }
        }
    }

    public void mostrarLibro(Libro libro) {
        if (libro.getIdEditorial() > 0) {
            System.out.println("\nDatos del libro");
            System.out.println(libro.toString());
            System.out.println();
        } else {
            System.out.println("El libro no fue encontrado en la base de datos");
        }
    }

    public void mostrarLibros(List<Libro> libros) {
        System.out.println("Datos de los Libros\n");
        for (Libro libro : libros) {
            System.out.println(libro.toString());
            System.out.println("****************************************");
        }

    }

    private void crearLibro() {

        Libro libro = new Libro();
        System.out.println("\nDigite los datos del libro");
        System.out.flush();

        System.out.println("\nInserte el titulo: ");
        entrada.nextLine();
        libro.setTitulo(entrada.nextLine());

        System.out.println("\nIngrese el valor: ");
        libro.setValor(entrada.nextDouble());

        System.out.println("\nIngrese el numero de ejemplares: ");
        libro.setEjemplares(entrada.nextInt());

        System.out.println("\nIngrese la edicion: ");
        entrada.nextLine();
        libro.setEdicion(entrada.nextLine());

        System.out.println("\nIngrese el id de la editorial");
        libro.setIdEditorial(entrada.nextInt());

        System.out.println("\nIngrese el ISBN");
        libro.setIsbn(entrada.next());

        System.out.println("\n\n");
        entrada.nextLine();
        objControl.crearLibro(libro);
    }

    private void actualizarLibro(int id) {
        Libro libro = objControl.mostrarLibro(id);
        mostrarLibro(libro);

        System.out.println("Digita 1 si deseas modificar o 0 si no");
        System.out.println("\n Deseas actualizar el titulo:  " + libro.getTitulo());
        if (entrada.nextInt() > 0) {
            entrada.nextLine();
            System.out.println("\nEscribe el Titulo del libro:");
            libro.setTitulo(entrada.nextLine());
        }

        entrada.nextLine();
        System.out.println("\n Deseas actualizar el valor:  " + libro.getValor());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el Valor del libro:");
            libro.setValor(entrada.nextDouble());
        }

        entrada.nextLine();
        System.out.println("\n Deseas actualizar el numero de ejemplares disponibles:  " + libro.getEjemplares());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el numejo de de ejemplares del libro:");
            libro.setEjemplares(entrada.nextInt());
        }

        System.out.println("\n Deseas actualizar la edicion del libro: " + libro.getEdicion());
        if (entrada.nextInt() > 0) {
            entrada.nextLine();
            System.out.println("\nEscribe el numero de edicion del libro: ");
            libro.setEdicion(entrada.nextLine());
        }

        entrada.nextLine();
        System.out.println("\n Deseas actualizar el id de editorial:  " + libro.getIdEditorial());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el id de la editorial:");
            libro.setIdEditorial(entrada.nextInt());
        }

        entrada.nextLine();
        System.out.println("\n Deseas actualizar el ISBN del libro:  " + libro.getIsbn());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el ISBN del libro:");
            libro.setIsbn(entrada.next());
        }

        objControl.actualizarLibro(libro, id);

    }

    private void eliminarLibro(int id) {

        Libro libro = objControl.mostrarLibro(id);
        mostrarLibro(libro);
        System.out
                .println(
                        "¿Seguro que deseas eliminar el libro?   Digite 1 para eliminar.  Digite 2 para cancelar.");
        int opcion = entrada.nextInt();
        if (opcion == 1) {
            objControl.eliminarLibro(id);
            System.out.println("Libro eliminado.");

        } else if (opcion == 2) {
            System.out.println("Operacion cancelada.");

        }
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}