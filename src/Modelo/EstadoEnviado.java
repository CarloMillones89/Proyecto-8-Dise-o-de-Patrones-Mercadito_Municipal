/**
 *
 * @author Carlo
 */

package Modelo;

public class EstadoEnviado implements EstadoPedido {
    @Override
    public String getNombreEstado() {
        return "ENVIADO";
    }
}