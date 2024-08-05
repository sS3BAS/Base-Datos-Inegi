/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import vistainegi.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MunicipiosModelo {
    private conexion cone;

    public MunicipiosModelo(conexion cone) {
        this.cone = cone;
    }

    public void MostrarMunicipios(JTable tablamunicipios) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(model);
        tablamunicipios.setRowSorter(OrdenarTabla);

        model.addColumn("idEntidad");
        model.addColumn("idMunicipio");
        model.addColumn("nom_municipio");

        tablamunicipios.setModel(model);

        String sql = "SELECT * FROM municipios;";
        String[] date = new String[3];
        Statement st;

        try {//(Connection connection = cone.getConnection();
             //Statement st = connection.createStatement();
             //ResultSet rs = st.executeQuery(sql)) {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                date[0] = rs.getString(1);
                date[1] = rs.getString(2);
                date[2] = rs.getString(3);
                model.addRow(date);
            }

            tablamunicipios.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla: " + e.toString());
        }
    }
    
    public DefaultTableModel BuscarMunicipios(JTable tablamunicipios, String buscar) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablamunicipios.setRowSorter(ordenarTabla);

        model.addColumn("identidad");
        model.addColumn("idMunicipio");
        model.addColumn("nom_municipio");

        String sql = "select * from municipios WHERE nom_municipio LIKE '%" + buscar + "%'";

        String[] datos = new String[3];
        Statement st;

        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                model.addRow(datos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }

        return model;
    }
}
