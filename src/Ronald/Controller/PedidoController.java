package Controller;
import Dao.IPedidoDAO;
import Dao.PedidoDAO;
import Dao.PedidoDAOProxy;
import java.util.List;
import Model.Pedido;
public class PedidoController {
    private final PedidoDAO dao; 
    public PedidoController() {
        this.dao = new PedidoDAO();
    }
    public boolean registrarPedidoConFactory(String tipo, String nombre, String dni,
                                             String producto, int cantidad, double precio,
                                             String direccion, double costoEnvio) {
        Pedido p = PedidoFactory.crearPedido(tipo);
        if (p == null) {
            System.out.println("Tipo de pedido no valido: " + tipo);
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre del cliente es obligatorio.");
            return false;
        }
        if (dni == null || dni.length() != 8) {
            System.out.println("El DNI debe tener 8 digitos.");
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return false;
        }
        if (precio <= 0) {
            System.out.println("El precio unitario debe ser mayor a 0.");
            return false;
        }
        if ("DELIVERY".equalsIgnoreCase(tipo) && (direccion == null || direccion.trim().isEmpty())) {
            System.out.println("La direccion es obligatoria para Delivery.");
            return false;
        }   
        p.setNombreCliente(nombre);
        p.setDniCliente(dni);
        p.setProducto(producto);
        p.setCantidad(cantidad);
        p.setPrecioUnitario(precio);
        p.setEstado("Pendiente");
        if ("DELIVERY".equalsIgnoreCase(tipo)) {
            p.setDireccionEntrega(direccion);
            p.setCostoEnvio(costoEnvio);
        } else {
            p.setDireccionEntrega(null);
            p.setCostoEnvio(0.0);
        }
        return dao.registrar(p);
    }
    public List<Pedido> consultarTodos(String trRolUsuario) {
        //APLICAMOS EL PATRON PROXY
        IPedidoDAO trDaoProxy = new PedidoDAOProxy(trRolUsuario);
        return trDaoProxy.consultarTodos();
    }
    public List<Pedido> consultarPorDni(String dni) {
        return dao.consultarPorDni(dni);
    }
    public Pedido clonarPedido(Pedido base, String nuevoNombre,
                               String nuevoDni, String nuevoProducto,
                               int nuevaCantidad, double nuevoPrecio) {
        Pedido clon = base.clone();
        clon.setNombreCliente(nuevoNombre);
        clon.setDniCliente(nuevoDni);
        clon.setProducto(nuevoProducto);
        clon.setCantidad(nuevaCantidad);
        clon.setPrecioUnitario(nuevoPrecio);
        clon.setIdPedido(0);
        clon.setEstado("Pendiente");
        return clon;
    }
    // METODO PARA EL PATRON STATE Y OBSERVER
    public void cambiarEstadoPedido(Pedido p, String nuevoEstado) {
        if (p != null) {
            // 1. Actualiza (Patron STATE)
            if (nuevoEstado.equals("PENDIENTE")) {
                p.cambiarEstado(new Model.EstadoPendiente());
            } else {
                p.cambiarEstado(new Model.EstadoEnviado());
            }
            // 2. Guardamos en la Base de Datos
            dao.actualizarEstado(p.getIdPedido(), nuevoEstado);
        }
    }
}