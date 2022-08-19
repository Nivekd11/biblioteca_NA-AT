package boundaries;

import control.Control_Libros;
import control.Control_Prestamos;
import entities.Libro;
import entities.Prestamo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

public class gestionar_préstamos {

    private Scanner entrada;
    private Control_Prestamos objControl;

    public gestionar_préstamos() {
        this.objControl = new Control_Prestamos();
        entrada = new Scanner(System.in);
    }

    public static void main(String[] args) {
        gestionar_préstamos objPrestamo = new gestionar_préstamos();
        objPrestamo.cronPenallizacion();
    }

    public void cronPenallizacion(){
        LocalDate fechaActual = LocalDate.now();
        objControl.crearPenalizaciones(java.sql.Date.valueOf(fechaActual));
    }
    public void menu() {

        // System.out.flush();
        int opcion = 0;
        while (opcion != 8) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print(
                    "\nManejo de Prestamos \n\nDigita la opción que deseas utilizar.\n\n1. Generar Prestamo\n2. Buscar Prestamo\n3. Entrega de Libro\n4. Crear Penalización \n5. Ver penalizaciones de socio \n6. Ver todas las penalizaciones \n7. Pagar deuda \n8. Salir\n");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    generarPrestamo();
                    break;
                case 2:
                    mostrarPrestamo();
                    break;
                case 3:
                    entregarLibro();
                    break;
                case 4:
                    generarPenalizacionPerdida();
                    break;
                case 5:
                    mostrarPenalizaciones();
                    break;
                case 6:
                    mostrarTodasLasPenalizaciones();
                    break;
                case 7:
                    pagarDeuda();
                    break;

            }
        }

    }

    public void generarPrestamo() {
        limpiarPantalla();
        Prestamo prestamo = new Prestamo();
        String curp = "";
        System.out.println("Ingrese CURP del socio: ");
        // System.out.flush();
        curp = entrada.nextLine().toUpperCase();
        curp = entrada.nextLine().toUpperCase();
        if (!objControl.buscarSocio(curp).equals("")) {
            int total = 3 - objControl.buscarPrestamosPorSocio(curp);
            int opcion = 0;
            while ((total != 0) && (opcion != 2)) {
                System.out.println("Tiene " + total + " prestamos disponibles");
                System.out.println("1- Ingresar datos\n2- Salir");
                opcion = entrada.nextInt();
                if (opcion == 1) {
                    prestamo.setIdSocio(curp);
                    System.out.println("Ingrese Id del libro:");
                    prestamo.setIdLibro(entrada.nextInt());
                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    LocalDate fechaEgreso = LocalDate.now();
                    prestamo.setFechaEgreso(java.sql.Date.valueOf(fechaEgreso));
                    LocalDate fechaLimite = fechaEgreso.plusDays(7);
                    prestamo.setFechaLimite(java.sql.Date.valueOf(fechaLimite));
                    System.out.println("Fecha de egreso: " + fechaEgreso);
                    System.out.println("Fecha limite de ingreso: " + fechaLimite);
                    objControl.crearPrestamo(prestamo);
                }
                total = 3 - objControl.buscarPrestamosPorSocio(curp);
            }

        } else {
            System.out.print("El socio no existe UnU\n");
            System.out.println("Presione enter para continuar...");
            String linea = entrada.nextLine();
        }

    }

    public void entregarLibro() {
        int folio = 0;
        limpiarPantalla();
        String linea;
        System.out.println("Ingrese folio del prestamo: ");
        folio = entrada.nextInt();
        int idlibro = objControl.buscarPrestamoPorFolio(folio);
        if (idlibro != 0) {
            LocalDate fechaIngreso = LocalDate.now();
            objControl.actuaLizarPrestamo(folio, java.sql.Date.valueOf(fechaIngreso), idlibro);
            System.out.println("Presione enter para continuar...");
            linea = entrada.nextLine();
            linea = entrada.nextLine();
        } else {
            System.out.println("El prestamo con folio " + folio + " no existe");
            System.out.println("Presione enter para continuar...");
            linea = entrada.nextLine();
            linea = entrada.nextLine();
        }

    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void generarPenalizacionPerdida() {
        int folio = 0;
        String tipoPerdida = "";
        float costo;
        limpiarPantalla();
        System.out.println("Ingrese folio del prestamo: ");
        folio = entrada.nextInt();
        System.out.println("Seleccione el tipo de perdida:");
        System.out.println("1. Perdido");
        System.out.println("2. Estropeado");
        if (entrada.nextInt() == 1) {
            tipoPerdida = "Perdido";
        } else {
            tipoPerdida = "Estropeado";
        }
        Control_Libros objControlLibro = new Control_Libros();
        Prestamo prestamo = objControl.mostrarPrestamo(folio);
        Libro libro = objControlLibro.mostrarLibroPorId(prestamo.getIdLibro());
        System.out.println("El precio del libro " + libro.getTitulo() + " es de: " + libro.getValor());
        System.out.println("Digite el costo del libro:");
        costo = entrada.nextFloat();
        Date fechaIngreso = new Date(System.currentTimeMillis());
        // objControl.actuaLizarPrestamo(folio, new
        // java.sql.Date(fechaIngreso.getTime()) );
        prestamo = objControl.mostrarPrestamo(folio);
        // prestamo.toString();
        if (prestamo != null) {
            int milisecondsByDay = 86400000;
            int dias = (int) ((prestamo.getFechaLimite().getTime() - fechaIngreso.getTime()) / milisecondsByDay);
            establecerPenalizacion(dias, folio, tipoPerdida, costo);
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }

    public void establecerPenalizacion(int dias, int folio, String tipoPerdida, float costo) {
        if (dias < 0) {
            objControl.actualizarPenalizacion(folio, tipoPerdida, costo);
            entrada.nextLine();
            entrada.nextLine();
        } else {
            objControl.crearPenalizacion(tipoPerdida, new Date(System.currentTimeMillis()), costo, folio);
            entrada.nextLine();
            entrada.nextLine();
        }

    }

    public void mostrarPenalizaciones() {
        System.out.println("Digita el CURP del socio para mostrar sus penalizaciones activas:");
        String curp = "";
        String mensaje = "";
        // System.out.flush();
        curp = entrada.nextLine().toUpperCase();
        curp = entrada.nextLine().toUpperCase();
        mensaje = objControl.mostrarPenalizacionesActivas(curp);
        if (!mensaje.equals("")) {
            System.out.println(mensaje);
        } else {
            System.out.println("El usuario no tiene penalizaciones activas");
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();

    }

    public void mostrarTodasLasPenalizaciones() {
        String mensaje = "";
        mensaje = objControl.mostrarPenalizacionesActivas();
        if (!mensaje.equals("")) {
            System.out.println(mensaje);
        } else {
            System.out.println("No hay penalizaciones activas");
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();

    }

    public void pagarDeuda() {
        System.out.println("Digita folio del prestamo:");
        int folio = 0;
        folio = entrada.nextInt();
        objControl.actualizarPenalizacion(folio);
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }

    public void mostrarPrestamo() {
        int folio = 0;
        limpiarPantalla();
        String linea;
        System.out.println("Ingrese folio del prestamo: ");
        folio = entrada.nextInt();
        int idlibro = objControl.buscarPrestamoPorFolio(folio);
        if (idlibro != 0) {
            LocalDate fechaIngreso = LocalDate.now();
            Prestamo prestamo = new Prestamo();
            prestamo = objControl.mostrarPrestamo(folio);
            System.out.println("\n*********************************");
            System.out.println("Fecha de egreso: " + prestamo.getFechaEgreso());
            System.out.println("Fecha limite: " + prestamo.getFechaLimite());
            System.out.println("Fecha ingreso: " + prestamo.getFechaIngreso());
            System.out.println("Socio: " + prestamo.getIdSocio());
            System.out.println("Libro: " + prestamo.getIdLibro());
            System.out.println("Presione enter para continuar...");
            linea = entrada.nextLine();
            linea = entrada.nextLine();
        } else {
            System.out.println("El prestamo con folio " + folio + " no existe");
            System.out.println("Presione enter para continuar...");
            linea = entrada.nextLine();
            linea = entrada.nextLine();
        }
    }

}