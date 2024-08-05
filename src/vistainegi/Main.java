/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vistainegi;

/**
 *
 * @author marle
 */
public class Main {

    static conexion cone;
    static boolean cambios;
    
    public static void main (String args []) {
        
        Loggin L = new Loggin();
        cone = new conexion();
        cambios = false;
        L.setCone(cone);
        L.setVisible(true);
    }

    public static conexion getCone() {
        return cone;
    }

    public static void setCone(conexion cone) {
        Main.cone = cone;
    }

    public static boolean isCambios() {
        return cambios;
    }

    public static void setCambios(boolean cambios) {
        Main.cambios = cambios;
    
    }
    
}
