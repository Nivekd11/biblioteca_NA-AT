package entities;

public class Autor {
    private int idautor;
    private String nombre;

    public Autor() {

    }

    public int getIdautor() {
        return idautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "id" + idautor + ". Nombre: " + nombre;
    }

}
