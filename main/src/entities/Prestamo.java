package entities;

import java.util.*;

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
    private Date fechaIngreso;

    /*
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
    private Usuario cliente;

    /**
     * 
     */
    private Libro libro;

    /**
     * 
     */
    public void finishLoan() {
        // TODO
    }

    /**
     * 
     */
    public void updateLoanState() {
        // TODO
    }

    /**
     * 
     */
    public void getDatosPrestamo() {
        // TODO
    }

    /**
     * 
     */
    public String getFolio() {
        return this.folio;
    }

    /**
     * 
     */
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    /**
     * 
     */
    public Date getFechaLimite() {

        return this.fechaLimite;
    }

    /**
     * 
     */
    public Date getFechaEgreso() {
        return this.fechaEgreso;
    }

    /**
     * 
     */
    public Usuario getCliente() {
        return this.cliente;
    }

    /**
     * 
     */
    public Libro getLibro() {
        return this.libro;
    }

}