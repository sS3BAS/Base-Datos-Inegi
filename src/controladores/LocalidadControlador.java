/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.LocalidadModelo;
import vistainegi.Localidad;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LocalidadControlador {
    private Localidad vista;
    private LocalidadModelo modelo;
    
    public LocalidadControlador(Localidad vista, LocalidadModelo modelo) {
        this.vista = vista;
        this.modelo = new LocalidadModelo();
       // cargarDatos();
    }
    
     public void inicializar() {
        vista.setVisible(true);
        cargarDatos();
    }

    public void cargarDatos() {
       modelo.mostrarLocalidades(vista.getTblLocalidades());
        
}
}
