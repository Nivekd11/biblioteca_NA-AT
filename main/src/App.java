import boundaries.gestionar_usuarios;
import control.ConexionBD;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        //ConexionBD.connectDatabase();
        
        gestionar_usuarios vistaUsuarios =new gestionar_usuarios();
        vistaUsuarios.manejarMenu();
        

    }
    
    
}
