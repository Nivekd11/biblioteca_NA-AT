package entities;

import java.util.Date;

/**
 * 
 */
public class Prestamo {

    /**
     * Default constructor
     */
    public Prestamo() {
    }

    /**
     * 
     */
    private String folio;

    /**
     * 
     */

    /**
     * 
     */
    private Date fechaIngreso;
    /**
     * 
     */
    private Date fechaLimite;

    /**
     * 
     */
    private Date fechaEgreso;

    /**
     * 
     */
    private Socio socio;

    /**
     * 
     */
    private Libro libro;

    /**
     * @return
     */
    public String getFolio() {
        return this.folio;
    }

    /**
     * @return
     */
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    /**
     * @return
     */
    public Date getFechaLimite() {
        return this.fechaLimite;
    }

    /**
     * @return
     */
    public Date getFechaEgreso() {
        return this.fechaEgreso;
    }

    /**
     * @return
     */
    public Socio getSocio() {
        return this.socio;
    }

    /**
     * @return
     */
    public Libro getLibro() {
        return this.libro;
    }

    /**
     * @param folio
     */
    public void setFolio(String folio) {
        // TODO implement here
    }

    /**
     * @param fechaIngreso
     */
    public void setFechaIngreso(Date fechaIngreso) {
        // TODO implement here
    }

    /**
     * @param fechaLimite
     */
    public void setFechaLimite(Date fechaLimite) {
        // TODO implement here
    }

    /**
     * @param fechaEgreso
     */
    public void setFechaEgreso(Date fechaEgreso) {
        // TODO implement here
    }

    /**
     * @param socio
     */
    public void setSocio(Socio socio) {
        // TODO implement here
    }

    /**
     * @param libro
     */
    public void setLibro(Libro libro) {
        // TODO implement here
    }

}