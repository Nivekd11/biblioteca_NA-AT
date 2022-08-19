package control;

import java.sql.*;
import java.util.*;

import entities.Autor;
import entities.Editorial;
import entities.Libro;

/**
 * 
 */
public class Control_Libros {

    private Libro objLibro;
    private List<Libro> listLibros;
    private Connection conexion;

    public Control_Libros() {
        listLibros = new ArrayList<Libro>();
    }

    /**
     * @param id
     */
    public void actualizarDisponibilidad(int id, int nuevoValor) {
        // TODO implement here
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE libro SET ejemplares = ? WHERE idlibro = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, nuevoValor);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
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
    public void crearLibro(Libro libro, List<Autor> autores) {
        // TODO implement here
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String query1 = "Insert into libro (titulo, valor, ejemplares, edicion, ideditorial, isbn) values (?,?,?,?,?,?) returning idlibro";
        String query2 = "insert into autor_libro values (?,?)"; // (idlibro, idautor)
        ResultSet rs = null;
        int idLibro = 0;
        try {

            ps = conexion.prepareStatement(query1);
            ps.setString(1, libro.getTitulo());
            ps.setDouble(2, libro.getValor());
            ps.setInt(3, libro.getEjemplares());
            ps.setString(4, libro.getEdicion());
            ps.setInt(5, libro.getIdEditorial());
            ps.setString(6, libro.getIsbn());
            rs = ps.executeQuery();
            if (rs.next()) {
                idLibro = rs.getInt(1);// obtiene el id de la insercion
            }

            for (Autor autor : autores) {
                try {
                    ps = conexion.prepareStatement(query2);
                    ps.setInt(1, idLibro);
                    ps.setInt(2, autor.getIdautor());
                    ps.execute();
                } catch (SQLException e) {
                    System.err.println(e);
                }
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
    }

    /**
     * @param libro
     */
    public void actualizarLibro(Libro libro, int id) {
        // TODO implement here
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE libro SET titulo = ? , valor = ?, ejemplares = ?,edicion = ?,ideditorial = ?,isbn = ? WHERE idlibro = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setDouble(2, libro.getValor());
            ps.setInt(3, libro.getEjemplares());
            ps.setString(4, libro.getEdicion());
            ps.setInt(5, libro.getIdEditorial());
            ps.setString(6, libro.getIsbn());
            ps.setInt(7, libro.getIdLibro());
            libro.getEstante();
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

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
     */
    public void eliminarLibro(int id) {
        // TODO implement here
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "DELETE FROM libro WHERE idlibro=?;";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
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
     * @return
     */
    public List<Libro> mostrarLibros() {

        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsAll = null;
        String sql = "select idlibro,titulo,valor,ejemplares,edicion,isbn,editorial.nombre AS editorial, libro.ideditorial from libro inner join editorial on libro.ideditorial = editorial.ideditorial order by 1;";
        List<String> autores;
        List<String> estantes;
        List<String> secciones;

        try {

            ps = conexion.prepareStatement(sql);
            rsAll = ps.executeQuery();
            while (rsAll.next()) {
                autores = new ArrayList<String>();
                estantes = new ArrayList<String>();
                secciones = new ArrayList<String>();
                int id = rsAll.getInt("idlibro");
                objLibro = new Libro();
                this.objLibro.setIdLibro((rsAll.getInt("idlibro")));
                this.objLibro.setTitulo(rsAll.getString("titulo"));
                this.objLibro.setValor(rsAll.getDouble("valor"));
                this.objLibro.setEjemplares(rsAll.getInt("ejemplares"));
                this.objLibro.setEdicion(rsAll.getString("edicion"));
                this.objLibro.setIdEditorial(rsAll.getInt("ideditorial"));
                this.objLibro.setIsbn(rsAll.getString("isbn"));
                this.objLibro.setEditorial(rsAll.getString("editorial"));

                sql = "select autor.nombre AS autor from libro join  autor_libro on autor_libro.idlibro = libro.idlibro join autor on autor.idautor = autor_libro.idautor where libro.idlibro =?;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        autores.add((rs.getString("autor")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                sql = "select estante.nombre AS estante from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante where libro.idlibro =?;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        estantes.add((rs.getString("estante")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                sql = "select seccion.nombre AS seccion from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante join seccion on estante.idseccion = seccion.idseccion where libro.idlibro=? group by libro.titulo, seccion.nombre;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        secciones.add((rs.getString("seccion")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                this.objLibro.setAutor(autores);
                this.objLibro.setEstante(estantes);
                this.objLibro.setSeccion(secciones);
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
    public Libro mostrarLibroPorId(int id) {
        conexion = ConexionBD.connectDatabase();
        // TODO implement here
        objLibro = new Libro();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select idlibro,titulo,valor,ejemplares,edicion,isbn,editorial.nombre, libro.ideditorial from libro inner join editorial on libro.ideditorial = editorial.ideditorial where libro.idlibro = ?;";
        List<String> autores = new ArrayList<String>();
        List<String> estantes = new ArrayList<String>();
        List<String> secciones = new ArrayList<String>();
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
                this.objLibro.setEditorial(rs.getString("nombre"));

                sql = "select autor.nombre AS autor from libro join  autor_libro on autor_libro.idlibro = libro.idlibro join autor on autor.idautor = autor_libro.idautor where libro.idlibro =?;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        autores.add((rs.getString("autor")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                sql = "select estante.nombre AS estante from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante where libro.idlibro =?;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        estantes.add((rs.getString("estante")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                sql = "select seccion.nombre AS seccion from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante join seccion on estante.idseccion = seccion.idseccion where libro.idlibro=? group by libro.titulo, seccion.nombre;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        secciones.add((rs.getString("seccion")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                this.objLibro.setAutor(autores);
                this.objLibro.setEstante(estantes);
                this.objLibro.setSeccion(secciones);

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

    /**
     * @param id
     * @return
     */
    public Libro mostrarLibroPorTitulo(String Titulo) {
        conexion = ConexionBD.connectDatabase();
        // TODO implement here
        objLibro = new Libro();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select idlibro,titulo,valor,ejemplares,edicion,isbn,editorial.nombre, libro.ideditorial from libro inner join editorial on libro.ideditorial = editorial.ideditorial where UPPER(libro.titulo) = UPPER(?);";
        List<String> autores = new ArrayList<String>();
        List<String> estantes = new ArrayList<String>();
        List<String> secciones = new ArrayList<String>();
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, Titulo);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.objLibro.setIdLibro((rs.getInt("idlibro")));
                this.objLibro.setTitulo(rs.getString("titulo"));
                this.objLibro.setValor(rs.getDouble("valor"));
                this.objLibro.setEjemplares(rs.getInt("ejemplares"));
                this.objLibro.setEdicion(rs.getString("edicion"));
                this.objLibro.setIdEditorial(rs.getInt("ideditorial"));
                this.objLibro.setIsbn(rs.getString("isbn"));
                this.objLibro.setEditorial(rs.getString("nombre"));

                sql = "select autor.nombre AS autor from libro join  autor_libro on autor_libro.idlibro = libro.idlibro join autor on autor.idautor = autor_libro.idautor where UPPER(libro.titulo) =UPPER(?);";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, Titulo);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        autores.add((rs.getString("autor")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                sql = "select estante.nombre AS estante from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante where UPPER(libro.titulo) =UPPER(?);";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, Titulo);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        estantes.add((rs.getString("estante")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                sql = "select seccion.nombre AS seccion from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante join seccion on estante.idseccion = seccion.idseccion where UPPER(libro.titulo)=UPPER(?) group by libro.titulo, seccion.nombre;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, Titulo);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        secciones.add((rs.getString("seccion")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                this.objLibro.setAutor(autores);
                this.objLibro.setEstante(estantes);
                this.objLibro.setSeccion(secciones);

            }
        } catch (SQLException e) {
            System.err.println(e);
            objLibro = null;

        } finally {
            try {
                conexion.close();

            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        return objLibro;
    }

    public Libro mostrarLibroPorISBN(String isbn) {
        conexion = ConexionBD.connectDatabase();
        objLibro = new Libro();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select idlibro,titulo,valor,ejemplares,edicion,isbn,editorial.nombre, libro.ideditorial from libro inner join editorial on libro.ideditorial = editorial.ideditorial where UPPER(libro.isbn) = UPPER(?);";
        List<String> autores = new ArrayList<String>();
        List<String> estantes = new ArrayList<String>();
        List<String> secciones = new ArrayList<String>();
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, isbn);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.objLibro.setIdLibro((rs.getInt("idlibro")));
                this.objLibro.setTitulo(rs.getString("titulo"));
                this.objLibro.setValor(rs.getDouble("valor"));
                this.objLibro.setEjemplares(rs.getInt("ejemplares"));
                this.objLibro.setEdicion(rs.getString("edicion"));
                this.objLibro.setIdEditorial(rs.getInt("ideditorial"));
                this.objLibro.setIsbn(rs.getString("isbn"));
                this.objLibro.setEditorial(rs.getString("nombre"));

                sql = "select autor.nombre AS autor from libro join  autor_libro on autor_libro.idlibro = libro.idlibro join autor on autor.idautor = autor_libro.idautor where UPPER(libro.isbn) =UPPER(?);";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, isbn);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        autores.add((rs.getString("autor")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                sql = "select estante.nombre AS estante from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante where UPPER(libro.isbn) =UPPER(?);";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, isbn);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        estantes.add((rs.getString("estante")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                sql = "select seccion.nombre AS seccion from libro join estante_libro on estante_libro.idlibro = libro.idlibro join estante on estante.idestante = estante_libro.idestante join seccion on estante.idseccion = seccion.idseccion where UPPER(libro.isbn)=UPPER(?) group by libro.titulo, seccion.nombre;";
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, isbn);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        secciones.add((rs.getString("seccion")));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                this.objLibro.setAutor(autores);
                this.objLibro.setEstante(estantes);
                this.objLibro.setSeccion(secciones);

            }
        } catch (SQLException e) {
            System.err.println(e);
            objLibro = null;

        } finally {
            try {
                conexion.close();

            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        return objLibro;
    }

    public List<Autor> mostrarAutores() bus{
        conexion = ConexionBD.connectDatabase();
        List<Autor> autores = new ArrayList<Autor>();
        Autor autor = new Autor();
        ResultSet rs = null;
        String sql = "select * from autor";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                autor = new Autor();
                autor.setIdautor(rs.getInt("idautor"));
                autor.setNombre(rs.getString("nombre"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return autores;
    }

    public List<Editorial> mostrarEditoriales() {
        conexion = ConexionBD.connectDatabase();
        List<Editorial> editoriales = new ArrayList<Editorial>();
        Editorial editorial = new Editorial();
        ResultSet rs = null;
        String sql = "select * from editorial";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                editorial = new Editorial();
                editorial.setIdEditorial(rs.getInt("ideditorial"));
                editorial.setNombre(rs.getString("nombre"));
                editoriales.add(editorial);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return editoriales;
    }

    public Editorial buscarEditorial(String nombre) {
        conexion = ConexionBD.connectDatabase();
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        ResultSet rs = null;
        String sql = "select * from editorial where UPPER(nombre) =UPPER(?);";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                editorial.setIdEditorial(rs.getInt("ideditorial"));
                editorial.setNombre(rs.getString("nombre"));
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

        return editorial;
    }

    public int crearEditorial(String nombre) {
        int id = 0;
        conexion = ConexionBD.connectDatabase();
        ResultSet rs = null;
        String sql = " INSERT INTO editorial (nombre) VALUES (?) returning ideditorial;";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);// obtiene el id de la insercion
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
        return id;
    }

    public int crearAutor(String nombre) {
        int id = 0;
        conexion = ConexionBD.connectDatabase();
        ResultSet rs = null;
        String sql = " INSERT INTO autor (nombre) VALUES (?) returning idautor;";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);// obtiene el id de la insercion
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
        return id;
    }

    public Autor buscarAutor(String nombre) {
        conexion = ConexionBD.connectDatabase();
        Autor autor = new Autor();
        autor.setNombre(nombre);
        ResultSet rs = null;
        String sql = "select * from autor where UPPER(nombre) =UPPER(?);";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                autor.setIdautor(rs.getInt("idautor"));
                autor.setNombre(rs.getString("nombre"));
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
        return autor;
    }
}