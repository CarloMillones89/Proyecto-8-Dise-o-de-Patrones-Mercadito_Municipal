package DAO;
import Modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
public class PedidoDAOProxy implements IPedidoDAO {
    private PedidoDAO trDaoReal;
    private String trRolUsuario;
    public PedidoDAOProxy(String trRolUsuario) {
        this.trDaoReal = new PedidoDAO();
        this.trRolUsuario = trRolUsuario;
    }
    @Override
    public boolean registrar(Pedido p) {
        return trDaoReal.registrar(p);
    }
    @Override
    public List<Pedido> consultarTodos() {
        if (trRolUsuario != null && trRolUsuario.equalsIgnoreCase("ADMIN")) {

        System.out.println("Proxy: Acceso concedido. Mostrando todos los pedidos." );
        return trDaoReal.consultarTodos();
        } else {
            System.out.println("Proxy: ACCESO DENEGADO. Solo el administrador puede ver el historial completo.");

        return new ArrayList<>();
    }
}
    @Override
    public List<Pedido> consultarPorDni(String dni) {
        return trDaoReal.consultarPorDni(dni);
    }
}