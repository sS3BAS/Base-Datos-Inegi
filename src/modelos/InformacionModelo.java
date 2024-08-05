/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import vistainegi.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InformacionModelo {
    private conexion cone = new conexion();
    private Connection con;
    private PreparedStatement ps;

    public void mostrarCensoPob(JTable table) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

    try {
        con = cone.getConnection();
        String sql = "SELECT * FROM censo_pob";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Object[] row = new Object[9];
            row[0] = rs.getInt("idEntidad");
            row[1] = rs.getInt("idMunicipio");
            row[2] = rs.getInt("idLocalidad");
            row[3] = rs.getString("nom_localidad");            
            row[4] = rs.getInt("pob_total");
            row[5] = rs.getInt("poblacion_m");
            row[6] = rs.getInt("poblacion_f");
            row[7] = rs.getInt("no_escuelas");
            row[8] = rs.getInt("no_viviendas");
            model.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    public boolean eliminarRegistro(int idEntidad, int idMunicipio, int idLocalidad) {
    String sql = "DELETE FROM censo_pob WHERE idEntidad = ? AND idMunicipio = ? AND idLocalidad = ?";
    
    try (Connection con = cone.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idEntidad);
        ps.setInt(2, idMunicipio);
        ps.setInt(3, idLocalidad);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean actualizarRegistro(int idEntidad, int idMunicipio, int idLocalidad, String nom_localidad, int pob_total, int poblacion_m, int poblacion_f, int no_escuelas, int no_viviendas) {
        Connection connection = cone.getConnection();
        String query = "UPDATE censo_pob SET nom_localidad = ?, pob_total = ?, poblacion_m = ?, poblacion_f = ?, no_escuelas = ?, no_viviendas = ? WHERE idEntidad = ? AND idMunicipio = ? AND idLocalidad = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom_localidad);
            statement.setInt(2, pob_total);
            statement.setInt(3, poblacion_m);
            statement.setInt(4, poblacion_f);
            statement.setInt(5, no_escuelas);
            statement.setInt(6, no_viviendas);
            statement.setInt(7, idEntidad);
            statement.setInt(8, idMunicipio);
            statement.setInt(9, idLocalidad);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public DefaultTableModel buscarCensoPob(JTable table, String buscar) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        try {
            con = cone.getConnection();
            String sql = "SELECT * FROM censo_pob WHERE nom_localidad LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[9];
                row[0] = rs.getInt("idEntidad");
                row[1] = rs.getInt("idMunicipio");
                row[2] = rs.getInt("idLocalidad");
                row[3] = rs.getString("nom_localidad");
                row[4] = rs.getInt("pob_total");
                row[5] = rs.getInt("poblacion_m");
                row[6] = rs.getInt("poblacion_f");
                row[7] = rs.getInt("no_escuelas");
                row[8] = rs.getInt("no_viviendas");
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return model;
    }

}
