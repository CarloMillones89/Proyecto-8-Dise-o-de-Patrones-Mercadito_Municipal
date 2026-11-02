/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;
import DAO.PedidoDAO;
import DAO.ProductoDAO;
import DAO.UnitOfWork;
import Modelo.Pedido;
import java.sql.SQLException;

/**
 *
 * @author Carlo
 */
public class CompraTransaccional {
    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;

    public CompraTransaccional() {
        this.pedidoDAO = new PedidoDAO();
        this.productoDAO = new ProductoDAO();
    }

    public boolean registrarCompra(Pedido pedido, int idProducto) {

        UnitOfWork unidad = new UnitOfWork();

        try {
            unidad.iniciar();

            // Registrar el pedido.
            pedidoDAO.registrar(unidad.getConexion(), pedido);

            // Descontar el inventario.
            productoDAO.procesarVenta(
                    unidad.getConexion(),
                    idProducto,
                    pedido.getCantidad()
            );

            // Confirmar ambas operaciones.
            unidad.confirmar();

            System.out.println("Compra registrada correctamente.");
            return true;

        } catch (SQLException e) {

            try {
                unidad.revertir();
            } catch (SQLException errorRollback) {
                System.out.println(
                    "Error al revertir la compra: "
                    + errorRollback.getMessage()
                );
            }

            System.out.println(
                "No se pudo registrar la compra: " + e.getMessage()
            );

            return false;
        }
    }
    
}
