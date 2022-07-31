package entities;

import java.sql.*;

public class Conexion {

    private String host = "20.25.166.209";
    private String port = "5432";
    private String database = "biblioteca1";
    private String user = "postgres";
    private String password = "root";
    private Connection connection = null;

    public Conexion() {

    }

    public Connection getConnection() {
        String url = "";
        try {

            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
                url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
                // Database connect
                // Conectamos con la base de datos
                connection = (Connection) DriverManager.getConnection(
                        url,
                        user, password);
                boolean valid = connection.isValid(50000);
                System.out.println(valid ? "TEST OK" : "TEST FAIL");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
        }
        return connection;
    }

}
