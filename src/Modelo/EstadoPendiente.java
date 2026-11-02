/**
 *
 * @author Carlo
 */
package Modelo;

public class EstadoPendiente implements EstadoPedido {
    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}