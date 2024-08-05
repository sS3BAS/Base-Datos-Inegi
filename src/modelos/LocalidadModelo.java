/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import vistainegi.conexion;

public class LocalidadModelo {
    private conexion cone;
    
    public LocalidadModelo(){
        this.cone = new conexion();
    }
    
    public void mostrarLocalidades(JTable tablaLocalidades) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablaLocalidades.setRowSorter(ordenarTabla);

        model.addColumn("idEntidad");
        model.addColumn("idMunicipio");
        model.addColumn("idLocalidad");
        model.addColumn("nom_localidad");

        tablaLocalidades.setModel(model);

        String sql = "select * from localidades;";

        String[] datos = new String[4];
        Statement st;

        try {
          st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
             //System.out.print("dkjs");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                model.addRow(datos);
            }

            tablaLocalidades.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }
    }
    
    public DefaultTableModel BuscarLocalidad(JTable tablalocalidades, String buscar) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(model);
        tablalocalidades.setRowSorter(ordenarTabla);

        model.addColumn("identidad");
        model.addColumn("idMunicipio");
        model.addColumn("idLocalidad");
        model.addColumn("nom_localidad");

        String sql = "select * from localidades WHERE nom_localidad LIKE '%" + buscar + "%'";

        String[] datos = new String[4];
        Statement st;

        try {
            st = cone.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                model.addRow(datos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla " + e.toString());
        }

        return model;
    }
}
