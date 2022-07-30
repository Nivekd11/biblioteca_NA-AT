package entities;

import java.util.*;

/**
 * 
 */
public class Socio {

    /**
     * Default constructor
     */
    public Socio() {
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
    private List<Prestamo> prestamos;

    /**
     * 
     */
    private boolean tienePenalizaciones;

    /**
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return
     */
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * @return
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * @return
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return
     */
    public List<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    /**
     * @return
     */
    public boolean getTienePenalizaciones() {
        return this.tienePenalizaciones;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        // TODO implement here
    }

    /**
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        // TODO implement here
    }

    /**
     * @param direccion
     */
    public void setDireccion(String direccion) {
        // TODO implement here
    }

    /**
     * @param telefono
     */
    public void setTelefono(String telefono) {
        // TODO implement here
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        // TODO implement here
    }

    /**
     * @param prestamos
     */
    public void setPrestamos(List<Prestamo> prestamos) {
        // TODO implement here
    }

    /**
     * @param tienePenalizacion
     */
    public void setTienePenalziaciones(boolean tienePenalizacion) {
        // TODO implement here
    }

}