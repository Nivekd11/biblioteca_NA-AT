package control;

import entities.Usuario;

/**
 * 
 */
public class Control_Usuarios {

    /**
     * Default constructor
     */
    public Control_Usuarios() {
    }

    /**
     * 
     */
    private Usuario obj_Usuario;

    /**
     * @param usuario
     */
    public void crearUsuario(Usuario usuario) {
        // TODO implement here
    }

    /**
     * @param usuario
     */
    public void actualizarUsuario(Usuario usuario) {
        obj_Usuario = usuario;
        // TODO: Agregar comportamiento
        System.out.println(obj_Usuario);
    }

    /**
     * @param curp
     */
    public void eliminarUsuario(String curp) {
        // TODO implement here
    }

    /**
     * 
     */
    public void mostrarUsuarios() {
        // TODO implement here
    }

}