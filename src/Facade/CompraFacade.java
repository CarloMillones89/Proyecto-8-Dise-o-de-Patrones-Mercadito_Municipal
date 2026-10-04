/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

/**
 *
 * @author Carlo
 */
import Adapter.PagoAdapter;
import Adapter.PagoService;
import DAO.DAOProducto;


public class CompraFacade {
    private DAOProducto daoProducto;
    private PagoService pagoService;

    public CompraFacade() {
        daoProducto = new DAOProducto();
        pagoService = new PagoAdapter();
    }

    public boolean realizarCompra(int idProducto,int cantidad,double precio) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("           PROCESANDO COMPRA");
        System.out.println("==========================================");

        System.out.println("Producto seleccionado: "+ idProducto);

        System.out.println("Cantidad solicitada: "+ cantidad);
        double total = precio * cantidad;

        System.out.println("Precio unitario: S/ "+ String.format("%.2f", precio));

        System.out.println(
                "Total de compra: S/ " + String.format("%.2f", total));

        System.out.println();
        System.out.println("Verificando pago...");

        boolean pago = pagoService.procesarPago(total);

        if (!pago) {

            System.out.println("Pago rechazado.");
            return false;
        }

        System.out.println();
        System.out.println("Actualizando stock...");

        boolean venta = daoProducto.procesarVenta(idProducto,cantidad);

        if (!venta) {
            System.out.println("No se pudo actualizar el stock." );
            return false;
        }
        System.out.println();
        System.out.println("==========================================");
        System.out.println("          COMPRA COMPLETADA");
        System.out.println("==========================================");

        System.out.println("Total pagado: S/ " + String.format("%.2f", total));
        System.out.println("Stock actualizado correctamente." );

        return true;
    }
    
}
