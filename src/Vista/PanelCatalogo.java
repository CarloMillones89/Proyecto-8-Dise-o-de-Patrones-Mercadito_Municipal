/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;
import DAO.IProductoDAO;
import DAO.ProductoDAOProxy;
import Modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
/**
 *
 * @author Carlo
 */
public class PanelCatalogo extends javax.swing.JPanel {

    private final IProductoDAO productoDAO;
    private final Consumer<Producto> alSeleccionar;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;

    public PanelCatalogo(String rol) {
        this(rol, null);
    }

    public PanelCatalogo(String rol, Consumer<Producto> alSeleccionar) {

        this.productoDAO = new ProductoDAOProxy(rol);
        this.alSeleccionar = alSeleccionar;

        inicializarComponentes();
        cargarProductos();
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(235, 238, 242));

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // TÍTULO
        JLabel titulo = new JLabel(
                "CATÁLOGO DE PRODUCTOS - MERCADITO MUNICIPAL",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(35, 45, 60));

        // BARRA SUPERIOR DE ACCIONES
        JPanel barraAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        barraAcciones.setBackground(new Color(215, 220, 228));

        JButton btnActualizar = new JButton("Actualizar");
        JButton btnSeleccionar = new JButton("Seleccionar producto");
        JButton btnCerrar = new JButton("Cerrar");

        barraAcciones.add(btnActualizar);
        barraAcciones.add(btnSeleccionar);
        barraAcciones.add(btnCerrar);

        // BUSCADOR
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(new Color(235, 238, 242));

        txtBuscar = new JTextField(25);
        JButton btnBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar producto:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // ENCABEZADO GENERAL
        JPanel encabezado = new JPanel(new BorderLayout(5, 5));
        encabezado.setBackground(new Color(235, 238, 242));

        encabezado.add(titulo, BorderLayout.NORTH);
        encabezado.add(barraAcciones, BorderLayout.CENTER);
        encabezado.add(panelBusqueda, BorderLayout.SOUTH);

        add(encabezado, BorderLayout.NORTH);

        // TABLA DE PRODUCTOS
        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "ID", "Código de barras", "Producto",
                    "Precio", "Stock", "Categoría"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setRowHeight(28);
        tablaProductos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tablaProductos.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        add(scroll, BorderLayout.CENTER);

        // EVENTOS
        btnActualizar.addActionListener(e -> cargarProductos());

        btnBuscar.addActionListener(e ->
                filtrarProductos(txtBuscar.getText().trim())
        );

        txtBuscar.addActionListener(e ->
                filtrarProductos(txtBuscar.getText().trim())
        );

        btnSeleccionar.addActionListener(e -> seleccionarProducto());

        btnCerrar.addActionListener(e -> cerrarVentana());

        // También permite seleccionar con doble clic
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionarProducto();
                }
            }
        });
    }

    private void cargarProductos() {

        modeloTabla.setRowCount(0);

        List<Producto> productos =
                productoDAO.consultarProductosDisponibles();

        mostrarProductos(productos);
    }

    private void filtrarProductos(String texto) {

        modeloTabla.setRowCount(0);

        List<Producto> productos =
                productoDAO.consultarProductosDisponibles();

        for (Producto producto : productos) {

            if (producto.getNombre() != null
                    && producto.getNombre().toLowerCase()
                            .contains(texto.toLowerCase())) {

                agregarFila(producto);
            }
        }
    }

    private void mostrarProductos(List<Producto> productos) {

        for (Producto producto : productos) {
            agregarFila(producto);
        }
    }

    private void agregarFila(Producto producto) {

        modeloTabla.addRow(new Object[]{
            producto.getIdProducto(),
            producto.getCodigoBarras(),
            producto.getNombre(),
            producto.getPrecioVenta(),
            producto.getStockActual(),
            producto.getCategoria()
        });
    }

    private void seleccionarProducto() {

        int fila = tablaProductos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Primero debes seleccionar un producto.",
                    "Producto no seleccionado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tablaProductos.convertRowIndexToModel(fila);

        int idProducto = Integer.parseInt(
                modeloTabla.getValueAt(filaModelo, 0).toString()
        );

        String codigo = modeloTabla.getValueAt(filaModelo, 1).toString();
        String nombre = modeloTabla.getValueAt(filaModelo, 2).toString();
        double precio = Double.parseDouble(
                modeloTabla.getValueAt(filaModelo, 3).toString()
        );

        int stock = Integer.parseInt(
                modeloTabla.getValueAt(filaModelo, 4).toString()
        );

        String categoria = modeloTabla.getValueAt(filaModelo, 5).toString();

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setCodigoBarras(codigo);
        producto.setNombre(nombre);
        producto.setPrecioVenta(precio);
        producto.setStockActual(stock);
        producto.setCategoria(categoria);

        if (alSeleccionar != null) {
            alSeleccionar.accept(producto);
        }

        cerrarVentana();
    }

    private void cerrarVentana() {

        Window ventana = SwingUtilities.getWindowAncestor(this);

        if (ventana != null) {
            ventana.dispose();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
