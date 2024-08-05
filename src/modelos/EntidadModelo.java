/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import vistainegi.conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class EntidadModelo {
    private conexion cone;

    public EntidadModelo() {
        //conexion con = new conexion();
        this.cone = new conexion();
    }

     public void MostrarEntidades(JTable tablaentidades) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablaentidades.setRowSorter(ordenarTabla);

        model.addColumn("identidad");
        model.addColumn("nom_entidad");

        tablaentidades.setModel(model);

        String sql = "select * from entidades;";

        String[] datos = new String[2];
        Statement st;

        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                model.addRow(datos);
            }

            tablaentidades.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }
    }
     
     public DefaultTableModel BuscarEntidades(JTable tablaentidades, String buscar) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablaentidades.setRowSorter(ordenarTabla);

        model.addColumn("identidad");
        model.addColumn("nom_entidad");

        String sql = "select * from entidades WHERE nom_entidad LIKE '%" + buscar + "%'";

        String[] datos = new String[2];
        Statement st;

        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                model.addRow(datos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }

        return model;
    }
}
