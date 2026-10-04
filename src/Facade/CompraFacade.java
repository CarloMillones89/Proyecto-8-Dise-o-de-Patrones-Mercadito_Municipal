/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

/**
 *
 * @author Carlo
 */
import DAO.DAOProducto;
public class CompraFacade {
    private DAOProducto daoProducto;

    public CompraFacade() {
        daoProducto = new DAOProducto();
    }

    public boolean realizarCompra(int idProducto, int cantidad) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("           PROCESANDO COMPRA");
        System.out.println("==========================================");

        System.out.println(
                "Producto seleccionado: " + idProducto
        );

        System.out.println(
                "Cantidad solicitada: " + cantidad
        );

        System.out.println();
        System.out.println("Verificando stock...");

        boolean venta = daoProducto.procesarVenta(
                idProducto,
                cantidad
        );

        if (!venta) {

            System.out.println(
                    "Compra rechazada."
            );

            return false;
        }

        System.out.println(
                "Stock actualizado correctamente."
        );

        System.out.println(
                "Compra realizada correctamente."
        );

        return true;
    }
    
}
