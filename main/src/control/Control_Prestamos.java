package control;

import java.util.*;
import java.sql.*;
import entities.Libro;
import entities.Penalizacion;
import entities.Prestamo;
import entities.Socio;


public class Control_Prestamos {

    //private Prestamo objPrestamo;
    private Socio objSocio;
    private Libro objLibro;
    private List<Prestamo> listPrestamos;
    public Penalizacion objPenalizacion;
    private Connection conexion;

    public Control_Prestamos() {

    }

    public String buscarSocio(String curp){
        conexion = ConexionBD.connectDatabase();
        objSocio = new Socio();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT curp from socio where curp=?";
         try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, curp);
            rs = ps.executeQuery();

            if (rs.next()) return  rs.getString("curp");
            return "";
                
            

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
   
    public int buscarPrestamosPorSocio(String curp){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) as total FROM prestamo WHERE idsocio = ? and fecha_ingreso is null";
         try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, curp);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("total");



        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }
        return 0;
    }

    public int buscarPrestamoPorFolio(int folio){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT idlibro FROM prestamo WHERE folio = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, folio);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("idlibro");
        } catch (SQLException e) {
            System.err.println("--"+e);
            return  0;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }
        return 0;
    }

    public void crearPrestamo(Prestamo prestamo) {
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "INSERT INTO prestamo (fecha_egreso,fecha_ingreso,fecha_limite,idsocio,idlibro) VALUES(?,?,?,?,?);";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setDate(1, prestamo.getFechaEgreso());
            ps.setDate(2, null);
            ps.setDate(3, prestamo.getFechaLimite());
            ps.setString(4, prestamo.getIdSocio());
            ps.setInt(5, prestamo.getIdLibro());
            ps.executeUpdate();
            actualizarEjemplares(prestamo.getIdLibro(),numeroEjemplares(prestamo.getIdLibro()) - 1);
            System.out.println("Se registro correctamente el prestamo con folio "+ultimoIndex());

        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }
    }


    public  void actuaLizarPrestamo(int folio, java.sql.Date fechaIngreso,int idlibro){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE prestamo set fecha_ingreso = ? where folio = ?;";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setDate(1,fechaIngreso);
            ps.setInt(2,folio);
            ps.executeUpdate();
            actualizarEjemplares(idlibro,numeroEjemplares(idlibro) + 1);
            System.out.println("--Libro entregado--");
            System.out.println("Se ha actualizado la fecha de entrega del pretamo");
        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }

    }

    public void actualizarEjemplares(int idlibro,int ejemplares){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE libro set ejemplares = ? where idlibro = ?;";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1,ejemplares);
            ps.setInt(2,idlibro);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }
    }
    public int ultimoIndex(){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT folio FROM prestamo order by folio desc limit 1";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("folio");
        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }
        return 0;
    }
    public int numeroEjemplares(int idlibro){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ejemplares FROM libro WHERE idlibro = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idlibro);
            rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("ejemplares");



        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }
        return 0;
    }

    /*
    public List<Prestamo> mostrarPrestamos() {
        return null;
    }


*/
    public Prestamo mostrarPrestamo(int idFolio) {
        Prestamo objPrestamo=new Prestamo();
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM prestamo WHERE folio = ?;";
         try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idFolio);
            rs = ps.executeQuery();
            if (rs.next()) {
                objPrestamo.setIdSocio(rs.getString("idsocio"));
                objPrestamo.setFechaEgreso(rs.getDate("fecha_egreso"));
                objPrestamo.setFechaLimite(rs.getDate("fecha_limite"));
                objPrestamo.setFechaIngreso(rs.getDate("fecha_ingreso"));
                objPrestamo.setIdLibro(rs.getInt("idlibro"));
                return objPrestamo;

            }



        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }
        return null;
    }

    public void crearPenalizacion(String tipo,java.sql.Date fechaCreacion,float costo,int idPrestamo){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "INSERT INTO penalizacion (estatus,tipo,fecha_creacion,costo,idPrestamo) VALUES(?,?,?,?,?);";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setObject(1, "Activa",Types.OTHER);
            ps.setObject(2, tipo, Types.OTHER);
            ps.setDate(3, fechaCreacion);
            
            ps.setFloat(4, costo);
            ps.setInt(5, idPrestamo);
            ps.executeUpdate();
            
            System.out.println("Se registro correctamente la penalizacion ");

        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }

    }

    public void actualizarPenalizacion(int folio,String tipoPerdida,float costo){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE penalizacion set costo = ?, tipo = ? where idprestamo = ?;";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setFloat(1, costo);
            ps.setObject(2,tipoPerdida , Types.OTHER);
            ps.setInt(3, folio);
            ps.executeUpdate();
            System.out.println("Se actualizo correctamente la penalizacion ");
        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }
    }

    public void actualizarPenalizacion(int folio){
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        String sql = "UPDATE penalizacion set estatus = ? where idprestamo = ?;";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setObject(1,"Cumplida" , Types.OTHER);
            ps.setInt(2, folio);
            ps.executeUpdate();
            System.out.println("Se actualizo correctamente la penalizacion ");
        } catch (SQLException e) {
            System.err.println("---errorSQL---"+e);
        }finally {
            try {
                conexion.close();
            }catch (SQLException e){
                System.out.println("error al cerrar la conexion: "+e);
            }
        }
    }


    public String mostrarPenalizacionesActivas(String curp){
        String data="";
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT penalizacion.estatus,penalizacion.tipo,penalizacion.fecha_creacion,penalizacion.costo,libro.titulo,socio.nombre,socio.correo from prestamo INNER JOIN penalizacion on (prestamo.folio=penalizacion.idprestamo) INNER JOIN libro on (prestamo.idlibro= libro.idlibro) INNER JOIN socio on (prestamo.idsocio = socio.curp) WHERE prestamo.idsocio = ? and penalizacion.estatus != 'Cumplida';";
         try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1,curp );
            rs = ps.executeQuery();

            while (rs.next()) {
                data+=data+"\n*********************************";
                data=data + "\nEstatus: "+rs.getString("estatus");
                data=data + "\nTipo: "+rs.getString("tipo");
                data=data + "\nFecha de creacion: "+rs.getDate("fecha_creacion").toString();
                data=data + "\nCosto: "+rs.getFloat("costo");
                data=data + "\nTitulo: "+rs.getString("titulo")+"\n\n";
            }
        
            return data;


        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }

        return "";
    }

    public String mostrarPenalizacionesActivas(){
        String data="";
        conexion = ConexionBD.connectDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT penalizacion.estatus,penalizacion.tipo,penalizacion.fecha_creacion,penalizacion.costo,libro.titulo,socio.nombre,socio.correo from prestamo INNER JOIN penalizacion on (prestamo.folio=penalizacion.idprestamo) INNER JOIN libro on (prestamo.idlibro= libro.idlibro) INNER JOIN socio on (prestamo.idsocio = socio.curp)  WHERE penalizacion.estatus != 'Cumplida';";
         try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            int i=0;
            while (rs.next()) {
                data=data+"\n********************************* "+i++;
                data=data + "\nEstatus: "+rs.getString("estatus");
                data=data + "\nTipo: "+rs.getString("tipo");
                data=data + "\nFecha de creacion: "+rs.getDate("fecha_creacion").toString();
                data=data + "\nCosto: "+rs.getFloat("costo");
                data=data + "\nTitulo: "+rs.getString("titulo");
                data=data + "\nNombre: "+rs.getString("nombre");
                data=data + "\nCorreo: "+rs.getString("correo")+"\n\n";
            }
        
            return data;


        } catch (SQLException e) {
            System.err.println("--"+e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("++"+e);
            }
        }

        return "";
    }

    

}