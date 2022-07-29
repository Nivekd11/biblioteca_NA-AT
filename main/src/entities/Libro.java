package entities;

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
    private int id;

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
    private boolean disponibilidad;

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
    private String estante;

    /**
     * 
     */
    private String editorial;

    /**
     * 
     */
    private boolean tienePenalizacion;

    /**
     * 
     */
    private String autor;

    /**
     * 
     */
    public void getDatosLibro() {
        // TODO implement here
    }

    /**
     * 
     */
    public int getId() {
        return this.id;
    }

    /**
     * 
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * 
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * 
     */
    public boolean getDisponibilidad() {
        return this.disponibilidad;
    }

    /**
     * 
     */
    public String getEdicion() {
        return this.edicion;
    }

    /**
     * 
     */
    public double getValor() {
        return this.valor;
    }

    /**
     * 
     */
    public String getEstante() {
        return this.estante;
    }

    /**
     * 
     */
    public String getEditorial() {
        return this.editorial;
    }

    /**
     * 
     */
    public boolean getTienePenalizacion() {
        return this.tienePenalizacion;
    }

    /**
     * 
     */
    public String getAutor() {
        return this.autor;
    }

}