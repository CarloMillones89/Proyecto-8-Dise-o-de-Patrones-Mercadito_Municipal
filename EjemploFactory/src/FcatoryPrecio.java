
public abstract class FcatoryPrecio {
    
    public abstract I_precioServicio FcatoryPrecio();
    
    public void obtenerPrecios(){
        I_precioServicio precio = FcatoryPrecio();
        
        precio.obtenerPrecio();
    }
        
    
}
