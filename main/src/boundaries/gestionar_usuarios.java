package boundaries;

import control.Control_Socios;
import entities.Socio;

//import java.lang.System.Logger;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * 
 */
public class gestionar_usuarios {

    private Control_Socios objControl;
    private Scanner entrada;

    public gestionar_usuarios() {
        this.objControl = new Control_Socios();
        entrada = new Scanner(System.in);
    }

    public void manejarMenu() {
        int opcion = 0;
        do {
            limpiarPantalla();
            System.out.println(
                    "Manejo de Socios \n\nDigita la opción que deseas utilizar.\n\n1. Buscar un soció\n2. Ver todos los socios\n3. Registrar a un soció\n4. Eliminar a un soció\n5. Modificar los datos de un soció\n6. Regresar al menu principal\n\nDigita la opción: ");
            opcion = entrada.nextInt(10);
            seleccionarOpcion(opcion);
        } while (opcion > 0 && opcion < 6);
    }

    public void seleccionarOpcion(int opcion) {
        switch (opcion) {
            case 1: {
                limpiarPantalla();
                System.out.println("Escribe el CURP del Socio que deseas buscar:");
                String curp = entrada.next();
                mostrarSocio(objControl.mostrarSocio(curp));
                break;
            }
            case 2: {
                limpiarPantalla();
                mostrarSocios(objControl.mostrarSocios());
                break;
            }
            case 3: {
                limpiarPantalla();
                registrarSocio();
                break;
            }
            case 4: {
                limpiarPantalla();
                System.out.println("Escribe el CURP del Socio que deseas eliminar:");
                String curp = entrada.next();
                //System.out.println("El socio se a eliminado con exito");
                //mostrarSocio(objControl.mostrarSocio(curp));
                if(objControl.mostrarSocio(curp)!=null){
                    objControl.eliminarSocio(curp);
                }
                else{
                    System.out.println("El socio no existe");
                }
                
                System.out.println("Presiones cualquier tecla y un enter para continuar....");
                entrada.next();
                break;
            }
            case 5: {
                limpiarPantalla();
                System.out.println("Escribe el CURP del Socio que deseas actualizar:");
                String curp = entrada.next();
                Socio socio = actualizarSocio(objControl.mostrarSocio(curp));
                if(socio!=null)
                    objControl.actualizarSocio(socio, curp);
                break;
            }
        }
    }

    public void mostrarSocio(Socio socio) {
        if(socio == null){
            System.out.println("El socio no existe, por favor registralo");
            
            
        }
        else{
            System.out.println("Datos del Socio\n");
        System.out.println(socio.toString()+"\n");
        }
        
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }

    public void mostrarSocios(List<Socio> socios) {
        System.out.println("Datos de los Socios\n");

        for (Socio socio : socios) {
            System.out.println(socio.toString());
            System.out.println("****************************************");
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }

    public void registrarSocio() {
        System.out.println("Ingresa Los datos del socio.\n");
        Socio socio = new Socio();
        System.out.println("\nEscribe el CURP del socio:");
        socio.setCurp(entrada.next());
        System.out.println("\nEscribe el nombre del socio:");
        entrada.nextLine();
        socio.setNombre(entrada.nextLine());
        System.out.println("\nEscribe la fecha de nacimiento del socio:");
        socio.setFechaNacimiento(registrarFechaDeNacimiento());
        System.out.println("\nDigita el numero de telefono del socio:");
        socio.setTelefono(entrada.next());
        System.out.println("\nEscribe el correo del socio:");
        socio.setEmail(entrada.next());
        System.out.println("\nEscribe la dirección del socio:");
        entrada.nextLine();
        socio.setDireccion(entrada.nextLine());
        System.out.println("\nDigita el estatus del socio:");
        System.out.println("1. Habilitado");
        System.out.println("2. Por Cumplir");
        if (entrada.nextInt() == 1) {
            socio.setEstatus("Habilitado");
        } else {
            socio.setEstatus("Por Cumplir");
        }
        objControl.crearSocio(socio);
    }

    public Socio actualizarSocio(Socio socio) {
        if(socio !=null){
            System.out.println("Digita 1 si deseas modificar o 0 si no");
        System.out.println("\n Deseas actualizar el CURP " + socio.geCurp());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el CURP del socio:");
            socio.setCurp(entrada.next());
        }
        System.out.println("\n Deseas actualizar el nombre " + socio.getNombre());
        if (entrada.nextInt() > 0) {
            entrada.nextLine();
            System.out.println("\nEscribe el nombre del socio:");
            socio.setNombre(entrada.nextLine());
        }

        System.out.println("\n Deseas actualizar la fecha de nacimiento " + socio.getFechaNacimiento());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe la fecha de nacimiento del socio:");
            socio.setFechaNacimiento(registrarFechaDeNacimiento());
        }

        System.out.println("\n Deseas actualizar el numero de telefono " + socio.getTelefono());
        if (entrada.nextInt() > 0) {
            System.out.println("\nDigita el numero de telefono del socio:");
            socio.setTelefono(entrada.next());
        }

        System.out.println("\n Deseas actualizar el correo " + socio.getEmail());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe el correo del socio:");
            socio.setEmail(entrada.next());
        }

        System.out.println("\n Deseas actualizar la dirección " + socio.getDireccion());
        if (entrada.nextInt() > 0) {
            System.out.println("\nEscribe la dirección del socio:");
            entrada.nextLine();
            socio.setDireccion(entrada.nextLine());
        }

        System.out.println("\n Deseas actualizar el estatus " + socio.getEstatus());
        if (entrada.nextInt() > 0) {
            System.out.println("\nDigita el estatus del socio:");
            System.out.println("1. Habilitado");
            System.out.println("2. Por Cumplir");
            if (entrada.nextInt() == 1) {
                socio.setEstatus("Habilitado");
            } else {
                socio.setEstatus("Por Cumplir");
            }
        }
        return socio;
        }else{
            System.out.println("El socio no se encuentra registrado\n\n");
            System.out.println("Presiones cualquier tecla y un enter para continuar....");
            entrada.next();
            return null;
        }
        
    }

    public void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public java.sql.Date registrarFechaDeNacimiento() {
        int anio = 0;
        int mes = 0;
        int dia = 0;
        boolean buenaFecha = false;
        do {
            do {
                System.out.println("\nDigita el año");
                anio = Integer.parseInt(entrada.next());
            } while (anio < 1900 && anio > 2017);

            do {
                System.out.println("\nDigita el numero del mes");
                mes = Integer.parseInt(entrada.next());
            } while (mes < 1 && mes > 12);

            do {
                System.out.println("\nDigita el día");
                dia = Integer.parseInt(entrada.next());

            } while (dia < 1 && dia > 31);

            try {
                return java.sql.Date.valueOf(LocalDate.of(anio, Month.of(mes), dia));
            } catch (DateTimeException dte) {
                System.out.println("La fecha escrita es incorrecta");
            }
        } while (buenaFecha == false);

        return null;
    }

}