/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hilary fernando
 */
public class PrecioNacioFactory extends FcatoryPrecio{

    @Override
    public I_precioServicio FcatoryPrecio() {
        return new PrecioNacional();
    }
        
    
}
