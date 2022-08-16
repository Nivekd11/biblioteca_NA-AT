package boundaries;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import control.Control_Prestamos;
import java.util.*;

import java.time.ZoneId;
import java.util.Scanner;
import entities.Prestamo;
public class gestionar_préstamos {

    private Scanner entrada;
    private Control_Prestamos objControl;

    public gestionar_préstamos() {
        this.objControl = new Control_Prestamos();
         entrada = new Scanner(System.in);
    }

    public void menu(){
        
        //System.out.flush();
        int opcion = 0;
        while (opcion < 9){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print(
                    "\nManejo de Prestamos \n\nDigita la opción que deseas utilizar.\n\n1. Generar Prestamo\n2. Buscar Prestamo\n3. Entrega de Libro\n4. Ver prestamos\n5. Crear Penalización \n6. Ver penalizaciones de socio \n7. Ver todas las penalizaciones \n8. Pagar deuda \n9. Salir\n");
            opcion = entrada.nextInt();
            switch(opcion){
                case 1:
                    generarPrestamo();
                    break;
                case 3:
                    entregarLibro();
                    break;
                case 5:
                    generarPenalizacionPerdida();
                case 6:
                    mostrarPenalizaciones();
                    break;
                case 7:
                    mostrarTodasLasPenalizaciones();
                    break;
                case 8:
                    pagarDeuda();
                


            }
        }

    }

    public void generarPrestamo(){
        limpiarPantalla();
        Prestamo prestamo = new Prestamo();
        String curp = "";
        System.out.println("Ingrese CURP del socio: ");
        //System.out.flush();
        curp = entrada.nextLine().toUpperCase();
        curp = entrada.nextLine().toUpperCase();
        if(!objControl.buscarSocio(curp).equals("")){
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

        }else{
            System.out.print("El socio no existe UnU\n"); 
        }


    }

    public  void entregarLibro(){
        int folio = 0;
        limpiarPantalla();
        System.out.println("Ingrese folio del prestamo: ");
        folio = entrada.nextInt();
        LocalDate fechaIngreso = LocalDate.now();

    }
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void generarPenalizacionPerdida(){
        int folio = 0;
        String tipoPerdida = "";
        float costo;
        limpiarPantalla();
        System.out.println("Ingrese folio del prestamo: ");
        folio = entrada.nextInt();
        System.out.println("Seleccione el tipo de perdida:");
        System.out.println("1. Perdido");
        System.out.println("2. Estropeado");
        if(entrada.nextInt()== 1){
            tipoPerdida="Perdido";
        }else{
            tipoPerdida = "Estropeado";
        }
        System.out.println("Digite el costo del libro:");
        costo = entrada.nextFloat();
        Date fechaIngreso =new Date(System.currentTimeMillis());
        //objControl.actuaLizarPrestamo(folio, new  java.sql.Date(fechaIngreso.getTime()) );
        Prestamo prestamo = objControl.mostrarPrestamo(folio);
        //prestamo.toString();
        if(prestamo != null){
            int milisecondsByDay = 86400000;
            int dias = (int) ((prestamo.getFechaLimite().getTime()-fechaIngreso.getTime()) / milisecondsByDay);
            establecerPenalizacion(dias,folio,tipoPerdida,costo);
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }

    public void establecerPenalizacion(int dias,int folio,String tipoPerdida,float costo){
        if(dias < 0){
            objControl.actualizarPenalizacion(folio, tipoPerdida, costo);
            entrada.nextLine();
            entrada.nextLine();
        }else{
            objControl.crearPenalizacion(tipoPerdida, new Date(System.currentTimeMillis()), costo, folio);
            entrada.nextLine();
            entrada.nextLine();
        }
    
    }

    public void mostrarPenalizaciones(){
        System.out.println("Digita el CURP del socio para mostrar sus penalizaciones activas:");
        String curp = "";
        String mensaje ="";
        //System.out.flush();
        curp = entrada.nextLine().toUpperCase();
        curp = entrada.nextLine().toUpperCase();
        mensaje = objControl.mostrarPenalizacionesActivas(curp);
        if(!mensaje.equals("")){
            System.out.println(mensaje);
        }else{
            System.out.println("El usuario no tiene penalizaciones activas");
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
        
    }

    public void mostrarTodasLasPenalizaciones(){
        String mensaje ="";
        mensaje = objControl.mostrarPenalizacionesActivas();
        if(!mensaje.equals("")){
            System.out.println(mensaje);
        }else{
            System.out.println("No hay penalizaciones activas");
        }
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
        
    }

    public void pagarDeuda(){
        System.out.println("Digita folio del prestamo:");
        int folio = 0;
        folio=entrada.nextInt();
        objControl.actualizarPenalizacion(folio);
        System.out.println("Presiones cualquier tecla y un enter para continuar....");
        entrada.next();
    }
    
    public static void main(String[] args) {
        gestionar_préstamos objPrestamo = new gestionar_préstamos();
        objPrestamo.menu();
    }
    

}