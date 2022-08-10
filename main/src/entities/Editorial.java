package entities;

public class Editorial {
    private int ideditorial;
    private String nombre;

    public Editorial() {

    }

    public int getIdEditorial() {
        return ideditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdEditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "id" + ideditorial + ". Nombre: " + nombre;
    }
}
