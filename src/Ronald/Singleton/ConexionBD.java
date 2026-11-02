package Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionBD {
    private static ConexionBD instancia;
    private Connection conexion;
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
          + "databaseName=MercadoMunicipalDB;"
          + "encrypt=true;trustServerCertificate=true;"
          + "loginTimeout=5;";
    private static final String USUARIO = "sa";
    private static final String CLAVE   = "1013182122";  
    private ConexionBD() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión a SQL Server establecida (unica instancia).");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontro el driver JDBC de SQL Server", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con SQL Server: " + e.getMessage(), e);
        }
    }
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }
    public Connection getConexion() {
        return conexion;
    }
    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
}