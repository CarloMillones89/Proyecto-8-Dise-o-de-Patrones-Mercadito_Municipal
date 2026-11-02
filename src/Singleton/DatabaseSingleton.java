/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;

import Controller.ConexionSQLServer;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Carlo Montañez
 */
public class DatabaseSingleton {
    private static DatabaseSingleton instancia;

    private Connection conexion;

    private DatabaseSingleton() {
        try {
            conexion = ConexionSQLServer.conectar();

            System.out.println(
                    "Conexión única a SQL Server establecida."
            );

        } catch (SQLException e) {
            
            throw new IllegalStateException(
                    "No se pudo conectar con SQL Server: "
                    + e.getMessage(), e
            );
        }
    }

    public static synchronized DatabaseSingleton getInstancia() {

        if (instancia == null) {
            instancia = new DatabaseSingleton();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}
