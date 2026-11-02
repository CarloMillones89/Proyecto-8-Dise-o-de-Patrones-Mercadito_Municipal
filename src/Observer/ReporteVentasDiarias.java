package Observer;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * PATRÓN OBSERVER - OBSERVADOR CONCRETO 1 (Reporte de Ventas Diarias)
 * ============================================================================
 * Propósito:
 * Este observador recopila todas las transacciones realizadas durante el día
 * y calcula métricas clave: recaudación total, total de unidades vendidas y
 * desglose de ventas por producto.
 * 
 * ¿Cómo funciona?
 * 1. Implementa `ReporteObserver`.
 * 2. Cada vez que `actualizar(VentaEvento evento)` es llamado, acumula el total en
 *    dinero y las unidades vendidas en sus estructuras internas en memoria.
 * 3. En `emitirReporte()`, muestra un resumen ejecutivo de las ventas diarias
 *    para la administración del mercado municipal.
 * 
 * @author Carlo Montañez
 */
public class ReporteVentasDiarias implements ReporteObserver {

    private double totalRecaudado;
    private int totalUnidadesVendidas;
    private int totalTransacciones;
    private final Map<Integer, Integer> unidadesPorProducto;

    public ReporteVentasDiarias() {
        this.totalRecaudado = 0.0;
        this.totalUnidadesVendidas = 0;
        this.totalTransacciones = 0;
        this.unidadesPorProducto = new HashMap<>();
    }

    @Override
    public void actualizar(VentaEvento evento) {
        this.totalRecaudado += evento.getTotal();
        this.totalUnidadesVendidas += evento.getCantidad();
        this.totalTransacciones++;

        // Acumular cantidad vendida por ID de producto
        int acumulado = unidadesPorProducto.getOrDefault(evento.getIdProducto(), 0);
        unidadesPorProducto.put(evento.getIdProducto(), acumulado + evento.getCantidad());

        System.out.println("  [ReporteVentasDiarias] Registrada venta: Producto ID "
                + evento.getIdProducto() + " | +" + evento.getCantidad() + " unidades | +S/ "
                + String.format("%.2f", evento.getTotal()));
    }

    @Override
    public void emitirReporte() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("         REPORTE DE VENTAS DIARIAS               ");
        System.out.println("--------------------------------------------------");
        System.out.println("Total transacciones realizadas : " + totalTransacciones);
        System.out.println("Total de unidades vendidas     : " + totalUnidadesVendidas);
        System.out.println("Monto total recaudado          : S/ " + String.format("%.2f", totalRecaudado));
        System.out.println("Desglose por producto:");
        for (Map.Entry<Integer, Integer> entry : unidadesPorProducto.entrySet()) {
            System.out.println("  • Producto ID " + entry.getKey() + ": " + entry.getValue() + " unidades vendidas");
        }
        System.out.println("--------------------------------------------------");
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public int getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }
}

