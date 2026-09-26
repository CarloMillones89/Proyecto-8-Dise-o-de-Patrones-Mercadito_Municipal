package PRUEBAS;


import DAO.DAOProducto;
import Modelo.Producto;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Carlo Montañez
 */
public class PruebaDAOProducto {

    public static void main(String[] args) {

        DAOProducto dao = new DAOProducto();

        List<Producto> productos =
                dao.consultarProductosDisponibles();

        System.out.println("==============================");
        System.out.println("PRODUCTOS DISPONIBLES");
        System.out.println("==============================");

        for (Producto producto : productos) {

            System.out.println("ID: " + producto.getIdProducto());

            System.out.println("Código: " + producto.getCodigoBarras());

            System.out.println("Nombre: " + producto.getNombre());

            System.out.println("Precio: S/ " + producto.getPrecioVenta());

            System.out.println("Stock: " + producto.getStockActual());

            System.out.println("Categoría: " + producto.getCategoria());

            System.out.println("------------------------------");
        }
    }
}