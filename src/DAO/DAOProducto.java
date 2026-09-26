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
public class DAOProducto {
    private final DatabaseSingleton db;
    public DAOProducto() {
        db = DatabaseSingleton.getInstancia();
    }
    
    
    public boolean ingresarStock(int idProducto, int cantidad, double precioCompra) {

        String sql = "{CALL sp_ingresar_stock(?, ?, ?)}";

    try {
        Connection conexion = (Connection) DatabaseSingleton.getInstancia().getConexion();

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
    public boolean procesarVenta(int idProducto, int cantidadAVender) {

        String sql = "{CALL sp_procesar_venta_producto(?, ?)}";

        try {
            Connection conexion = db.getConexion();

            CallableStatement sentencia = conexion.prepareCall(sql);

            sentencia.setInt(1, idProducto);
            sentencia.setInt(2, cantidadAVender);

            sentencia.execute();

            sentencia.close();

            System.out.println("Venta procesada correctamente.");

            return true;

        } catch (SQLException e) {

            System.out.println("Error al procesar la venta: " + e.getMessage());

            return false;
        }
    }

    public List<Producto> consultarProductosDisponibles() {

        List<Producto> disponibles = new ArrayList<>();

        String sql = "{CALL sp_consultar_productos_disponibles}";

        try {
            Connection conexion = db.getConexion();

            CallableStatement sentencia = conexion.prepareCall(sql);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                Producto producto = new Producto();

                producto.setIdProducto(resultado.getInt("id_producto"));

                producto.setCodigoBarras(resultado.getString("codigo_barras"));

                producto.setNombre(resultado.getString("nombre"));

                producto.setPrecioVenta(resultado.getDouble("precio_venta"));

                producto.setStockActual(resultado.getInt("stock_actual"));
                producto.setCategoria(resultado.getString("categoria"));
                disponibles.add(producto);
            }

            resultado.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error al consultar productos: " + e.getMessage()
            );
        }

        return disponibles;
    }
}
