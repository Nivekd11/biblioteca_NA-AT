package entities;

import java.util.List;

/**
 * 
 */
public class Libro {

    /**
     * Default constructor
     */
    public Libro() {
    }

    /**
     * 
     */
    private int idLibro;

    /**
     * 
     */
    private String isbn;

    /**
     * 
     */
    private String titulo;

    /**
     * 
     */
    private int ejemplares;

    /**
     * 
     */
    private String edicion;

    /**
     * 
     */
    private double valor;

    /**
     * 
     */
    private List<String> estante;

    private List<String> seccion;

    /**
     * 
     */
    private String editorial;

    /**
     * 
     */
    private int idEditorial;
    /**
    * 
    */

    private boolean tienePenalizacion;

    /**
     * 
     */
    private List<String> autor;

    /**
     * @return id
     */
    public int getIdLibro() {
        return this.idLibro;
    }

    /**
     * @return isbn
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * @return titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @return
     */
    public int getEjemplares() {
        return this.ejemplares;
    }

    /**
     * @return edicion
     */
    public String getEdicion() {
        return this.edicion;
    }

    /**
     * @return edicion
     */

    /**
     * @return valor
     */
    public double getValor() {
        return this.valor;
    }

    /**
     * @return estante
     */
    public List<String> getEstante() {
        return this.estante;
    }

    public List<String> getSeccion() {
        return this.seccion;
    }

    /**
     * @return editorial
     */
    public String getEditorial() {
        return this.editorial;
    }

    public int getIdEditorial() {
        return this.idEditorial;
    }

    /**
     * @return tienePenalizacion
     */
    public boolean getTienePenalizacion() {
        return this.tienePenalizacion;
    }

    /**
     * @return autor
     */
    public List<String> getAutor() {
        return this.autor;
    }

    /**
     * @param idLibro
     */
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;

    }

    /**
     * @param isbn
     */
    public void setIsbn(String isbn) {
        if (isbn.length() == 10 || isbn.length() == 13) {
            this.isbn = isbn;
        } else {
            // System.err.println("El dato ingresado no es un ISBN valido.");
        }
    }

    /**
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param Numero de ejemplares disponibles
     */
    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    /**
     * @param edicion
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @param estante
     */
    public void setEstante(List<String> estante) {
        this.estante = estante;
    }

    /**
     * @param seccion
     */

    public void setSeccion(List<String> seccion) {
        this.seccion = seccion;
    }

    /**
     * @param editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    /**
     * @param tienePenalizacion
     */
    public void setTienePenalizacion(boolean tienePenalizacion) {
        // TODO implement here
        this.tienePenalizacion = tienePenalizacion;
    }

    /**
     * @param autor
     */
    public void setAutor(List<String> autor) {
        // TODO implement here
        this.autor = autor;
    }

    public void addEstante (String estante){
        this.estante.add(estante);
    }

    public String toString() {
        return "idLibro: " + idLibro + "\nISBN: " + isbn + "\nTitulo: " + titulo + "\nPrecio: " + valor
                + "\n#Ejemplares: " + ejemplares + "\nEdicion: " + edicion + "\nIdEditorial: " + idEditorial
                + "\nEditorial: " + editorial + "\nAutores: " + autor.toString() + "\nEstantes" + estante
                + "\nSeccion: "
                + seccion + ".";
    }

}