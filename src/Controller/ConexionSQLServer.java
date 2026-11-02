/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Carlo
 */
public class ConexionSQLServer {
    private static final String URL =
            "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName=Mercadito_Municipal;"
            + "integratedSecurity=true;"
            + "encrypt=false;";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
