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
public class PruebaDAOProducto {

    public static void main(String[] args) {

        DAOProducto dao = new DAOProducto();

        System.out.println("\n3. REGISTRAR PRODUCTO");
        System.out.println("------------------------------------------");

        String codigoBarras = "7750123450998";
        String nombre = "Galletas Oreo";
        String descripcion = "Galletas de chocolate";
        double precioVenta = 3.50;
        int idCategoria = 1;
        int idTienda = 1;

        boolean registrado = dao.registrarProducto(codigoBarras,nombre,descripcion,precioVenta,idCategoria,idTienda);

    if (registrado) {

        System.out.println("Producto: " + nombre);

        System.out.println("Tienda: " + idTienda);
    } else {

        System.out.println("No se pudo registrar el producto.");
    }
    }
}