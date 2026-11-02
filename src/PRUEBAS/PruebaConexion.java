/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PRUEBAS;

import Singleton.DatabaseSingleton;
import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("     PRUEBA DE CONEXION SQL");
        System.out.println("==================================");

        try {
            Connection conexion =
                    DatabaseSingleton.getInstancia().getConexion();

            if (conexion != null && !conexion.isClosed()) {

                System.out.println("CONEXION EXITOSA");
                System.out.println("Base de datos: "+ conexion.getCatalog());

            } else {

                System.out.println("No se pudo establecer la conexion.");
            }

        } catch (SQLException | IllegalStateException e) {

            System.out.println("ERROR DE CONEXION");
            System.out.println(e.getMessage());
        }

        System.out.println("==================================");
    }
}