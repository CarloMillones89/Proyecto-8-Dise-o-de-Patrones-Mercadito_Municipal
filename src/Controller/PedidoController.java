package Controller;

import Factory.PedidoFactory;
import DAO.IPedidoDAO;
import DAO.PedidoDAO;
import DAO.PedidoDAOProxy;
import Modelo.Pedido;
import Modelo.EstadoPendiente;
import Modelo.EstadoEnviado;
import java.util.List;

public class PedidoController {

    private final PedidoDAO dao;

    public PedidoController() {
        this.dao = new PedidoDAO();
    }

    public boolean registrarPedidoConFactory(
            String tipo,
            String nombre,
            String dni,
            String producto,
            int cantidad,
            double precio,
            String direccion,
            double costoEnvio) {

        if (tipo == null || nombre == null || dni == null || producto == null) {
            System.out.println("Faltan datos obligatorios.");
            return false;
        }

        String tipoNormalizado = tipo.trim().toUpperCase();
        nombre = nombre.trim();
        dni = dni.trim();
        producto = producto.trim();

        Pedido p = PedidoFactory.crearPedido(tipoNormalizado);

        if (p == null) {
            System.out.println("Tipo de pedido no válido.");
            return false;
        }

        if (nombre.isEmpty() || producto.isEmpty()) {
            System.out.println("El nombre y el producto son obligatorios.");
            return false;
        }

        if (!dni.matches("\\d{8}")) {
            System.out.println("El DNI debe contener exactamente 8 dígitos.");
            return false;
        }

        if (cantidad <= 0 || precio <= 0) {
            System.out.println("La cantidad y el precio deben ser mayores a cero.");
            return false;
        }

        if (costoEnvio < 0) {
            System.out.println("El costo de envío no puede ser negativo.");
            return false;
        }

        if ("DELIVERY".equals(tipoNormalizado)
                && (direccion == null || direccion.trim().isEmpty())) {
            System.out.println("La dirección es obligatoria para Delivery.");
            return false;
        }

        p.setNombreCliente(nombre);
        p.setDniCliente(dni);
        p.setProducto(producto);
        p.setCantidad(cantidad);
        p.setPrecioUnitario(precio);
        p.setTipoPedido(tipoNormalizado);
        p.setEstado("Pendiente");

        if ("DELIVERY".equals(tipoNormalizado)) {
            p.setDireccionEntrega(direccion.trim());
            p.setCostoEnvio(costoEnvio);
        } else {
            p.setDireccionEntrega(null);
            p.setCostoEnvio(0.0);
        }

        return dao.registrar(p);
    }

    public List<Pedido> consultarTodos(String rolUsuario) {
        IPedidoDAO daoProxy = new PedidoDAOProxy(rolUsuario);
        return daoProxy.consultarTodos();
    }

    public List<Pedido> consultarPorDni(String dni) {
        if (dni == null || !dni.trim().matches("\\d{8}")) {
            System.out.println("El DNI debe contener exactamente 8 dígitos.");
            return java.util.Collections.emptyList();
        }

        return dao.consultarPorDni(dni.trim());
    }

    public Pedido clonarPedido(
            Pedido base,
            String nuevoNombre,
            String nuevoDni,
            String nuevoProducto,
            int nuevaCantidad,
            double nuevoPrecio) {

        if (base == null) {
            return null;
        }

        Pedido clon = base.clone();
        clon.setIdPedido(0);
        clon.setNombreCliente(nuevoNombre);
        clon.setDniCliente(nuevoDni);
        clon.setProducto(nuevoProducto);
        clon.setCantidad(nuevaCantidad);
        clon.setPrecioUnitario(nuevoPrecio);
        clon.setEstado("Pendiente");

        return clon;
    }

    public boolean cambiarEstadoPedido(Pedido p, String nuevoEstado) {
        if (p == null || nuevoEstado == null) {
            return false;
        }

        String estado = nuevoEstado.trim();

        if (estado.equalsIgnoreCase("PENDIENTE")) {
            estado = "Pendiente";
        } else if (estado.equalsIgnoreCase("ENVIADO")) {
            estado = "Enviado";
        } else {
            System.out.println("Estado no válido.");
            return false;
        }

        if (p.getIdPedido() <= 0) {
            System.out.println("El pedido no tiene un ID válido.");
            return false;
        }

        boolean actualizado = dao.actualizarEstado(p.getIdPedido(), estado);

        if (actualizado) {
            if ("Pendiente".equals(estado)) {
                p.cambiarEstado(new EstadoPendiente());
            } else {
                p.cambiarEstado(new EstadoEnviado());
            }
        }

        return actualizado;
    }
}