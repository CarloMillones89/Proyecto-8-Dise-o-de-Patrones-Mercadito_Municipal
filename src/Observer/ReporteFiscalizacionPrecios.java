package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * PATRÓN OBSERVER - OBSERVADOR CONCRETO 3 (Reporte de Fiscalización de Precios)
 * ============================================================================
 * Propósito:
 * Responde directamente al requerimiento municipal del proyecto:
 * "Control y fiscalización de precios justos para los ciudadanos".
 * 
 * ¿Cómo funciona?
 * 1. Implementa `ReporteObserver`.
 * 2. Cada vez que `actualizar(VentaEvento evento)` es notificado, valida si el
 *    precio unitario supera un límite de referencia municipal establecido.
 * 3. Si se detecta un sobreprecio, se registra una observación para el equipo
 *    de fiscalización de la municipalidad.
 * 4. En `emitirReporte()`, entrega el informe de auditoría de precios.
 * 
 * @author Carlo Montañez
 */
public class ReporteFiscalizacionPrecios implements ReporteObserver {

    // Precio máximo de referencia general permitido (simulación)
    private final double precioMaximoReferencia;
    private final List<String> observaciones;

    public ReporteFiscalizacionPrecios() {
        this(50.0); // Por defecto alerta si el precio unitario supera S/ 50.00
    }

    public ReporteFiscalizacionPrecios(double precioMaximoReferencia) {
        this.precioMaximoReferencia = precioMaximoReferencia;
        this.observaciones = new ArrayList<>();
    }

    @Override
    public void actualizar(VentaEvento evento) {
        if (evento.getPrecioUnitario() > precioMaximoReferencia) {
            String observacion = "SOBREPRECIO DETECTADO: Producto ID " + evento.getIdProducto()
                    + " vendido a S/ " + String.format("%.2f", evento.getPrecioUnitario())
                    + " (Límite municipal: S/ " + String.format("%.2f", precioMaximoReferencia) + ") en fecha "
                    + evento.getFechaHora();
            observaciones.add(observacion);
            System.out.println("  [Fiscalización] 🚨 " + observacion);
        } else {
            System.out.println("  [Fiscalización] Precio conforme para Producto ID "
                    + evento.getIdProducto() + " (S/ " + String.format("%.2f", evento.getPrecioUnitario()) + ")");
        }
    }

    @Override
    public void emitirReporte() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("   REPORTE DE FISCALIZACIÓN Y CONTROL DE PRECIOS  ");
        System.out.println("--------------------------------------------------");
        System.out.println("Límite de precio municipal de referencia: S/ " + String.format("%.2f", precioMaximoReferencia));
        if (observaciones.isEmpty()) {
            System.out.println("No se detectaron infracciones ni sobreprecios en el período evaluado.");
        } else {
            System.out.println("Infracciones / Observaciones detectadas:");
            for (String obs : observaciones) {
                System.out.println("  • " + obs);
            }
        }
        System.out.println("--------------------------------------------------");
    }
}

