package control;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    public static Connection connectDatabase() {

        String host = "20.25.166.209";
        String port = "5432";
        String database = "biblioteca1";
        String user = "postgres";
        String password = "root";

        String url = "";
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    user, password);
            boolean valid = connection.isValid(50000);
            //System.out.println(valid ? "TEST OK" : "TEST FAIL");

            return connection;

        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);

            return null;
        }
    }
}
