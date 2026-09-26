/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

/**
 *
 * @author Carlo
 */

import Modelo.Comprador;
import Modelo.Usuario;
import Modelo.Vendedor;

public class UsuarioFactory {
    public static Usuario crearUsuario(
            int tipo,
            String nombre,
            int idTienda,
            String tienda) {

        switch (tipo) {

            case 1 -> {return new Vendedor(nombre,idTienda,tienda);}
            
            case 2 -> {return new Comprador(nombre);}
            
            default -> throw new IllegalArgumentException(
                        "Tipo de usuario no válido."
                );
        }
    }
}
