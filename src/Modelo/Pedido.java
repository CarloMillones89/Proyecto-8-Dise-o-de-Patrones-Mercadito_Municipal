package Modelo;
import java.util.ArrayList;
import java.util.List;


public class Pedido implements Cloneable {
    private int idPedido;
    private String nombreCliente;
    private String dniCliente;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private String fechaPedido;
    private String estado;
    private String tipoPedido;
    private String direccionEntrega;
    private double costoEnvio;
    
    // VARIABLES PARA STATE Y OBSERVER
    private EstadoPedido estadoActual = new EstadoPendiente();
    private List<ObservadorPedido> observadores = new ArrayList<>();
        
    
    public Pedido() {
    }
    // Constructor para el DAO al leer de BD
    public Pedido(int idPedido, String nombreCliente, String dniCliente,
                  String producto, int cantidad, double precioUnitario,
                  double total, String fechaPedido, String estado,
                  String tipoPedido, String direccionEntrega, double costoEnvio) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.costoEnvio = costoEnvio;
    }
    //prototype
    @Override
    public Pedido clone() {
    try {
        Pedido clon = (Pedido) super.clone();

        // Evita compartir la lista de observadores.
        clon.observadores = new ArrayList<>(this.observadores);

        // Copia el estado actual del pedido.
        if ("Enviado".equalsIgnoreCase(this.estado)) {
            clon.estadoActual = new EstadoEnviado();
        } else {
            clon.estadoActual = new EstadoPendiente();
        }

        return clon;

    } catch (CloneNotSupportedException e) {
        throw new RuntimeException("Error al clonar Pedido", e);
    }
}
    // METODOS PARA EL PATRON OBSERVER
    public void agregarObservador(ObservadorPedido observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }
    public void eliminarObservador(ObservadorPedido observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (ObservadorPedido observador :
            new ArrayList<>(observadores)) {

        observador.actualizar(this);
    }
    }
    // METODOS PARA EL PATRON STATE
    public void cambiarEstado(EstadoPedido nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El estado no puede ser null.");
        }
        this.estadoActual = nuevoEstado;
        this.estado = nuevoEstado.getNombreEstado();
        notificarObservadores();
    }
    public String getEstadoActual() {
        return estadoActual.getNombreEstado();
    }
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getDniCliente() { return dniCliente; }
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(String fechaPedido) { this.fechaPedido = fechaPedido; }
    public String getEstado() { return estado; }
    
    public void setEstado(String estado) {
        this.estado = estado;
        if (estado != null && estado.equalsIgnoreCase("Enviado")) {
            this.estadoActual = new EstadoEnviado();
        } else {
            this.estadoActual = new EstadoPendiente();
        }
    }
    public String getTipoPedido() { return tipoPedido; }
    public void setTipoPedido(String tipoPedido) { this.tipoPedido = tipoPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public double getCostoEnvio() { return costoEnvio; }
    public void setCostoEnvio(double costoEnvio) { this.costoEnvio = costoEnvio; }
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + idPedido +
                ", cliente='" + nombreCliente + '\'' +
                ", dni='" + dniCliente + '\'' +
                ", producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precioUnitario +
                ", total=" + total +
                ", fecha='" + fechaPedido + '\'' +
                ", estado='" + estado + '\'' +
                 ", tipo='" + tipoPedido + '\'' +
                ", direccion='" + direccionEntrega + '\'' +
                ", costoEnvio=" + costoEnvio +
                '}';
    }
}