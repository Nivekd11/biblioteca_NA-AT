package control;

import java.sql.*;
import java.util.*;

import entities.Conexion;
import entities.Socio;

/**
 * 
 */
public class Control_Socios extends Conexion {

    Connection conexion;
    private Socio objSocio;
    private List<Socio> listSocios;

    public Control_Socios() {

       conexion = ConexionBD.connectDatabase();
       listSocios = new ArrayList<Socio>();
    }

    

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
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from socio";
        try{
           ps = conexion.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
                Socio socio = new Socio();
                socio.setCurp(rs.getString("curp"));
                socio.setNombre(rs.getString("nombre"));
                socio.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                socio.setTelefono(rs.getString("telefono"));
                socio.setEmail(rs.getString("correo"));
                socio.setDireccion(rs.getString("direccion"));
                socio.setEstatus(rs.getString("estatus"));
                this.listSocios.add(socio);
           }
           
            return listSocios;
        } catch (Exception e) {
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
     * @param id
     * @return
     */
    public Socio mostrarSocio(String curp) {
        // TODO implement here
        objSocio = new Socio();
        PreparedStatement ps = null;
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