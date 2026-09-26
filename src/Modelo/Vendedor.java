/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carlo
 */
public class Vendedor extends Usuario{
    private String tienda;

    public Vendedor(String nombre, String tienda) {
        super(nombre);
        this.tienda = tienda;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    @Override
    public void mostrarMenu() {

        System.out.println();
        System.out.println("==================================");
        System.out.println("        MENÚ DEL VENDEDOR");
        System.out.println("==================================");
        System.out.println("Vendedor: " + nombre);
        System.out.println("Tienda: " + tienda);
        System.out.println("----------------------------------");
        System.out.println("1. Ingresar producto");
        System.out.println("2. Consultar productos");
        System.out.println("3. Salir");
    }
}
