package Model;
public class EstadoPendiente implements EstadoPedido {
    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}