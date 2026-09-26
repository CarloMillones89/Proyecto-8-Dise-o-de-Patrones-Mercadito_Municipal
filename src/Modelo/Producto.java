/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carlo Montañez
 */
public class Producto {
    private int idProducto;
    private String codigoBarras;
    private String nombre;
    private double precioVenta;
    private int stockActual;
    private String categoria;

    public Producto() {
    }

    public Producto(int idProducto, String codigoBarras, String nombre, double precioVenta, int stockActual, String categoria) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.categoria = categoria;
    }
    

    public int getIdProducto() {
        return idProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
}
