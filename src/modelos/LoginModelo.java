/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModelo {

    private Connection connection;

    public LoginModelo(Connection connection) {
        this.connection = connection;
    }

    public String verificarCredenciales(String correo, String contrasena) throws SQLException {
        String rol = null;
        String query = "SELECT rol FROM usuarios WHERE correo = ? AND contrasena = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                rol = rs.getString("rol");  // Asumiendo que 'rol' es el nombre de la columna que contiene el rol del usuario
            }
        }
        
        return rol;
    }
}
