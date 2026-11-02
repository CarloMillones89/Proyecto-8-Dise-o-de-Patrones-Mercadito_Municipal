/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Singleton.DatabaseSingleton;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Carlo
 */
public class UnitOfWork {
    private final Connection conexion;
    private boolean transaccionActiva;

    public UnitOfWork() {
        this.conexion = DatabaseSingleton.getInstancia().getConexion();
    }

    public synchronized void iniciar() throws SQLException {
        if (transaccionActiva) {
            throw new SQLException("Ya existe una transacción activa.");
        }

        conexion.setAutoCommit(false);
        transaccionActiva = true;
    }

    public Connection getConexion() {
        return conexion;
    }

    public synchronized void confirmar() throws SQLException {
        if (transaccionActiva) {
            conexion.commit();
            conexion.setAutoCommit(true);
            transaccionActiva = false;
        }
    }

    public synchronized void revertir() throws SQLException {
        if (transaccionActiva) {
            try {
                conexion.rollback();
            } finally {
                conexion.setAutoCommit(true);
                transaccionActiva = false;
            }
        }
    }
}
