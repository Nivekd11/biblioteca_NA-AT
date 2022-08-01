package control;

import java.sql.*;
import java.util.*;

import entities.Conexion;
import entities.Libro;

/**
 * 
 */
public class Control_Libros extends Conexion {

    private Libro objLibro;
    private List<Libro> listLibros;
    private Connection conexion;

    public Control_Libros() {
        listLibros = new ArrayList<Libro>();
    }

    /**
     * @param id
     */
    public void actualizarDisponibilidad(int id) {
        // TODO implement here
    }

    /**
     * @param libro
     */
    public void crearLibro(Libro libro) {
        // TODO implement here
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "Insert into libro (titulo, valor, ejemplares, edicion, ideditorial, isbn) values (?,?,?,?,?,?)";

        try {

            ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setDouble(2, libro.getValor());
            ps.setInt(3, libro.getEjemplares());
            ps.setString(4, libro.getEdicion());
            ps.setInt(5, libro.getIdEditorial());
            ps.setString(6, libro.getIsbn());
            ps.execute();
            System.out.println("Libro insertado");
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * @param libro
     */
    public void actualizarLibro(Libro libro) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void eliminarLibro(int id) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Libro> mostrarLibros() {
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from libro";
        try {

            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                objLibro = new Libro();
                this.objLibro.setIdLibro((rs.getInt("idlibro")));
                this.objLibro.setTitulo(rs.getString("titulo"));
                this.objLibro.setValor(rs.getDouble("valor"));
                this.objLibro.setEjemplares(rs.getInt("ejemplares"));
                this.objLibro.setEdicion(rs.getString("edicion"));
                this.objLibro.setIdEditorial(rs.getInt("ideditorial"));
                this.objLibro.setIsbn(rs.getString("isbn"));
                this.listLibros.add(this.objLibro);
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                conexion.close();

            } catch (SQLException e) {
                System.err.println("ERROR. El libro no se pudo crear.");
                System.err.println(e);

            }
        }
        return this.listLibros;

    }

    /**
     * @param id
     * @return
     */
    public Libro mostrarLibro(int id) {
        conexion = ConexionBD.connectDatabase();
        // TODO implement here

        objLibro = new Libro();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from libro where idlibro=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.objLibro.setIdLibro((rs.getInt("idlibro")));
                this.objLibro.setTitulo(rs.getString("titulo"));
                this.objLibro.setValor(rs.getDouble("valor"));
                this.objLibro.setEjemplares(rs.getInt("ejemplares"));
                this.objLibro.setEdicion(rs.getString("edicion"));
                this.objLibro.setIdEditorial(rs.getInt("ideditorial"));
                this.objLibro.setIsbn(rs.getString("isbn"));
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
        return objLibro;
    }

}