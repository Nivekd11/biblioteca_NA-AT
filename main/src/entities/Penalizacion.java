package entities;

import java.util.*;

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
    public String folio;

    /**
     * 
     */
    public boolean estatus;

    /**
     * 
     */
    public Date fechaCreacion;

    /**
     * 
     */
    public double tipoPenalizacion;

    /**
     * 
     */
    public double costo;

    /**
     * 
     */
    public String getFolio() {
        return this.folio;
    }

    /**
     * 
     */
    public boolean getEstatus() {
        return this.estatus;
    }

    /**
     * 
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * 
     */
    public double getTipoPenalizacion() {
        return this.tipoPenalizacion;
    }

    /**
     * 
     */
    public double getCosto() {
        return this.costo;
    }

    /**
     * 
     */
    public Penalizacion getDatosPenalizacion() {
        return this;
    }

}