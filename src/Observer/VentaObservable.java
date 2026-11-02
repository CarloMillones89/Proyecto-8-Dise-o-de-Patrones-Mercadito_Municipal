package Observer;

/**
 * ============================================================================
 * PATRÓN OBSERVER - INTERFAZ SUJETO / OBSERVABLE (Subject Interface)
 * ============================================================================
 * Define las operaciones que debe tener la entidad observada (el emisor de eventos).
 * 
 * ¿Cómo funciona?
 * 1. Mantiene el registro de los observadores interesados (`suscribir` y `desuscribir`).
 * 2. Proporciona el mecanismo para difundir (`notificarObservadores`) los cambios
 *    o eventos a todos los observadores registrados sin acoplarse a ellos.
 * 
 * Principio SOLID aplicado:
 * - Principio Abierto/Cerrado (OCP): Se pueden agregar nuevos tipos de reportes
 *   sin modificar esta interfaz ni la lógica del emisor.
 * 
 * @author Carlo Montañez
 */
public interface VentaObservable {

    /**
     * Registra un nuevo observador en la lista de suscritos.
     * 
     * @param observador Instancia del reporte que desea recibir notificaciones.
     */
    void agregarObservador(ReporteObserver observador);

    /**
     * Elimina un observador de la lista de suscritos para que no reciba más notificaciones.
     * 
     * @param observador Instancia del reporte a desuscribir.
     */
    void eliminarObservador(ReporteObserver observador);

    /**
     * Recorre la lista de observadores suscritos y llama al método `actualizar`
     * de cada uno, enviándoles el evento ocurrido.
     * 
     * @param evento Información de la venta.
     */
    void notificarObservadores(VentaEvento evento);
}

