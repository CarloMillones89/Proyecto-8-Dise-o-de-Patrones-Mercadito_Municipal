package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 * PATRÓN OBSERVER - OBSERVADOR CONCRETO 2 (Reporte de Stock Crítico / Alertas)
 * ============================================================================
 * Propósito:
 * Monitorea el flujo de ventas para detectar productos que tienen alta rotación
 * o que demandan reposición inmediata de inventario por parte de los comerciantes.
 * 
 * ¿Cómo funciona?
 * 1. Implementa `ReporteObserver`.
 * 2. Cada vez que recibe `actualizar(VentaEvento evento)`, registra la salida de inventario
 *    y evalúa si las salidas acumuladas superan un umbral de reabastecimiento municipal.
 * 3. En `emitirReporte()`, lista los productos que requieren reposición urgente
 *    para evitar desabastecimiento en los puestos del mercado municipal.
 * 
 * @author Carlo Montañez
 */
public class ReporteStockCritico implements ReporteObserver {

    // Umbral de unidades vendidas para generar aviso de reposición
    private final int umbralAlertaVenta;
    private final Map<Integer, Integer> salidasAcumuladas;
    private final List<String> alertasGeneradas;

    public ReporteStockCritico() {
        this(5); // Por defecto, si un producto vende 5 o más unidades genera alerta
    }

    public ReporteStockCritico(int umbralAlertaVenta) {
        this.umbralAlertaVenta = umbralAlertaVenta;
        this.salidasAcumuladas = new HashMap<>();
        this.alertasGeneradas = new ArrayList<>();
    }

    @Override
    public void actualizar(VentaEvento evento) {
        int salidas = salidasAcumuladas.getOrDefault(evento.getIdProducto(), 0) + evento.getCantidad();
        salidasAcumuladas.put(evento.getIdProducto(), salidas);

        // Si las salidas acumuladas alcanzan o superan el umbral, generamos alerta
        if (salidas >= umbralAlertaVenta) {
            String alerta = "ALERTA: El producto ID " + evento.getIdProducto()
                    + " ha registrado salidas por " + salidas + " unidades. (Requiere reposición inmediata).";
            alertasGeneradas.add(alerta);
            System.out.println("  [ReporteStockCritico] ⚠️ " + alerta);
        } else {
            System.out.println("  [ReporteStockCritico] Producto ID " + evento.getIdProducto()
                    + " - Salidas acumuladas: " + salidas + "/" + umbralAlertaVenta + " para alerta.");
        }
    }

    @Override
    public void emitirReporte() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("        REPORTE DE ALERTA DE STOCK CRÍTICO        ");
        System.out.println("--------------------------------------------------");
        if (alertasGeneradas.isEmpty()) {
            System.out.println("Todos los productos se encuentran en niveles seguros de stock.");
        } else {
            System.out.println("Productos que alcanzaron umbral de reposición:");
            for (String alerta : alertasGeneradas) {
                System.out.println("  • " + alerta);
            }
        }
        System.out.println("--------------------------------------------------");
    }
}

