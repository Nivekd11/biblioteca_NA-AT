package boundaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import control.Control_Libros;
import entities.Autor;
import entities.Editorial;
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
            limpiarPantalla();
            System.out.println(
                    "\nManejo de Libros \n\nDigita la opción que deseas utilizar.\n\n1. Buscar un libro\n2. Ver todos los libros\n3. Registrar un nuevo libro \n4. Eliminar el registro de un libro\n5. Actualizar los datos de un libro\n6. Mostrar Autores\n7. Mostrar editoriales\n8. Regresar al menu principal\n\nDigita la opción: ");
            opcion = entrada.nextInt(10);
            seleccionarOpcion(opcion);
        } while (opcion > 0 && opcion < 8);
        limpiarPantalla();
    }

    public void seleccionarOpcion(int opcion) {
        System.out.flush();
        switch (opcion) {
            case 1: {
                limpiarPantalla();
                mostrarLibro("Buscar");
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
                eliminarLibro();
                break;
            }
            case 5: {
                limpiarPantalla();
                actualizarLibro();
                break;
            }
            case 6: {
                limpiarPantalla();
                mostrarAutores(objControl.mostrarAutores());
                break;
            }
            case 7: {
                limpiarPantalla();
                mostrarEditoriales(objControl.mostrarEditoriales());
                break;
            }
        }
    }

    public Libro mostrarLibro(String text) {
        Libro libro = new Libro();
        System.out.println(text + " el libro por: \n1.Id\n2.Titulo\n3.ISBN");
        int opcion = entrada.nextInt();
        limpiarPantalla();
        if (opcion == 1) {
            System.out.println("Ingrese el Id");
            libro = objControl.mostrarLibroPorId(entrada.nextInt());
        } else if (opcion == 2) {
            System.out.println("Ingrese el Titulo");
            entrada.nextLine();
            libro = objControl.mostrarLibroPorTitulo(entrada.nextLine());
        } else if (opcion == 3) {
            System.out.println("Ingrese el ISBN");
            entrada.nextLine();
            libro = objControl.mostrarLibroPorISBN(entrada.nextLine());
        }
        if (!text.equals("Actualizar")) {
            if (libro.getIdEditorial() > 0) {
                System.out.println("\nDatos del libro");
                System.out.println("*********************************************************");
                System.out.println(libro.toString());
                System.out.println("*********************************************************");

            } else {
                System.out.println("El libro no fue encontrado en la base de datos");
            }
            System.out.println("\n\nPresione enter para continuar...");
            entrada.nextLine();
            entrada.nextLine();
        }

        return libro;
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
        System.out.println("****************************************");
        for (Libro libro : libros) {
            System.out.println(libro.toString());
            System.out.println("****************************************");
        }
        System.out.println("\n\nPresione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();

    }

    private void crearLibro() {

        Libro libro = new Libro();
        List<Autor> autores = new ArrayList<Autor>();
        List<String> nombreAutores = new ArrayList<String>();
        Editorial editorial = new Editorial();
        Autor autor = new Autor();

        int opcion = 0;

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

        System.out.println("\nIngrese el ISBN");
        libro.setIsbn(entrada.next());

        System.out.println("\nIngrese el nombre de la editorial");
        entrada.nextLine();
        editorial.setNombre(entrada.nextLine());
        editorial = objControl.buscarEditorial(editorial.getNombre());

        if (editorial.getIdEditorial() != 0) { // si la editorial existe se busca su id
            libro.setIdEditorial(editorial.getIdEditorial());
            libro.setEditorial(editorial.getNombre());
        } else {
            editorial.setIdEditorial(objControl.crearEditorial(editorial.getNombre()));
        }
        libro.setIdEditorial(editorial.getIdEditorial());

        // System.out.println("\nIngresa los autores del libro.");
        do {
            entrada.nextLine();
            System.out.println("\nIngresa el nombre del autor");
            autor.setNombre(entrada.nextLine());
            entrada.nextLine();
            // System.out.println("Nombre ingresado: " + autor.getNombre());
            autor = objControl.buscarAutor(autor.getNombre());
            if (autor.getIdautor() != 0) {
                libro.setIdEditorial(editorial.getIdEditorial());
                libro.setEditorial(editorial.getNombre());
            } else {
                autor.setIdautor(objControl.crearAutor(autor.getNombre()));
                // System.out.println("Autor creado");
            }
            autores.add(autor);
            nombreAutores.add(autor.getNombre());
            System.out.println("Si desea ingresar un nuevo autor ingrese 1");
            opcion = entrada.nextInt();
        } while (opcion == 1);

        libro.setAutor(nombreAutores);
        System.out.println("\n\n");
        entrada.nextLine();
        objControl.crearLibro(libro, autores);
        System.out.println("Libro creado");
        System.out.println("\n\nPresione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();
    }

    private void actualizarLibro() {
        Libro libro = mostrarLibro("Actualizar");
        if (libro.getIdLibro() > 0) {

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

            objControl.actualizarLibro(libro, libro.getIdLibro());
            System.out.println("Libro actualizado");
        } else {
            System.out.println("El libro no fue encontrado en la base de datos.");
        }
        System.out.println("\n\nPresione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();

    }

    private void eliminarLibro() {
        Libro libro = mostrarLibro("Eliminar");
        if (libro.getIdLibro() > 0) {
            System.out
                    .println(
                            "¿Seguro que deseas eliminar el libro?   Digite 1 para eliminar.  Digite 2 para cancelar.");
            int opcion = entrada.nextInt();
            if (opcion == 1) {
                objControl.eliminarLibro(libro.getIdLibro());
                System.out.println("Libro eliminado.");

            } else if (opcion == 2) {
                System.out.println("Operacion cancelada.");
            }

            System.out.println("\n\nPresione enter para continuar...");
            entrada.nextLine();
            entrada.nextLine();
        }
    }

    private void mostrarAutores(List<Autor> autores) {
        System.out.println("Autores\n");
        System.out.println("****************************************");
        for (Autor autor : autores) {
            System.out.println(autor.toString());
            System.out.println("****************************************");
        }
        System.out.println("\n\nPresione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();
    }

    private void mostrarEditoriales(List<Editorial> editoriales) {
        System.out.println("Editorial\n");
        System.out.println("****************************************");
        for (Editorial editorial : editoriales) {
            System.out.println(editorial.toString());
            System.out.println("****************************************");
        }
        System.out.println("\n\nPresione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}