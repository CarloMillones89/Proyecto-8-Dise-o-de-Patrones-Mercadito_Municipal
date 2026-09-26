/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PRUEBAS;

import Singleton.DatabaseSingleton;

/**
 *
 * @author Carlo Montañez
 * 
 */
public class PruebaSingelton {

    public static void main(String[] args) {
        DatabaseSingleton conexion1 = DatabaseSingleton.getInstancia();
        DatabaseSingleton conexion2 = DatabaseSingleton.getInstancia();

        if (conexion1 == conexion2) {
            System.out.println("Las dos variables utilizan la misma instancia.");
            System.out.println("Singleton funcionando correctamente.");
        } else {
            System.out.println("Se crearon dos instancias diferentes.");
        }
    }
    
}
