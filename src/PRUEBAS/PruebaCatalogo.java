/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PRUEBAS;
import Vista.PanelCatalogo;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author Carlo
 */
public class PruebaCatalogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Crear el panel del catálogo con rol CLIENTE
            PanelCatalogo panel = new PanelCatalogo("CLIENTE");

            // Crear ventana de prueba
            JFrame ventana = new JFrame(
                    "Prueba del Catálogo - Mercadito Municipal"
            );

            ventana.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            ventana.setContentPane(panel);
            ventana.setSize(850, 500);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(true);
            ventana.setVisible(true);
        });
    }
    
    
}
