package boundaries;

import control.Control_Socios;
import entities.Socio;
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

    public void manejarMenu(){
        int opcion = 0;
        do{
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Manejo de Socios \n\nDigita la opción que deseas utilizar.\n\n1. Buscar un soció\n2. Ver todos los socios\n3. Eliminar a un soció\n4. Modificar los datos de un soció\n5. Regresar al menu principal\n\nDigita la opción: ");
            opcion = entrada.nextInt(10);
            seleccionarOpcion(opcion);            
        }while(opcion>0);
    }


    public void seleccionarOpcion(int opcion){
        switch(opcion){
            case 1:{
               System.out.println("Escribe el CURP del Socio que deseas buscar:");
               String curp = entrada.next();
               mostrarSocio(objControl.mostrarSocio(curp)); 
            }
            case 2:{
                mostrarSocios(objControl.mostrarSocios());
            }
        }
    }

    public void mostrarSocio(Socio socio){
        System.out.println("Datos del Socio\n");
        System.out.println(socio.toString());
        entrada.next();
    }

    public void mostrarSocios(List<Socio> socios){
        System.out.println("Datos de los Socios\n");
        for(Socio socio : socios){
            System.out.println(socio.toString());
            System.out.println("****************************************");
        }
        entrada.next();
    }
    
    

}