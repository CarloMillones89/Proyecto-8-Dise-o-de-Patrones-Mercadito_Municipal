package Main;
import Controller.PedidoController;
import java.util.List;
import Model.Pedido;
public class MainConsola {
    public static void main(String[] args) {
        PedidoController trController = new PedidoController();
        System.out.println("=== SISTEMA DE PEDIDOS - PRUEBA DE CONSOLA ===");
        System.out.println("\n1. Consultando como ADMIN...");
        List<Pedido> trListaAdmin = trController.consultarTodos("ADMIN");
        if (trListaAdmin.isEmpty()) {
            System.out.println("No hay pedidos en la base de datos.");
        } else {
            for (Pedido p : trListaAdmin) {
                System.out.println("Pedido: " + p.getProducto() + " | Cliente: " + p.getNombreCliente());
            }
        }
        System.out.println("\n2. Consultando como CLIENTE...");
        List<Pedido> trListaCliente = trController.consultarTodos("CLIENTE");
        if (trListaCliente.isEmpty()) {
            System.out.println("La lista esta vacia (El Proxy bloqueo el acceso).");
        }
        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}
