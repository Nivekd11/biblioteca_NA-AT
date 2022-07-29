package entities;

import java.util.*;

/**
 * 
 */
public class Usuario {

    /**
     * Default constructor
     */
    public Usuario() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private Date fechaNacimiento;

    /**
     * 
     */
    private String direccion;

    /**
     * 
     */
    private String telefono;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private List<Prestamo> prestamos[];

    /**
     * 
     */
    private boolean tienePenalizaciones;

    /**
     * 
     */
    public void deleteClient() {
        // TODO
    }

    /**
     * @param Client
     */
    public void updateClientData(Usuario client) {
        // TODO
    }

    /**
     * 
     */
    public void getDatosCliente() {
        // TODO
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
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * 
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * 
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * 
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     */
    public List<Prestamo>[] getPrestamos() {
        return this.prestamos;
    }

    /**
     * 
     */
    public boolean getTienePenalizaciones() {
        return this.tienePenalizaciones;
    }

}