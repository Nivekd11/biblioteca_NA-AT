package control;

import java.sql.*;
import java.util.*;

import entities.Socio;

/**
 * 
 */
public class Control_Socios {

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
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "INSERT INTO socio (curp,nombre,fecha_nacimiento,telefono,correo,direccion,estatus) VALUES(?,?,?,?,?,?,?);";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.geCurp());
            ps.setString(2, socio.getNombre());
            ps.setDate(3, socio.getFechaNacimiento());
            ps.setString(4, socio.getTelefono());
            ps.setString(5, socio.getEmail());
            ps.setString(6, socio.getDireccion());
            ps.setObject(7, socio.getEstatus(), Types.OTHER);
            ps.executeQuery();

        } catch (Exception e) {
            //System.err.println(e);

        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                // System.err.println(e);
            }
        }
    }

    /**
     * @param socio
     */
    public void actualizarSocio(Socio socio, String curp) {
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE socio SET curp = ?, nombre = ? , fecha_nacimiento = ?, telefono = ?,correo = ?,direccion = ?,estatus = ? WHERE curp = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.geCurp());
            ps.setString(2, socio.getNombre());
            ps.setDate(3, socio.getFechaNacimiento());
            ps.setString(4, socio.getTelefono());
            ps.setString(5, socio.getEmail());
            ps.setString(6, socio.getDireccion());
            ps.setObject(7, socio.getEstatus(), Types.OTHER);
            ps.setString(8, socio.geCurp());
            ps.executeQuery();

        } catch (Exception e) {
            //System.err.println(e);

        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                // System.err.println(e);
            }
        }

    }

    /**
     * @param curp
     */
    public void eliminarSocio(String curp) {
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "DELETE FROM socio WHERE curp=?;";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, curp);
            ps.executeQuery();
            System.out.println("El socio se a actualizado con exito");
        } catch (Exception e) {
           // System.err.println(e);

        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                // System.err.println(e);
            }
        }

    }

    /**
     * @return
     */
    public List<Socio> mostrarSocios() {
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from socio";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
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
     * @param curp
     * @return
     */
    public Socio mostrarSocio(String curp) {

        conexion = ConexionBD.connectDatabase();
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