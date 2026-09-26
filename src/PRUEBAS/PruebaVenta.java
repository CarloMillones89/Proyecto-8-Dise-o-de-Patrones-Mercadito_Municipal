package PRUEBAS;


import DAO.DAOProducto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Carlo Montañez
 */
public class PruebaVenta {
    public static void main(String[] args) {
        DAOProducto dao = new DAOProducto();

        int idProducto = 1;
        int cantidadAVender = 2;

        System.out.println("==============================");
        System.out.println("PRUEBA DE PROCESAR VENTA");
        System.out.println("==============================");

        boolean resultado =
                dao.procesarVenta(idProducto, cantidadAVender);

        if (resultado) {
            System.out.println("Venta procesada correctamente.");
        } else {
            System.out.println("No se pudo procesar la venta.");
        }
    }
    
}
