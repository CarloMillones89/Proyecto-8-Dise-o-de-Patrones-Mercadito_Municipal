package View;
import Controller.PedidoController;
import Model.Pedido;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class PedidoView_JF extends javax.swing.JFrame {
    private String rolActual = "CLIENTE"; 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PedidoView_JF.class.getName());
    private PedidoController controller;
    private DefaultTableModel modeloTabla;
    private Pedido ultimoPedido;
    public PedidoView_JF() {
        initComponents();
        controller = new PedidoController();
        configurarTabla();
        setLocationRelativeTo(null);
        setTitle("Sistema de Pedidos en Línea - Mercado Municipal");
        setResizable(false);
        actualizarCamposDelivery();
        rbTienda.addActionListener(e -> actualizarCamposDelivery());
        rbDelivery.addActionListener(e -> actualizarCamposDelivery());
        //LOGIN CON ROLES Y CONTRASEÑA
        String[] opciones = {"ADMIN", "CLIENTE"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, 
                "Seleccione su rol para ingresar:", 
                "Inicio de Sesión", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[1]); 
        if (seleccion == null) {
            System.exit(0);
        }
        if (seleccion.equals("ADMIN")) {
            String pass = JOptionPane.showInputDialog(this, "Ingrese contraseña de Administrador:");
            if (pass != null && pass.equals("1234")) {
                rolActual = "ADMIN";
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador.");
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Entrando como Cliente.");
                rolActual = "CLIENTE";
            }
        } else {
            rolActual = "CLIENTE";
            JOptionPane.showMessageDialog(this, "Bienvenido Cliente.");
        }
        if (rolActual.equals("CLIENTE")) {
            btnCambiarEstado.setVisible(false);
        }
    }
