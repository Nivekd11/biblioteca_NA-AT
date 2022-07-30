package entities;

import java.util.Date;

/**
 * 
 */
public class Penalizacion {

    /**
     * Default constructor
     */
    public Penalizacion() {
    }

    /**
     * 
     */
    private String folio;

    /**
     * 
     */
    private boolean estatus;

    /**
     * 
     */
    private Date fechaCreacion;

    /**
     * 
     */
    private double tipoPenalizacion;

    /**
     * 
     */
    private double costo;

    /**
     * @return folio
     */
    public String getFolio() {
        return this.folio;
    }

    /**
     * @return estatus
     */
    public boolean getEstatus() {
        return this.estatus;
    }

    /**
     * @return fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * @return tipoPenalizacion
     */
    public double getTipoPenalizacion() {
        return this.tipoPenalizacion;
    }

    /**
     * @return
     */
    public double getCosto() {
        return this.costo;
    }

    /**
     * @param folio
     */
    public void setFolio(String folio) {
        // TODO implement here
    }

    /**
     * @param estatus
     */
    public void setEstatus(boolean estatus) {
        // TODO implement here
    }

    /**
     * @param fecha
     */
    public void setFechaCreacion(Date fecha) {
        // TODO implement here
    }

    /**
     * @param tipoPenalizacion
     */
    public void setTipoPenalizacion(double tipoPenalizacion) {
        // TODO implement here
    }

    /**
     * @param costo
     */
    public void setCosto(double costo) {
        // TODO implement here
    }

}