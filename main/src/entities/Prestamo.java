package entities;


public class Prestamo {
    private String folio;
    private java.sql.Date  fechaIngreso;
    private java.sql.Date  fechaLimite;
    private java.sql.Date  fechaEgreso;
    private String idSocio;
    private int idLibro;

    public Prestamo(String folio, java.sql.Date  fechaIngreso, java.sql.Date  fechaLimite, java.sql.Date  fechaEgreso, String idSocio, int idLibro) {
        this.folio = folio;
        this.fechaIngreso = fechaIngreso;
        this.fechaLimite = fechaLimite;
        this.fechaEgreso = fechaEgreso;
        this.idSocio = idSocio;
        this.idLibro = idLibro;
    }

    public Prestamo() {
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public java.sql.Date  getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(java.sql.Date  fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public java.sql.Date  getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(java.sql.Date  fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public java.sql.Date  getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(java.sql.Date  fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
}