private void actualizarCamposDelivery() {
    boolean esDelivery = rbDelivery.isSelected();
    txtDireccion.setEnabled(esDelivery);
    txtCostoEnvio.setEnabled(esDelivery);
    if (!esDelivery) {
        txtDireccion.setText("");
        txtCostoEnvio.setText("");
    }
}
private void configurarTabla() {
    modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Cliente", "DNI", "Producto",
                         "Cantidad", "Precio", "Total", "Fecha", "Estado", "Tipo"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tblPedidos.setModel(modeloTabla);
}
private void cargarPedidos() {
         modeloTabla.setRowCount(0);
        List<Pedido> lista = controller.consultarTodos(rolActual);
        if (lista.isEmpty()) {
            if (rolActual.equals("CLIENTE")) {
                JOptionPane.showMessageDialog(this, "Acceso denegado. El Proxy no permite ver todos los pedidos a los clientes.", "Proxy", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay pedidos registrados en la base de datos.");
            }
            return;
        }
        for (Pedido p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getIdPedido(),
                p.getNombreCliente(),
                p.getDniCliente(),
                p.getProducto(),
                p.getCantidad(),
                p.getPrecioUnitario(),
                p.getTotal(),
                p.getFechaPedido(),
                p.getEstado(),
                p.getTipoPedido()
            });
        }
        if (!lista.isEmpty()) {
            ultimoPedido = lista.get(0);
        }
}
private void cargarPedidosPorDni(String dni) {
    modeloTabla.setRowCount(0);
    List<Pedido> lista = controller.consultarPorDni(dni);
    if (lista.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                "No se encontraron pedidos con ese DNI.");
        return;
    }
    for (Pedido p : lista) {
        modeloTabla.addRow(new Object[]{
            p.getIdPedido(),
            p.getNombreCliente(),
            p.getDniCliente(),
            p.getProducto(),
            p.getCantidad(),
            p.getPrecioUnitario(),
            p.getTotal(),
            p.getFechaPedido(),
            p.getEstado(),
            p.getTipoPedido()
        });
    }
    ultimoPedido = lista.get(0);
}
private void limpiarCampos() {
    txtNombre.setText("");
    txtDni.setText("");
    txtProducto.setText("");
    txtCantidad.setText("");
    txtPrecio.setText("");
    txtDireccion.setText("");
    txtCostoEnvio.setText("");
    rbTienda.setSelected(true);
    actualizarCamposDelivery();
    txtNombre.requestFocus();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCostoEnvio = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        rbDelivery = new javax.swing.JRadioButton();
        rbTienda = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        JScrollPane = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SISTEMA DE PEDIDOS EN LINEA - MERCADO MUNICIPAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PEDIDO:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Nombre:");

        jLabel2.setText("DNI:");

        jLabel3.setText("Cantidad:");

        jLabel4.setText("Producto:");

        jLabel5.setText("Precio:");

        txtNombre.addActionListener(this::txtNombreActionPerformed);

        txtDni.addActionListener(this::txtDniActionPerformed);

        txtCantidad.addActionListener(this::txtCantidadActionPerformed);

        jLabel6.setText("Direccion:");

        jLabel7.setText("Costo envio:");

        buttonGroup1.add(rbDelivery);
        rbDelivery.setText("Pedido Delivery");
        rbDelivery.addActionListener(this::rbDeliveryActionPerformed);

        buttonGroup1.add(rbTienda);
        rbTienda.setSelected(true);
        rbTienda.setText("Pedido Tienda");
        rbTienda.addActionListener(this::rbTiendaActionPerformed);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbDelivery)
                .addGap(30, 30, 30)
                .addComponent(rbTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDelivery)
                    .addComponent(rbTienda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(txtCantidad)
                                    .addComponent(txtDireccion))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCostoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCIONES", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        btnCambiarEstado.setText("Cambiar Estado");
        btnCambiarEstado.addActionListener(this::btnCambiarEstadoActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir)
                            .addComponent(btnCambiarEstado)
                            .addComponent(btnConsultar))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnRegistrar)
                .addGap(18, 18, 18)
                .addComponent(btnConsultar)
                .addGap(18, 18, 18)
                .addComponent(btnCambiarEstado)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTA DE PEDIDOS:"));

        JScrollPane.setToolTipText("");

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "DNI", "Producto", "Cant", "Precio", "Total", "Est"
            }
        ));
        JScrollPane.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (rolActual.equals("CLIENTE")) {
            JOptionPane.showMessageDialog(this, 
                    "Acceso Denegado. Solo el Administrador puede consultar pedidos.", 
                    "Seguridad - Proxy", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String dni = JOptionPane.showInputDialog(this, 
                "Ingrese DNI para filtrar (deje vacío para ver TODOS):",
                "Consultar pedidos",
                JOptionPane.QUESTION_MESSAGE);
        if (dni == null) return; 
        dni = dni.trim();
        if (dni.isEmpty()) {
            cargarPedidos(); 
        } else {
            cargarPedidosPorDni(dni); 
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
    try {
        if (txtNombre.getText().trim().isEmpty()
                || txtDni.getText().trim().isEmpty()
                || txtProducto.getText().trim().isEmpty()
                || txtCantidad.getText().trim().isEmpty()
                || txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos comunes son obligatorios.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tipo = rbDelivery.isSelected() ? "DELIVERY" : "TIENDA";
        String direccion = "";
        double costoEnvio = 0.0;
        if ("DELIVERY".equals(tipo)) {
            if (txtDireccion.getText().trim().isEmpty()
                    || txtCostoEnvio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Para Delivery, la dirección y el costo de envío son obligatorios.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            direccion = txtDireccion.getText().trim();
            costoEnvio = Double.parseDouble(txtCostoEnvio.getText().trim());
        }
        boolean ok = controller.registrarPedidoConFactory(
                tipo,
                txtNombre.getText().trim(),
                txtDni.getText().trim(),
                txtProducto.getText().trim(),
                Integer.parseInt(txtCantidad.getText().trim()),
                Double.parseDouble(txtPrecio.getText().trim()),
                direccion,
                costoEnvio
        );
        if (ok) {
            JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.");
            limpiarCampos();
            cargarPedidos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo registrar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Cantidad, Precio y Costo de Envío deben ser números válidos.",
                "Datos inválidos",
                JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int r = JOptionPane.showConfirmDialog(this,
            "¿Desea salir del sistema?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);
    if (r == JOptionPane.YES_OPTION) {
        System.exit(0);
    }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rbDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeliveryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDeliveryActionPerformed

    private void rbTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTiendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTiendaActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
       if (rolActual.equals("CLIENTE")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Acceso Denegado. Solo el Administrador puede cambiar estados.");
            return;
        }
        int fila = tblPedidos.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un pedido de la tabla.");
            return;
        }
        int idPedido = Integer.parseInt(tblPedidos.getValueAt(fila, 0).toString());
        String nombreCliente = tblPedidos.getValueAt(fila, 1).toString();
        Model.Pedido pedidoSeleccionado = new Model.Pedido();
        pedidoSeleccionado.setIdPedido(idPedido);
        pedidoSeleccionado.setNombreCliente(nombreCliente);
        // Preguntamos el nuevo estado
        String[] opcionesEstado = {"PENDIENTE", "ENVIADO"};
        String seleccion = (String) javax.swing.JOptionPane.showInputDialog(
                this, "Seleccione el nuevo estado:", "Cambiar Estado",
                javax.swing.JOptionPane.QUESTION_MESSAGE, null, opcionesEstado, opcionesEstado[0]);
        if (seleccion == null) return; 
        // El Observer salta automaticamente al cambiar de estado
        pedidoSeleccionado.agregarObservador(new Model.ObservadorPedido() {
            @Override
            public void actualizar(String mensaje) {
                javax.swing.JOptionPane.showMessageDialog(null, "NOTIFICACION AL CLIENTE: " + mensaje);
            }
        });

        // Llamamos al controlador (que aplica el STATE y guarda en la BD)
        controller.cambiarEstadoPedido(pedidoSeleccionado, seleccion);
        // Recargamos la tabla para que veas el cambio reflejado desde la BD
        cargarPedidos();
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PedidoView_JF().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton rbDelivery;
    private javax.swing.JRadioButton rbTienda;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCostoEnvio;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
