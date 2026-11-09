/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Producto;
import java.util.List;
/**
 *
 * @author Carlo
 */
public interface  IProductoDAO {
    List<Producto> consultarProductosDisponibles();
}
