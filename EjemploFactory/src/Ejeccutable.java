
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author hilary fernando
 */
public class Ejeccutable {

    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------\n");
        System.out.println("Binevenido Mercadito Municipal");
        System.out.println("Indiquenos que tipo de precio desea obtener: ");
        System.out.println("Nacional - Importado - C/Descuento");
        String respuesta = sc.nextLine();
        System.out.println("--------------------------------\n");
        
        if(respuesta.equalsIgnoreCase("Nacional")){
            PrecioNacioFactory nacional = new PrecioNacioFactory();
            nacional.obtenerPrecios();
        }
        
        if(respuesta.equalsIgnoreCase("descuento")){
            PrecioDescFactory descuento = new PrecioDescFactory();
            descuento.obtenerPrecios();
        }
        
        if(respuesta.equalsIgnoreCase("Importado")){
            PrecioImporFactory importar = new PrecioImporFactory();
            importar.obtenerPrecios();
        }
        
    }
    
}
