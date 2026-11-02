package Dao;
import java.util.List;
import Model.Pedido;
public interface IPedidoDAO {
 
    public boolean registrar(Pedido p);
    
    public List<Pedido> consultarTodos();
    
    public List<Pedido> consultarPorDni(String dni);
}