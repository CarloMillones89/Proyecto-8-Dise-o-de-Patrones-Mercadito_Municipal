/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Producto;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author Carlo
 */
public class ProductoDAOProxy implements IProductoDAO{
    private final ProductoDAO productoDAO;
    private final String rol;

    public ProductoDAOProxy(String rol) {
        this.productoDAO = new ProductoDAO();
        this.rol = rol;
    }

    @Override
    public List<Producto> consultarProductosDisponibles() {

        // Control de acceso al catálogo
        if (rol == null
                || (!rol.equalsIgnoreCase("CLIENTE")
                && !rol.equalsIgnoreCase("ADMIN"))) {

            System.out.println(
                    "Proxy: acceso al catálogo denegado."
            );

            return Collections.emptyList();
        }

        System.out.println(
                "Proxy: acceso autorizado al catálogo."
        );

        return productoDAO.consultarProductosDisponibles();
    }
}
