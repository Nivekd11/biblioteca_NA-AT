package entities;

import java.util.*;
import java.sql.Date;
/**
 * 
 */
public class Socio {

    private String curp;
    private String nombre;
    private java.sql.Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String estatus;
    private List<Prestamo> prestamos;
    private boolean tienePenalizaciones;

    public Socio() {
    }

    public String geCurp() {
        return this.curp;
    }

    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return
     */
    public java.sql.Date getFechaNacimiento() {
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

    public String getEstatus() {
        return this.estatus;
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
    public void setCurp(String curp) {
        if (curp.length() == 18) {
            this.curp = curp;
        } else {
            System.err.println("Curp invalid: " + curp);
        }

    }

    public void setNombre(String nombre) {
        if (nombre.length() > 0) {
            this.nombre = nombre;
        }
    }

    /**
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {

        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param telefono
     */
    public void setTelefono(String telefono) {
        // TODO implement here
        this.telefono = telefono;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        // TODO implement here
        this.email = email;
    }

    /**
     * @param prestamos
     */
    public void setPrestamos(List<Prestamo> prestamos) {
        // TODO implement here
        this.prestamos = prestamos;
    }

    /**
     * @param tienePenalizacion
     */
    public void setTienePenalziaciones(boolean tienePenalizaciones) {
        this.tienePenalizaciones = tienePenalizaciones;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String toString() {
        return String.format("Curp: " + curp + " \nNombre: " + nombre + " \nFecha de nacimiento: "
                + fechaNacimiento.toString() + " \nNumero: " + telefono + " \nCorreo: " + email + " \nDireccion: " + direccion
                + " \nEstatus: " + estatus);
    }
}