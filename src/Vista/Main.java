package Vista;


import DAO.DAOProducto;
import Modelo.Producto;
import Singleton.DatabaseSingleton;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Carlo Montañez
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE MERCADO");
        System.out.println("==========================================");


        System.out.println("\n1. PRUEBA DEL PATRÓN SINGLETON");
        System.out.println("------------------------------------------");

        DatabaseSingleton conexion1 = DatabaseSingleton.getInstancia();

        DatabaseSingleton conexion2 =
                DatabaseSingleton.getInstancia();

        if (conexion1 == conexion2) {
            System.out.println("Singleton funcionando correctamente.");
            System.out.println("Las dos variables utilizan la misma instancia.");
        } else {
            System.out.println("Error: se crearon instancias diferentes.");
        }

        Connection conexion = conexion1.getConexion();

        if (conexion != null) {
            System.out.println("Conexión a SQL Server disponible.");
        } else {
            System.out.println("No hay conexión con SQL Server.");
            return;
        }

        DAOProducto dao = new DAOProducto();

        System.out.println("\n2. PRODUCTOS DISPONIBLES");
        System.out.println("------------------------------------------");

        List<Producto> productos = dao.consultarProductosDisponibles();

        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getIdProducto());
            System.out.println("Código de barras: "+ producto.getCodigoBarras());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: S/ "+ producto.getPrecioVenta());
            System.out.println("Stock: " + producto.getStockActual());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("------------------------------------------");
        }

        System.out.println("\n3. INGRESO DE STOCK");
        System.out.println("------------------------------------------");

        int idProducto = 1;
        int cantidad = 5;
        double precioCompra = 2.80;

        String nombreProducto = "";

        for (Producto producto : productos) {

            if (producto.getIdProducto() == idProducto) {
                nombreProducto = producto.getNombre();
                break;
            }
        }

        boolean ingreso = dao.ingresarStock(idProducto,cantidad,precioCompra);

        if (ingreso) {
            System.out.println("Se ingresaron " + cantidad + " unidades de "+ nombreProducto );

        } else {
            System.out.println("No se pudo ingresar el stock.");
        }

        System.out.println("\n4. PROCESAR VENTA");
        System.out.println("------------------------------------------");

        int cantidadAVender = 38;

        boolean venta =
                dao.procesarVenta(idProducto,cantidadAVender);

        if (venta) {
            System.out.println("Se vendieron " + cantidadAVender + " unidades de " + nombreProducto);

        } else {
            System.out.println("No se pudo procesar la venta.");
        }

        System.out.println("\n5. STOCK ACTUALIZADO");
        System.out.println("------------------------------------------");
        List<Producto> productosActualizados =dao.consultarProductosDisponibles();
        for (Producto producto : productosActualizados) {
            if (producto.getIdProducto() == idProducto) {
                System.out.println("Producto: "+ producto.getNombre());
                System.out.println("Stock actual: "+ producto.getStockActual());
            }
        }
    }
    
}
