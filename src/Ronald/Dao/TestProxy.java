package Dao;
import Dao.IPedidoDAO;
import Dao.PedidoDAOProxy;
import java.util.List;
import Model.Pedido;
public class TestProxy {
    public static void main(String[] args) {
        System.out.println("--- PRUEBA 1: USUARIO NORMAL ---");
        IPedidoDAO trDaoCliente = new PedidoDAOProxy("CLIENTE");
        
        List<Pedido> trLista1 = trDaoCliente.consultarTodos();
        System.out.println("Pedidos obtenidos: " + trLista1.size());
        
        System.out.println("\n--- PRUEBA 2: USUARIO ADMIN ---");
        IPedidoDAO trDaoAdmin = new PedidoDAOProxy("ADMIN");
        
        List<Pedido> trLista2 = trDaoAdmin.consultarTodos();
        System.out.println("Pedidos obtenidos: " + trLista2.size());
    }
}