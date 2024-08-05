/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.EntidadModelo;
import vistainegi.Entidades;


public class EntidadControlador {
    private EntidadModelo modelo;
    private Entidades vista;

    public EntidadControlador(Entidades vista) {
        this.modelo = new EntidadModelo();
        this.vista = vista;
        cargarEntidades();
    }

    public void cargarEntidades() {
        modelo.MostrarEntidades(vista.getTblEntidades());
    }
}
