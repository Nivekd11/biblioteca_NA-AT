package entities;

/**
 * Entidad Estante.
 *
 * @author Samuel Alcántara Chávez
 * @version 1.0
 */
public class Estante {

    /**
     * Constructor vacío
     */
    public Estante() {
    }

    /**
     * Método constructor parametrizado
     *
     * @param id     Identificador del estante
     * @param nombre Nombre del estante
     */
    public Estante(String nombre, String id, String seccion) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public Estante(String nombre, String seccion) {
        this.nombre = nombre;
        this.seccion = seccion;
    }

    // Atributos
    private String id;
    private String nombre;
    private String seccion;

    /**
     * Método para obtener el identificador del Estante.
     *
     * @return int -> id del estante
     */
    public String getId() {
        return this.id;
    }

    /**
     * Método para obtener el nombre del Estante.
     *
     * @return String -> Nombre del estante
     */
    public String getNombre() {
        return this.nombre;
    }


    /**
     * Método que actualiza el valor del identificador del estante.
     *
     * @param id Identificador del estante
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método que actualiza el nombre del estante.
     *
     * @param nombre Nombre del estante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return this.seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}