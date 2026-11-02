package Factory;
import Modelo.Pedido;
import Modelo.PedidoDelivery;
import Modelo.PedidoTienda;
public class PedidoFactory {
    public static Pedido crearPedido(String tipo) {
        if (tipo == null) return null;
        if (tipo.equalsIgnoreCase("TIENDA")) {
            return new PedidoTienda();
        } else if (tipo.equalsIgnoreCase("DELIVERY")) {
            return new PedidoDelivery();
        }
        return null;
    }
}
