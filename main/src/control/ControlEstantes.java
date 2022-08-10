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
     * Método para crear un nuevo estante.
     *
     * @param nombre  Nombre del estante
     * @param seccion Nombre de la seccion a la que pertenecerá
     */
    public void crearEstante(String nombre, String seccion) {
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps;
        String sql = "INSERT INTO ESTANTE (nombre, idseccion) values (?,?)";
        int idSeccion;


        // Acción: Comprobar que la sección existe para guardar su id
        idSeccion = buscarIdSeccion(seccion);
        // Condición: Realizar la inserción de una nueva sección en caso de que no exista
        if (idSeccion == 0) {
            crearSeccion(seccion);
        }
        // Acción: Realizar la inserción con una sección ya existente
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idSeccion);
            ps.execute();
            System.out.println("Estante insertado");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Método para obtener el id de una Sección.
     *
     * @param seccion Nombre de la sección que se va a buscar
     * @return int -> Identificador de la sección.
     */
    private int buscarIdSeccion(String seccion) {
        //Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps;
        ResultSet rs;
        int idSeccion = 0;
        String confirmarIdSeccion = "SELECT idseccion FROM seccion WHERE nombre = ?;";

        // Action: Comprobar que la sección existe
        try {
            ps = conexion.prepareStatement(confirmarIdSeccion);
            ps.setString(1, seccion);
            rs = ps.executeQuery();
            idSeccion = rs.getInt("idseccion");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return idSeccion;
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
        PreparedStatement query;
        ResultSet rs;

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
        PreparedStatement query;
        ResultSet rs;

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
        PreparedStatement query;
        ResultSet rs;

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
     * Método para crear una nueva sección.
     *
     * @param nombre Nombre de la sección
     */
    private void crearSeccion(String nombre) {
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps;
        String sql = "INSERT INTO Seccion (nombre) values (?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}