package entities;

/**
 * Entidad Seccion.
 *
 * @author Samuel Alcántara Chávez
 * @version 1.0
 *
 */
public class Seccion {

    /**
     * Constructor vacío
     */
    public Seccion() {
    }

    /**
     * Método constructor parametrizado
     *
     * @param nombre Nombre de la sección
     * @param id     Identificador de la sección
     */
    public Seccion(String nombre, char id) {
        this.id = id;
        this.nombre = nombre;
    }

    // Atributos
    private String nombre;
    private char id;

    /**
     * Método para obtener el identificador de la Sección.
     *
     * @return char -> id de la sección
     */
    public char getId() {
        return this.id;
    }

    /**
     * Método para obtener el identificador de la Sección.
     *
     * @return String -> Nombre de la sección
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método que actualiza el valor del identificador de la sección.
     *
     * @param id Identificador de la sección
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método que actualiza el nombre de la sección.
     *
     * @param nombre Nombre de la sección
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}