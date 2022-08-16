package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    Connection conexion;
    private Estante objEstante;
    private Seccion objSeccion;
    private List<Estante> listEstantes;
    private List<Seccion> listSecciones;

    public ControlEstantes() {
        conexion = ConexionBD.connectDatabase();
        listEstantes = new ArrayList<Estante>();
        listSecciones = new ArrayList<Seccion>();
    }

    /**
     * Método para crear un nuevo estante.
     *
     * @param nombre  Nombre del estante
     * @param seccion Nombre de la seccion a la que pertenecerá
     */
    public void crearEstante(String nombre, int seccion) {
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps;
        String sql = "INSERT INTO ESTANTE (nombre, idseccion) values (?,?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, seccion);
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
     * @return int → Identificador de la sección.
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
            // Situation: La consulta mostró que la sección existe
            if (rs.next()) {
                idSeccion = rs.getInt("idseccion");
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

        return idSeccion;
    }

    /**
     * Método para obtener la información de un estante específico.
     *
     * @param nombre Identificador del estante
     * @return Estante → Información del estante.
     */
    public Estante mostrarEstante(String nombre) {
        // Variables
        Estante objEstante = new Estante();
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement query;
        ResultSet rs;

        // Action: Realizar consulta con la base de datos
        try {
            query = conexion.prepareStatement(
                    "SELECT e.idestante, e.nombre AS estante, s.nombre AS seccion FROM Estante e, Seccion s WHERE e.idseccion = s.idseccion AND e.nombre = ?;");
            query.setString(1, nombre);
            rs = query.executeQuery();
            if (rs.next()) {
                objEstante.setNombre(rs.getString("estante"));
                objEstante.setSeccion(rs.getString("seccion"));
                return objEstante;
            }
            System.out.println("No hay ningún estante con esta nomenclatura");
            return null;
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
    }

    /**
     * Método para obtener la información de todos los estantes.
     *
     * @return List<Estante> → Lista de estantes.
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
                Estante estante = new Estante();
                estante.setNombre((rs.getString("estante")));
                estante.setSeccion((rs.getString("seccion")));
                listEstantes.add(estante);
            }
            return listEstantes;
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
    }

    /**
     * Método para obtener la información de todas las secciones.
     *
     * @return List<Seccion> → Lista de secciones.
     */
    public List<Seccion> mostrarSecciones() {
        // Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement query = null;
        ResultSet rs = null;

        // Action: Realizar consulta con la base de datos
        try {
            query = conexion.prepareStatement("SELECT * FROM Seccion ORDER BY idseccion;");
            rs = query.executeQuery();
            while (rs.next()) {
                Seccion seccion = new Seccion();
                seccion.setNombre(rs.getString("nombre"));
                listSecciones.add(seccion);
            }
            return listSecciones;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }


    }

    /**
     * Método para crear una nueva sección.
     *
     * @param nombre Nombre de la sección
     */
    private void crearSeccion(String nombre) {
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "INSERT INTO Seccion (nombre) values (?);";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.execute();
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

    public int comprobarSeccion(String nombre) {
        // Variables
        Connection conexion = ConexionBD.connectDatabase();
        PreparedStatement ps;
        ResultSet rs = null;
        int idSeccion = 0;
        String sql = "INSERT INTO Seccion (nombre) values (?)";

        // Action: Corroborar si la sección existe
        idSeccion = buscarIdSeccion(nombre);
        // Situation: La sección no existe aún
        if (idSeccion == 0) {
            crearSeccion(nombre);
            // Action: Guardamos el ID para retornarlo
            idSeccion = buscarIdSeccion(nombre);
        }
        return idSeccion;
    }

    /**
     * Método para validar la entrada de una sección.
     *
     * @param seccion Nombre de la sección
     * @return Boolean
     */
    public Boolean validarSeccion(String seccion) {
        return seccion.matches("[A-Z]-[0-9]{1,3}");
    }

    /**
     * Método para validar la entrada de la sección.
     *
     * @param opcion Opción seleccionada
     * @return Boolean
     */
    public Boolean validarOpcionMenu (int opcion) {
        return String.valueOf(opcion).matches("[0-5]");
    }

}