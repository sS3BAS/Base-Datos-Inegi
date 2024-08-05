/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import vistainegi.conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.SQLException;

public class UsuariosModelo {
    private conexion cone;
    private Connection con;
    private PreparedStatement ps;

    public UsuariosModelo() {
        this.cone = new conexion();
    }

    public void MostrarUsuariios(JTable tablausuarios) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablausuarios.setRowSorter(ordenarTabla);
        
        model.addColumn("id");
        model.addColumn("correo");
        model.addColumn("usuario");
        model.addColumn("contrasena");
        model.addColumn("rol");
        
        tablausuarios.setModel(model);
        
        String sql = "select * from usuarios;";
        String[] datos = new String[5];
        Statement st;
        
        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                model.addRow(datos);
            }

            tablausuarios.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }
    }
    
     public void eliminarUsuario(int userId) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = cone.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void editarUsuario(int userId, String correo, String usuario, String contrasena, String rol) {
        String sql = "UPDATE usuarios SET correo = ?, usuario = ?, contrasena = ?, rol = ? WHERE id = ?";

        try (Connection conn = cone.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            pstmt.setString(2, usuario);
            pstmt.setString(3, contrasena);
            pstmt.setString(4, rol);
            pstmt.setInt(5, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar usuario: " + e.getMessage());
        }
    }
    
    public DefaultTableModel BuscarUsuariios(JTable tablausuarios, String buscar) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablausuarios.setRowSorter(ordenarTabla);
        
        model.addColumn("id");
        model.addColumn("correo");
        model.addColumn("usuario");
        model.addColumn("contrasena");
        model.addColumn("rol");
        
        tablausuarios.setModel(model);
        
        String sql = "select * from usuarios WHERE rol LIKE '%" + buscar + "%' OR usuario LIKE '%"+buscar+"%'";
        String[] datos = new String[5];
        Statement st;
        
        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                model.addRow(datos);
            }

            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }

        return model;
    }

    
}
