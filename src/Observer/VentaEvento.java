package Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ============================================================================
 * PATRÓN OBSERVER - OBJETO DE EVENTO (DTO / Event Data)
 * ============================================================================
 * Esta clase encapsula la información del evento ocurrido (una venta efectuada).
 * 
 * En el patrón Observer, el Sujeto genera este objeto y se lo envía a todos
 * los observadores suscritos mediante el método actualizar(evento).
 * 
 * Ventaja:
 * - Los observadores reciben toda la información necesaria sin tener que consultar
 *   directamente al Sujeto, logrando un desacoplamiento de tipo "Push" (empuje).
 * 
 * @author Carlo Montañez
 */
public class VentaEvento {

    private final int idProducto;
    private final int cantidad;
    private final double precioUnitario;
    private final double total;
    private final String fechaHora;

    public VentaEvento(int idProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = cantidad * precioUnitario;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "VentaEvento [Producto ID=" + idProducto + ", Cantidad=" + cantidad
                + ", Precio Unitario=S/ " + String.format("%.2f", precioUnitario)
                + ", Total=S/ " + String.format("%.2f", total)
                + ", Fecha=" + fechaHora + "]";
    }
}

