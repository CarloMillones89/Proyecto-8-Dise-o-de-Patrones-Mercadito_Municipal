package Observer;

/**
 * ============================================================================
 * PATRÓN OBSERVER - INTERFAZ OBSERVADOR (Observer Interface)
 * ============================================================================
 * Define el contrato que deben cumplir todos los tipos de reportes del sistema.
 * 
 * ¿Cómo funciona?
 * 1. Cualquier clase que quiera ser notificada cuando ocurra una venta debe
 *    implementar esta interfaz.
 * 2. El método `actualizar(VentaEvento evento)` es invocado automáticamente por
 *    el Sujeto (Observable) para entregar los datos de la transacción en tiempo real.
 * 3. El método `emitirReporte()` permite generar o imprimir el consolidado del
 *    reporte cuando el usuario o la administración municipal lo soliciten.
 * 
 * Principio SOLID aplicado:
 * - Inversión de Dependencias (DIP): El Sujeto depende de esta abstracción
 *   y no de clases concretas de reporte.
 * 
 * @author Carlo Montañez
 */
public interface ReporteObserver {

    /**
     * Recibe la notificación de una nueva venta ocurrida en el mercado.
     * Cada observador procesa esta información de acuerdo a su propósito.
     * 
     * @param evento Información detallada de la venta realizada.
     */
    void actualizar(VentaEvento evento);

    /**
     * Emite, imprime o exporta el reporte procesado hasta el momento.
     */
    void emitirReporte();
}

