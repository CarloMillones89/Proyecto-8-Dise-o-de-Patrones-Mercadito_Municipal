/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

/**
 *
 * @author Carlo
 */
public class PagoAdapter implements PagoService{
    private PagoExterno pagoExterno;

    public PagoAdapter() {

        pagoExterno =
                new PagoExterno();
    }

    @Override
    public boolean procesarPago(double monto) {

        System.out.println();
        System.out.println("Adapter: adaptando servicio de pago...");

        return pagoExterno.realizarTransaccion(monto);
    }
}
