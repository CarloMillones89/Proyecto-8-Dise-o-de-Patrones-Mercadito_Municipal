package Model;
public class EstadoEnviado implements EstadoPedido {
    @Override
    public String getNombreEstado() {
        return "ENVIADO";
    }
}