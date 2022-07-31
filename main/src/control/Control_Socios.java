package control;

import java.sql.*;
import java.util.*;

import entities.Conexion;
import entities.Socio;

/**
 * 
 */
public class Control_Socios extends Conexion {

    /**
     * Default constructor
     */
    public Control_Socios() {
    }

    /**
     * 
     */
    private Socio objSocio;

    /**
     * 
     */
    private List<Socio> listSocios;

    /**
     * @param socio
     */
    public void crearSocio(Socio socio) {
        // TODO implement here
    }

    /**
     * @param socio
     */
    public void actualizarSocio(Socio socio) {
        // TODO implement here
    }

    /**
     * @param curp
     */
    public void eliminarSocio(String curp) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Socio> mostrarSocios() {
        // TODO implement here

        return null;
    }

    /**
     * @param id
     * @return
     */
    public Socio mostrarSocio(String curp) {
        // TODO implement here
        objSocio = new Socio();
        PreparedStatement ps = null;
        Connection conexion = getConnection();
        ResultSet rs = null;
        String sql = "SELECT * from socio where curp=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, curp);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.objSocio.setCurp(rs.getString("curp"));
                this.objSocio.setNombre(rs.getString("nombre"));
                this.objSocio.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                this.objSocio.setTelefono(rs.getString("telefono"));
                this.objSocio.setEmail(rs.getString("correo"));
                this.objSocio.setDireccion(rs.getString("direccion"));
                this.objSocio.setEstatus(rs.getString("estatus"));

                return objSocio;

            }

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
        return null;
    }

}