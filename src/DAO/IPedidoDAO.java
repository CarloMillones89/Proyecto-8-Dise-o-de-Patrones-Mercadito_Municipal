package DAO;
import java.util.List;
import Modelo.Pedido;
public interface IPedidoDAO {
 
    public boolean registrar(Pedido p);
    
    public List<Pedido> consultarTodos();
    
    public List<Pedido> consultarPorDni(String dni);
}