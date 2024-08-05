/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import CrearPDF.CrearPDF;
import CrearPDF.EnvioCorreos2;
import vistainegi.conexion;
import modelos.UsuarioModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UsuarioControlador {
    private conexion conexion;    


public UsuarioControlador(){
    conexion = new conexion();
}

 public boolean crearUsuario(UsuarioModelo usuario) {
        Connection connection = conexion.getConnection();
        String query = "INSERT INTO usuarios (correo, usuario, contrasena, rol) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getCorreo());
            statement.setString(2, usuario.getUsuario());
            statement.setString(3, usuario.getContrasena());
            statement.setString(4, usuario.getRol());
            int rowsInserted = statement.executeUpdate();

            CrearPDF pdf = new CrearPDF(usuario.getCorreo());
            pdf.prueba();

            EnvioCorreos2 correo = new EnvioCorreos2(usuario.getCorreo(), "Bienvenido al INEGI", "Te has registrado correctamente.");
            correo.sendEmail();

            return true; // Retornar true si el registro y el envío del correo fueron exitosos
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}