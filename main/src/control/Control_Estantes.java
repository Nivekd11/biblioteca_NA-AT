package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entities.Estante;
import entities.Seccion;

/**
 * Control Estantes.
 *
 * @author Samuel Alcántara Chávez
 * @version 1.0
 */
public class Control_Estantes {

    /**
     * Constructor vacío
     */
    public Control_Estantes() {
    }

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
     * @param id Identificador del estante
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
            System.err.println(e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        return objEstante;
    }

    /**
     * @param nombre
     */
    public void crearSeccion(String nombre) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Estante> mostrarEstantes() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Seccion> mostrarSecciones() {
        // TODO implement here
        return null;
    }

}