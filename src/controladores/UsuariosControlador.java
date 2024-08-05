/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.UsuariosModelo;
import vistainegi.Usuarios;

public class UsuariosControlador {
    private UsuariosModelo modelo;
    private Usuarios vista;
    
    public UsuariosControlador(Usuarios vista) {
        this.modelo = new UsuariosModelo();
        this.vista = vista;
        cargarUsuarios();
        initController();
    }
    
    public void cargarUsuarios() {
        modelo.MostrarUsuariios(vista.getTblUsuarios());
    }
    
    private void initController() {
        vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        vista.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });
    }
    
   private void eliminarUsuario() {
    JTable table = vista.getTblUsuarios();
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(vista, "Selecciona una fila para eliminar.");
        return;
    }

    String userIdStr = model.getValueAt(selectedRow, 0).toString();
    int userId = Integer.parseInt(userIdStr); // Convierte el ID de usuario de String a Integer
    modelo.eliminarUsuario(userId);
    model.removeRow(selectedRow);
    JOptionPane.showMessageDialog(vista, "Usuario eliminado exitosamente.");
}

private void editarUsuario() {
    JTable table = vista.getTblUsuarios();
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(vista, "Selecciona una fila para editar.");
        return;
    }

    // Obtén los datos de la fila seleccionada
    String userIdStr = model.getValueAt(selectedRow, 0).toString();
    int userId = Integer.parseInt(userIdStr); // Convierte el ID de usuario de String a Integer
    String correo = model.getValueAt(selectedRow, 1).toString();
    String usuario = model.getValueAt(selectedRow, 2).toString();
    String contrasena = model.getValueAt(selectedRow, 3).toString();
    String rol = model.getValueAt(selectedRow, 4).toString();

    // Muestra un cuadro de diálogo para editar los datos
    correo = JOptionPane.showInputDialog(vista, "Correo:", correo);
    usuario = JOptionPane.showInputDialog(vista, "Usuario:", usuario);
    contrasena = JOptionPane.showInputDialog(vista, "Contraseña:", contrasena);
    rol = JOptionPane.showInputDialog(vista, "Rol:", rol);

    // Actualiza los datos en la base de datos
    modelo.editarUsuario(userId, correo, usuario, contrasena, rol);

    // Actualiza los datos en la tabla
    model.setValueAt(correo, selectedRow, 1);
    model.setValueAt(usuario, selectedRow, 2);
    model.setValueAt(contrasena, selectedRow, 3);
    model.setValueAt(rol, selectedRow, 4);

    JOptionPane.showMessageDialog(vista, "Usuario actualizado exitosamente.");
}
}
