package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * PATRÓN OBSERVER - SUJETO CONCRETO (Concrete Subject)
 * ============================================================================
 * Esta clase actúa como el gestor y emisor central de eventos de ventas.
 * 
 * ¿Cómo funciona?
 * 1. Mantiene una colección interna (`List<ReporteObserver>`) de todos los
 *    observadores interesados en enterarse de las ventas.
 * 2. Cuando ocurre una venta (`registrarVenta`), crea el evento `VentaEvento`
 *    y difunde automáticamente la notificación a cada observador suscrito.
 * 3. Puede ser utilizado de forma independiente o integrado dentro de
 *    `CompraFacade` para centralizar la emisión de reportes.
 * 
 * @author Carlo Montañez
 */
public class GestorReportesVenta implements VentaObservable {

    // Lista de observadores registrados (pueden ser 0, 1 o muchos)
    private final List<ReporteObserver> observadores;

    public GestorReportesVenta() {
        this.observadores = new ArrayList<>();
    }

    @Override
    public void agregarObservador(ReporteObserver observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("[GestorReportesVenta] Observador suscrito: " + observador.getClass().getSimpleName());
        }
    }

    @Override
    public void eliminarObservador(ReporteObserver observador) {
        if (observadores.remove(observador)) {
            System.out.println("[GestorReportesVenta] Observador desuscrito: " + observador.getClass().getSimpleName());
        }
    }

    @Override
    public void notificarObservadores(VentaEvento evento) {
        System.out.println();
        System.out.println(">>> [GestorReportesVenta] Notificando evento a " + observadores.size() + " observador(es)...");
        for (ReporteObserver observador : observadores) {
            observador.actualizar(evento);
        }
    }

    /**
     * Método de negocio que simula o registra una venta efectuada
     * y dispara automáticamente la notificación hacia todos los reportes.
     * 
     * @param idProducto Identificador del producto vendido.
     * @param cantidad Unidades vendidas.
     * @param precioUnitario Precio por unidad.
     */
    public void registrarVenta(int idProducto, int cantidad, double precioUnitario) {
        VentaEvento evento = new VentaEvento(idProducto, cantidad, precioUnitario);
        notificarObservadores(evento);
    }

    /**
     * Solicita a todos los observadores registrados que emitan su reporte consolidado.
     */
    public void emitirTodosLosReportes() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("   EMISIÓN DE TODOS LOS REPORTES DEL MERCADITO   ");
        System.out.println("==================================================");
        for (ReporteObserver observador : observadores) {
            observador.emitirReporte();
        }
    }
}

