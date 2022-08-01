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
                    "Manejo de Libros \n\nDigita la opción que deseas utilizar.\n\n1. Buscar un libro\n2. Ver todos los libros\n3. Crear un nuevo libro\n4. Modificar los datos de un libro\n5. Regresar al menu principal\n\nDigita la opción: ");
            opcion = entrada.nextInt(10);
            seleccionarOpcion(opcion);
        } while (opcion > 0);
    }

    public void seleccionarOpcion(int opcion) {
        System.out.flush();
        switch (opcion) {
            case 1: {
                System.out.println("Escribe el id  del libro que deseas buscar:");
                int id = entrada.nextInt();
                mostrarLibro(objControl.mostrarLibro(id));
            }
            case 2: {
                mostrarLibros(objControl.mostrarLibros());
            }
            case 3: {
                crearLibro();
            }
        }
    }

    public void mostrarLibro(Libro libro) {
        System.out.println("Datos del libro\n");
        System.out.println(libro.toString());

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
        libro.setTitulo(entrada.next());
        entrada.nextLine();

        System.out.println("\nIngrese el valor: ");
        libro.setValor(entrada.nextDouble());

        System.out.println("\nIngrese el numero de ejemplares: ");
        libro.setEjemplares(entrada.nextInt());

        System.out.println("\nIngrese la edicion: ");
        libro.setEdicion(entrada.next());
        entrada.nextLine();

        System.out.println("\nIngrese el id de la editorial");
        libro.setIdEditorial(entrada.nextInt());

        System.out.println("\nIngrese el ISBN");
        libro.setIsbn(entrada.next());

        System.out.println("\n\n");
        entrada.nextLine();
        objControl.crearLibro(libro);
    }

}