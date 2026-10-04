package Vista;
/**
 *
 * @author Carlo Montañez
 */
import Facade.CompraFacade;

public class Main {
    public static void main(String[] args) {
        CompraFacade compraFacade = new CompraFacade();
        System.out.println();
        System.out.println("===== PRUEBA FACADE =====");

        boolean compra =
        compraFacade.realizarCompra(1,2,22.90);

        if (compra) {
            System.out.println("Facade: compra completada.");
        } else {
            System.out.println("Facade: compra no realizada.");
        }
    }
}