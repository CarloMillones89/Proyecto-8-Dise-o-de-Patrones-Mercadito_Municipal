/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carlo
 */
public class Comprador extends Usuario{
    public Comprador(String nombre) {
        super(nombre);
    }

    @Override
    public void mostrarMenu() {

        System.out.println();
        System.out.println("==================================");
        System.out.println("        MENÚ DEL COMPRADOR");
        System.out.println("==================================");
        System.out.println("Comprador: " + nombre);
        System.out.println("----------------------------------");
        System.out.println("1. Ver productos");
        System.out.println("2. Comprar producto");
        System.out.println("3. Salir");
    }
}
