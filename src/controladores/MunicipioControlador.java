/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.MunicipiosModelo;
import vistainegi.Municipios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MunicipioControlador {
    private Municipios vista;
    private MunicipiosModelo modelo;

    public MunicipioControlador(Municipios vista, MunicipiosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
        cargarDatos();
        //this.modelo = new MunicipiosModelo();
        //this.vista.getBtnActualizar().addActionListener(new ActualizarActionListener());
        this.vista.getBtnCerrar().addActionListener(new CerrarActionListener());
    }

    public void cargarDatos() {
        modelo.MostrarMunicipios(vista.getTblMunicipios());
    }
    public void inicializar() {
        vista.setVisible(true);
        cargarDatos();
    }

    /*private class ActualizarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarDatos();
        }
    }*/

    private class CerrarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.dispose();
        }
    }
}

