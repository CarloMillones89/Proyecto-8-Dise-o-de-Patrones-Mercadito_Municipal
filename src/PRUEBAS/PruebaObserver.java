package PRUEBAS;

import Observer.GestorReportesVenta;
import Observer.ReporteFiscalizacionPrecios;
import Observer.ReporteStockCritico;
import Observer.ReporteVentasDiarias;

/**
 * ============================================================================
 * CLASE DE PRUEBA - DEMOSTRACIÓN DEL PATRÓN OBSERVER
 * ============================================================================
 * Esta clase prueba la interacción entre el Sujeto (GestorReportesVenta)
 * y los diferentes Observadores (ReporteVentasDiarias, ReporteStockCritico,
 * ReporteFiscalizacionPrecios).
 * 
 * Flujo:
 * 1. Se crea el Sujeto Observable.
 * 2. Se crean y registran los diferentes tipos de reportes (Observadores).
 * 3. Se simulan ventas en el mercado municipal.
 * 4. Los observadores reaccionan automáticamente a cada venta en tiempo real.
 * 5. Se desuscribe un observador para demostrar la dinamismo del patrón.
 * 6. Se emite el consolidado de cada reporte.
 * 
 * @author Carlo Montañez
 */
public class PruebaObserver {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DEMOSTRACIÓN DEL PATRÓN OBSERVER            ");
        System.out.println("  Sección: Emisión de Reportes - Mercadito        ");
        System.out.println("==================================================");

        // 1. Instanciamos el Sujeto Observable
        GestorReportesVenta gestor = new GestorReportesVenta();

        // 2. Instanciamos los Observadores concretos
        ReporteVentasDiarias reporteVentas = new ReporteVentasDiarias();
        ReporteStockCritico reporteStock = new ReporteStockCritico(4); // Alerta con 4 unidades
        ReporteFiscalizacionPrecios reportePrecios = new ReporteFiscalizacionPrecios(30.0); // Alerta si > S/ 30.00

        // 3. Suscripción de observadores al gestor de reportes
        System.out.println("\n--- [PASO 1] Suscribiendo Observadores al Sujeto ---");
        gestor.agregarObservador(reporteVentas);
        gestor.agregarObservador(reporteStock);
        gestor.agregarObservador(reportePrecios);

        // 4. Simulación de ventas en el mercado
        System.out.println("\n--- [PASO 2] Procesando Ventas en el Mercado Municipal ---");

        System.out.println("\n>> Transacción 1: Venta regular de frutas (Producto 1)");
        gestor.registrarVenta(1, 2, 12.50);

        System.out.println("\n>> Transacción 2: Venta de producto con precio alto (Producto 2)");
        gestor.registrarVenta(2, 1, 45.00); // Supera el umbral de S/ 30.00

        System.out.println("\n>> Transacción 3: Venta alta cantidad del Producto 1 (Alerta de Stock)");
        gestor.registrarVenta(1, 3, 12.50); // Acumula 5 unidades, supera umbral de 4

        // 5. Demostración de desuscripción dinámica
        System.out.println("\n--- [PASO 3] Desuscribiendo Reporte de Fiscalización ---");
        gestor.eliminarObservador(reportePrecios);

        System.out.println("\n>> Transacción 4: Otra venta con precio alto (Fiscalización ya no debe recibirla)");
        gestor.registrarVenta(3, 2, 60.00);

        // 6. Emisión final de reportes
        System.out.println("\n--- [PASO 4] Emisión de Reportes Consolidados ---");
        gestor.emitirTodosLosReportes();
    }
}

