/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistainegi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/inegi2020"; // URL de conexión
    private static final String USER = "root"; // Usuario
    private static final String PASSWORD = "089818sM/"; // Contraseña

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos, error: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para más detalles
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para más detalles
            return null;
        }
    }
}

