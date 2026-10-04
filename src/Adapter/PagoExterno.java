/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

/**
 *
 * @author Carlo
 */
public class PagoExterno {
    public boolean realizarTransaccion(double cantidad) {

        System.out.println();
        System.out.println("Conectando con servicio externo de pago...");

        System.out.println(
                "Procesando pago externo por: S/ "
                + String.format("%.2f", cantidad)
        );

        System.out.println(
                "Pago externo aprobado."
        );

        return true;
    }
}
