package Vista;
import Factory.UsuarioFactory;
import Modelo.Usuario;
import java.util.Scanner;
/**
 *
 * @author Carlo Montañez
 */
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("       MERCADITO MUNICIPAL");
        System.out.println("==========================================");

        System.out.println();
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.print("Opción: ");

        int tipo = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Ingrese su nombre: ");
        String nombre = entrada.nextLine();

        String tienda = "";

        if (tipo == 1) {

            System.out.print("Ingrese el nombre de su tienda: ");
            tienda = entrada.nextLine();
        }

        Usuario usuario =
                UsuarioFactory.crearUsuario(
                        tipo,
                        nombre,
                        tienda
                );

        System.out.println();

        System.out.println("Usuario creado correctamente.");

        usuario.mostrarMenu();

        entrada.close();
    }
}