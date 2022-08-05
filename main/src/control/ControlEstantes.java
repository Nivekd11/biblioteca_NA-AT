package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Estante;
import entities.Seccion;

/**
 * Control Estantes.
 *
 * @author Samuel Alcántara Chávez
 * @version 1.0
 */
public class ControlEstantes {

    // Atributos
    private Estante objEstante;
    private Seccion objSeccion;
    private List<Estante> listEstantes;
    private List<Seccion> listSecciones;

    /**
     * @param nombre
     */
    public void crearEstante(String nombre) {
        // TODO implement here
    }

    /**
     * Método para obtener la información de un estante específico.
     *
     * @param nombre Identificador del estante
     * @return Estante -> Información del estante.
     */
    public Estante mostrarEstante(String nombre) {
        // Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement query = null;
        ResultSet rs = null;

        // Action: Realizar consulta con la base de datos
        try {
            query = conexion.prepareStatement(
                    "SELECT e.idestante, e.nombre AS estante, s.nombre AS seccion FROM Estante e, Seccion s WHERE e.idseccion = s.idseccion AND e.nombre = ?;");
            query.setString(1, nombre);
            rs = query.executeQuery();
            while (rs.next()) {
                objEstante.setId((rs.getString("idestante")));
                objEstante.setNombre((rs.getString("estante")));
                objEstante.setSeccion((rs.getString("seccion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return objEstante;
    }

    /**
     * Método para obtener la información de todos los estantes.
     *
     * @return List<Estante> -> Lista de estantes.
     */
    public List<Estante> mostrarEstantes() {
        // Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement query = null;
        ResultSet rs = null;

        // Action: Realizar consulta con la base de datos
        try {
            query = conexion.prepareStatement(
                    "SELECT e.nombre AS estante, s.nombre AS seccion FROM Estante e, Seccion s WHERE e.idseccion = s.idseccion;");

            rs = query.executeQuery();
            while (rs.next()) {
                objEstante.setId((rs.getString("idestante")));
                objEstante.setNombre((rs.getString("estante")));
                objEstante.setSeccion((rs.getString("seccion")));
                listEstantes.add(objEstante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error: El libro no se pudo crear");
                e.printStackTrace();
            }
        }

        return listEstantes;
    }

    /**
     * Método para obtener la información de todas las secciones.
     *
     * @return List<Seccion> -> Lista de secciones.
     */
    public List<Seccion> mostrarSecciones() {
        // Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement query = null;
        ResultSet rs = null;

        // Action: Realizar consulta con la base de datos
        try {
            query = conexion.prepareStatement(
                    "SELECT * FROM Seccion ORDER BY idseccion;");

            rs = query.executeQuery();
            while (rs.next()) {
                objSeccion.setId((rs.getString("idseccion")));
                objSeccion.setNombre((rs.getString("nombre")));
                listSecciones.add(objSeccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error: No se han podido mostrar las secciones");
                e.printStackTrace();
            }
        }

        return listSecciones;
    }

    /**
     * @param nombre
     */
    public void crearSeccion(String nombre) {
        // TODO implement here
    }

}