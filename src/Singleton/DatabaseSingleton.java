/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;

import Controller.ConexionSQLServer;
import java.sql.Connection;

/**
 *
 * @author Carlo Montañez
 */
public class DatabaseSingleton {
     private static DatabaseSingleton instancia;

    private Connection conexion;

    private DatabaseSingleton() {
        conexion = (Connection) ConexionSQLServer.conectar();
    }

    public static DatabaseSingleton getInstancia() {

        if (instancia == null) {
            instancia = new DatabaseSingleton();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}
