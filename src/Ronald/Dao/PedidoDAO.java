package Dao;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Singleton.ConexionBD;
import Model.Pedido;
public class PedidoDAO implements IPedidoDAO {
    
    // REGISTRAR PEDIDO
    public boolean registrar(Pedido p) {
        String sql = "{call sp_registrar_pedido(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = ConexionBD.getInstancia()
                                            .getConexion()
                                            .prepareCall(sql)) {
            cs.setString(1, p.getNombreCliente());
            cs.setString(2, p.getDniCliente());
            cs.setString(3, p.getProducto());
            cs.setInt   (4, p.getCantidad());
            cs.setDouble(5, p.getPrecioUnitario());
            cs.setString(6, p.getTipoPedido());
            if ("DELIVERY".equalsIgnoreCase(p.getTipoPedido())) {
                cs.setString(7, p.getDireccionEntrega());
                cs.setDouble(8, p.getCostoEnvio());
            } else {
                cs.setNull(7, java.sql.Types.VARCHAR);
                cs.setDouble(8, 0.0);
            }
            cs.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar pedido: " + e.getMessage());
            return false;
        }
    }
    public List<Pedido> consultarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "{call sp_consultar_pedidos()}";
        try (CallableStatement cs = ConexionBD.getInstancia()
                                            .getConexion()
                                            .prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearPedido(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar pedidos: " + e.getMessage());
        }
        return lista;
    }
    public List<Pedido> consultarPorDni(String dni) {
        List<Pedido> lista = new ArrayList<>();
        String sql = "{call sp_consultar_pedido_por_dni(?)}";
        try (CallableStatement cs = ConexionBD.getInstancia()
                                            .getConexion()
                                            .prepareCall(sql)) {
            cs.setString(1, dni);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
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
    // METODO PARA GUARDAR EL ESTADO (STATE)
    public boolean actualizarEstado(int idPedido, String nuevoEstado) {
        String sql = "{call sp_actualizar_estado(?, ?)}";
        try (CallableStatement cs = ConexionBD.getInstancia()
                                            .getConexion()
                                            .prepareCall(sql)) {
            cs.setInt(1, idPedido);
            cs.setString(2, nuevoEstado);
            cs.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
}