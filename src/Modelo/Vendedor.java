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
    private final int idTienda;
    private final String tienda;

    public Vendedor(String nombre, int idTienda, String tienda) {
        super(nombre);
        this.idTienda = idTienda;
        this.tienda = tienda;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public String getTienda() {
        return tienda;
    }

    @Override
    public void mostrarMenu() {

        System.out.println();
        System.out.println("==================================");
        System.out.println("          MENU VENDEDOR");
        System.out.println("==================================");
        System.out.println("Vendedor: " + nombre);
        System.out.println("Tienda: " + tienda);
        System.out.println("ID Tienda: " + idTienda);
        System.out.println("----------------------------------");
        System.out.println("1. Registrar producto");
        System.out.println("2. Ingresar stock");
        System.out.println("3. Ver productos");
        System.out.println("4. Salir");
    }
}
