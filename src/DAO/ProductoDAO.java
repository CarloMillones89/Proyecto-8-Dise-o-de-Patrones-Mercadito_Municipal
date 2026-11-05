/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Producto;
import Singleton.DatabaseSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlo Montañez
 */
public class ProductoDAO {

    private final DatabaseSingleton db;

    public ProductoDAO() {
        db = DatabaseSingleton.getInstancia();
    }

    // INGRESAR STOCK
    public boolean ingresarStock(
            int idProducto,
            int cantidad,
            double precioCompra) {

        String sql = "{CALL sp_ingresar_stock(?, ?, ?)}";

        try {
            Connection conexion = db.getConexion();

            try (CallableStatement sentencia = conexion.prepareCall(sql)) {

                sentencia.setInt(1, idProducto);
                sentencia.setInt(2, cantidad);
                sentencia.setDouble(3, precioCompra);

                sentencia.execute();
            }

            System.out.println("Stock ingresado y actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al ingresar stock: " + e.getMessage());
            return false;
        }
    }

    // PROCESAR VENTA
    public boolean procesarVenta(
            int idProducto,
            int cantidadAVender) {

        String sql = "{CALL sp_procesar_venta_producto(?, ?)}";

        try {
            Connection conexion = db.getConexion();

            try (CallableStatement sentencia = conexion.prepareCall(sql)) {

                sentencia.setInt(1, idProducto);
                sentencia.setInt(2, cantidadAVender);

                sentencia.execute();
            }

            System.out.println("Venta procesada correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al procesar la venta: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR TODO EL STOCK
    public List<Producto> consultarStock() {
        return consultarProductosDisponibles();
    }

    // BUSCAR PRODUCTOS POR NOMBRE
    public List<Producto> buscarStock(String nombre) {

        List<Producto> lista = new ArrayList<>();

        // Reutilizamos el procedimiento almacenado existente
        List<Producto> productos = consultarProductosDisponibles();

        for (Producto p : productos) {

            if (p.getNombre() != null
                    && p.getNombre().toLowerCase()
                            .contains(nombre.toLowerCase())) {

                lista.add(p);
            }
        }

        return lista;
    }

    // CONSULTAR PRODUCTOS DISPONIBLES
    public List<Producto> consultarProductosDisponibles() {

        List<Producto> disponibles = new ArrayList<>();

        String sql = "{CALL sp_consultar_productos_disponibles}";

        try {
            Connection conexion = db.getConexion();

            try (CallableStatement sentencia = conexion.prepareCall(sql); ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Producto producto = new Producto();

                    producto.setIdProducto(
                            resultado.getInt("id_producto"));

                    producto.setCodigoBarras(
                            resultado.getString("codigo_barras"));

                    producto.setNombre(
                            resultado.getString("nombre"));

                    producto.setPrecioVenta(
                            resultado.getDouble("precio_venta"));

                    producto.setStockActual(
                            resultado.getInt("stock_actual"));

                    producto.setCategoria(
                            resultado.getString("categoria"));

                    disponibles.add(producto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar productos: " + e.getMessage());
        }

        return disponibles;
    }

    // REGISTRAR PRODUCTO
    public boolean registrarProducto(
            String codigoBarras,
            String nombre,
            String descripcion,
            double precioVenta,
            int idCategoria,
            int idTienda) {

        String sql = "{CALL sp_registrar_producto(?, ?, ?, ?, ?, ?)}";

        try {
            Connection conexion = db.getConexion();

            try (CallableStatement sentencia = conexion.prepareCall(sql)) {

                sentencia.setString(1, codigoBarras);
                sentencia.setString(2, nombre);
                sentencia.setString(3, descripcion);
                sentencia.setDouble(4, precioVenta);
                sentencia.setInt(5, idCategoria);
                sentencia.setInt(6, idTienda);

                sentencia.execute();
            }

            System.out.println("Producto registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    public void procesarVenta(
            Connection conexion,
            int idProducto,
            int cantidadAVender) throws SQLException {
        String sql = "{CALL sp_procesar_venta_producto(?, ?)}";

        try (CallableStatement sentencia = conexion.prepareCall(sql)) {
            sentencia.setInt(1, idProducto);
            sentencia.setInt(2, cantidadAVender);
            sentencia.execute();
        }
    }
}
