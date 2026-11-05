/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.util.List;
/**
 *
 * @author Pauloo
 */
public class StockController {

    private final ProductoDAO productoDAO;

    public StockController() {
        productoDAO = new ProductoDAO();
    }

    // Consultar todo el stock
    public List<Producto> consultarStock() {
        return productoDAO.consultarStock();
    }

    // Buscar stock por nombre
    public List<Producto> buscarStock(String nombre) {
        return productoDAO.buscarStock(nombre);
    }
}