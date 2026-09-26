package Vista;

/**
 *
 * @author Carlo Montañez
 */
import DAO.DAOProducto;
import Factory.UsuarioFactory;
import Modelo.Comprador;
import Modelo.Producto;
import Modelo.Usuario;
import Modelo.Vendedor;
import Singleton.DatabaseSingleton;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("==========================================");
        System.out.println("          MERCADITO MUNICIPAL");
        System.out.println("==========================================");
        DatabaseSingleton conexion1 =
                DatabaseSingleton.getInstancia();

        DatabaseSingleton conexion2 =
                DatabaseSingleton.getInstancia();
        if (conexion1 == conexion2) {
            System.out.println("Singleton funcionando correctamente.");

        } else {
            System.out.println("Error en el Singleton.");
        }

        Connection conexion =conexion1.getConexion();

        if (conexion == null) {
            System.out.println("No existe conexión con SQL Server.");
            entrada.close();
            return;
        }
        System.out.println(
                "Conexión a SQL Server disponible."
        );

        System.out.println();
        System.out.println("==========================================");
        System.out.println("          SELECCIÓN DE USUARIO");
        System.out.println("==========================================");

        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");

        System.out.print("Opción: ");

        int tipo = entrada.nextInt();
        entrada.nextLine();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo de usuario no válido." );

            entrada.close();
            return;
        }

        System.out.print("Ingrese su nombre: ");

        String nombre = entrada.nextLine();

        int idTienda = 0;
        String nombreTienda = "";

        if (tipo == 1) {
            System.out.println();
            System.out.println("==========================================" );

            System.out.println("              SELECCIÓN DE TIENDA");

            System.out.println("==========================================");

            System.out.println("1. Bodega Central");

            System.out.println("2. Mercado San José");

            System.out.println("3. Tienda El Ahorro");

            System.out.print("Seleccione una tienda: ");

            idTienda = entrada.nextInt();

            entrada.nextLine();

            switch (idTienda) {
                case 1:
                    nombreTienda = "Bodega Central";
                    break;

                case 2:
                    nombreTienda ="Mercado San José";
                    break;

                case 3:
                    nombreTienda ="Tienda El Ahorro";
                    break;

                default:
                    System.out.println("Tienda no válida.");
                    entrada.close();
                    return;
            }
        }

        
        Usuario usuario = UsuarioFactory.crearUsuario( tipo, nombre, idTienda,nombreTienda);

        System.out.println();
        System.out.println("Usuario creado mediante Factory.");

        DAOProducto dao = new DAOProducto();

        boolean continuar = true;

        while (continuar) {
            usuario.mostrarMenu();
            System.out.print("Seleccione una opción: " );

            int opcion = entrada.nextInt();

            entrada.nextLine();
            if (usuario instanceof Vendedor) {
                Vendedor vendedor =(Vendedor) usuario;

                switch (opcion) {

                    case 1:
                        System.out.println();
                        System.out.println("==========================================");

                        System.out.println("          REGISTRAR PRODUCTO");

                        System.out.println("==========================================");

                        System.out.println("Tienda: "+ vendedor.getTienda());

                        System.out.println();

                        System.out.print("Código de barras: ");

                        String codigo =entrada.nextLine();

                        System.out.print("Nombre del producto: ");

                        String nombreProducto =entrada.nextLine();

                        System.out.print("Descripción: ");

                        String descripcion = entrada.nextLine();

                        System.out.print("Precio de venta: S/ ");

                        double precio = entrada.nextDouble();

                        System.out.print("ID de categoría: ");

                        int categoria = entrada.nextInt();

                        entrada.nextLine();

                        boolean registrado = dao.registrarProducto(codigo,nombreProducto,descripcion,precio,categoria,vendedor.getIdTienda());

                        if (registrado) {
                            System.out.println();
                            System.out.println("Producto registrado correctamente." );

                            System.out.println("Producto: "+ nombreProducto);

                            System.out.println("Tienda: "+ vendedor.getTienda());
                        }
                        break;
                    
                    case 2:
                        System.out.println();
                        System.out.println("==========================================");

                        System.out.println("             INGRESAR STOCK");

                        System.out.println("==========================================");

                        System.out.println();

                        List<Producto> productosStock = dao.consultarProductosDisponibles();

                        System.out.printf(
                                "%-5s %-30s %-12s %-10s%n",
                                "ID",
                                "PRODUCTO",
                                "PRECIO",
                                "STOCK"
                        );

                        System.out.println( "------------------------------------------------------------" );

                        for (Producto producto :  productosStock) {
                            System.out.printf(
                                    "%-5d %-30s S/ %-9.2f %-10d%n",
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getPrecioVenta(),
                                    producto.getStockActual()
                            );
                        }

                        System.out.println( "------------------------------------------------------------" );

                        System.out.println();

                        System.out.print( "Ingrese el ID del producto: " );

                        int idProducto = entrada.nextInt();

                        System.out.print( "Cantidad a ingresar: "  );

                        int cantidad = entrada.nextInt();


                        entrada.nextLine();

                        boolean stock = dao.ingresarStock(  idProducto, cantidad, 0 );

                        if (stock) {
                            System.out.println();
                            System.out.println( "Stock ingresado correctamente."  );

                            System.out.println(  "Producto ID: " + idProducto );

                            System.out.println(  "Cantidad agregada: " + cantidad  );
                        }

                        break;

                    case 3:

                        System.out.println();
                        System.out.println( "=========================================="  );

                        System.out.println( "          PRODUCTOS DISPONIBLES" );

                        System.out.println( "==========================================" );

                        List<Producto> productos = dao.consultarProductosDisponibles();

                        System.out.printf(
                                "%-5s %-30s %-12s %-10s%n",
                                "ID",
                                "PRODUCTO",
                                "PRECIO",
                                "STOCK"
                        );

                        System.out.println( "------------------------------------------------------------" );

                        for (Producto producto : productos) {

                            System.out.printf(
                                    "%-5d %-30s S/ %-9.2f %-10d%n",
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getPrecioVenta(),
                                    producto.getStockActual()
                            );
                        }

                        System.out.println( "------------------------------------------------------------" );

                        break;

                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println();
                        System.out.println("Opción no válida.");
                }
            }

            
            else if (usuario instanceof Comprador) {
                switch (opcion) {

                    case 1:

                        System.out.println();
                        System.out.println( "==========================================" );

                        System.out.println( "        PRODUCTOS DISPONIBLES" );

                        System.out.println( "==========================================" );

                        List<Producto> productos = dao.consultarProductosDisponibles();

                        System.out.printf(
                                "%-5s %-30s %-12s %-10s%n",
                                "ID",
                                "PRODUCTO",
                                "PRECIO",
                                "STOCK"
                        );

                        System.out.println( "------------------------------------------------------------" );

                        for (Producto producto :  productos) {

                            System.out.printf(
                                    "%-5d %-30s S/ %-9.2f %-10d%n",
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getPrecioVenta(),
                                    producto.getStockActual()
                            );
                        }

                        System.out.println( "------------------------------------------------------------"  );

                        break;
                        
                    case 2:

                        System.out.println();
                        System.out.println( "==========================================" );

                        System.out.println( "            COMPRAR PRODUCTO" );

                        System.out.println( "==========================================" );

                        List<Producto> productosCompra = dao.consultarProductosDisponibles();

                        System.out.printf(
                                "%-5s %-30s %-12s %-10s%n",
                                "ID",
                                "PRODUCTO",
                                "PRECIO",
                                "STOCK"
                        );

                        System.out.println( "------------------------------------------------------------" );

                        for (Producto producto : productosCompra) {

                            System.out.printf(
                                    "%-5d %-30s S/ %-9.2f %-10d%n",
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getPrecioVenta(),
                                    producto.getStockActual()
                            );
                        }

                        System.out.println( "------------------------------------------------------------" );

                        System.out.println();

                        System.out.print( "Ingrese el ID del producto: " );

                        int idProducto = entrada.nextInt();

                        System.out.print( "Cantidad a comprar: " );

                        int cantidad = entrada.nextInt();

                        entrada.nextLine();

                        boolean venta = dao.procesarVenta(  idProducto, cantidad  );

                        if (venta) {

                            System.out.println();

                            System.out.println( "Compra realizada correctamente." );

                            System.out.println( "Producto ID: "  + idProducto  );

                            System.out.println(  "Cantidad comprada: "  + cantidad );
                        }

                        break;
 

                    case 3:

                        continuar = false;
                        break;

                    default:
                        System.out.println();

                        System.out.println( "Opción no válida."  );
                }
            }
        }

        System.out.println();

        System.out.println( "=========================================="  );

        System.out.println( "   GRACIAS POR USAR MERCADITO MUNICIPAL" );

        System.out.println( "==========================================" );

        entrada.close();
    }
}