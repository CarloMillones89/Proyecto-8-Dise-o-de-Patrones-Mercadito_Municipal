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
public class PruebaStock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOProducto dao = new DAOProducto();

        int idProducto = 1;
        int cantidad = 5;
        double precioCompra = 2.80;

        System.out.println("==============================");
        System.out.println("PRUEBA DE INGRESO DE STOCK");
        System.out.println("==============================");

        boolean resultado = dao.ingresarStock(idProducto, cantidad, precioCompra);

        if (resultado) {
            System.out.println("Stock ingresado correctamente.");
        } else {
            System.out.println("No se pudo ingresar el stock.");
        }
    }
}    

