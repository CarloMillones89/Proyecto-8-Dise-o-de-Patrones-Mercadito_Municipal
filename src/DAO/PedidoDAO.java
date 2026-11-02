package DAO;

import Modelo.Pedido;
import Singleton.DatabaseSingleton;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements IPedidoDAO {

    @Override
    public boolean registrar(Pedido p) {
        String sql = "{call sp_registrar_pedido(?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            CallableStatement cs = DatabaseSingleton.getInstancia()
                    .getConexion().prepareCall(sql);

            try (cs) {
                cs.setString(1, p.getNombreCliente());
                cs.setString(2, p.getDniCliente());
                cs.setString(3, p.getProducto());
                cs.setInt(4, p.getCantidad());
                cs.setDouble(5, p.getPrecioUnitario());
                cs.setString(6, p.getTipoPedido());

                if ("DELIVERY".equalsIgnoreCase(p.getTipoPedido())) {
                    cs.setString(7, p.getDireccionEntrega());
                    cs.setDouble(8, p.getCostoEnvio());
                } else {
                    cs.setNull(7, Types.VARCHAR);
                    cs.setDouble(8, 0.0);
                }

                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        p.setIdPedido(rs.getInt("id_pedido"));
                    }
                }
            }

            return p.getIdPedido() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar pedido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Pedido> consultarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "{call sp_consultar_pedidos()}";

        try {
            CallableStatement cs = DatabaseSingleton.getInstancia()
                    .getConexion().prepareCall(sql);

            try (cs; ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar pedidos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Pedido> consultarPorDni(String dni) {
        List<Pedido> lista = new ArrayList<>();
        String sql = "{call sp_consultar_pedido_por_dni(?)}";

        try {
            CallableStatement cs = DatabaseSingleton.getInstancia()
                    .getConexion().prepareCall(sql);

            try (cs) {
                cs.setString(1, dni);

                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        lista.add(mapearPedido(rs));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar por DNI: " + e.getMessage());
        }

        return lista;
    }

    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();

        p.setIdPedido(rs.getInt("id_pedido"));
        p.setNombreCliente(rs.getString("nombre_cliente"));
        p.setDniCliente(rs.getString("dni_cliente"));
        p.setProducto(rs.getString("producto"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setPrecioUnitario(rs.getDouble("precio_unitario"));
        p.setTotal(rs.getDouble("total"));
        p.setFechaPedido(rs.getString("fecha_pedido"));
        p.setEstado(rs.getString("estado"));
        p.setTipoPedido(rs.getString("tipo_pedido"));
        p.setDireccionEntrega(rs.getString("direccion_entrega"));
        p.setCostoEnvio(rs.getDouble("costo_envio"));

        return p;
    }

    public boolean actualizarEstado(int idPedido, String nuevoEstado) {
        String sql = "{call sp_actualizar_estado(?, ?)}";

        try {
            CallableStatement cs = DatabaseSingleton.getInstancia()
                    .getConexion().prepareCall(sql);

            try (cs) {
                cs.setInt(1, idPedido);
                cs.setString(2, nuevoEstado);
                cs.execute();
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
    public void registrar(
        Connection conexion,
        Pedido p) throws SQLException {

            String sql = "{CALL sp_registrar_pedido(?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement sentencia = conexion.prepareCall(sql)) {

                sentencia.setString(1, p.getNombreCliente());
                sentencia.setString(2, p.getDniCliente());
                sentencia.setString(3, p.getProducto());
                sentencia.setInt(4, p.getCantidad());
                sentencia.setDouble(5, p.getPrecioUnitario());
                sentencia.setString(6, p.getTipoPedido());

            if ("DELIVERY".equalsIgnoreCase(p.getTipoPedido())) {
                sentencia.setString(7, p.getDireccionEntrega());
                sentencia.setDouble(8, p.getCostoEnvio());
            } else {
                sentencia.setNull(7, java.sql.Types.VARCHAR);
                sentencia.setDouble(8, 0);
            }

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    p.setIdPedido(resultado.getInt("id_pedido"));
                } else {
                    throw new SQLException("No se recibió el ID del pedido.");
                }
            }
        }
    }
}