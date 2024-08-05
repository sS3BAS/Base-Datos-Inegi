/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistainegi;

import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class validacion {

    public boolean validarCorreos(String cadena) {
        String caracteresNoPermitidos = "[áéíóúÁÉÍÓÚñÑ\\s]";
        if (Pattern.compile(caracteresNoPermitidos).matcher(cadena).find()) {
            return false;
        }

        String[] dominiosPermitidos = {"@gmail.com", "@yahoo.com", "@hotmail.com","outlook.com", "@itoaxaca.edu.mx"};
        for (String dominio : dominiosPermitidos) {
            if (cadena.endsWith(dominio)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validarLetras(String texto) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$";
        return Pattern.matches(regex, texto);
    }
    public boolean validarLetrasNombre(String texto) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        return Pattern.matches(regex, texto);
    }
    
    public boolean validarLongitud(String numero, int maxLongitud) {
        String numeroStr = String.valueOf(numero);
        return numeroStr.length() <= maxLongitud;
    }
    
     
     public static boolean validarContrasena(String contrasena) {
        // Ejemplo de validación básica
        // Cambia esto según los requisitos de tu contraseña
        if (contrasena.length() < 8) {
            return false;
        }
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneCaracterEspecial = false;

        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isLowerCase(c)) tieneMinuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
            if (!Character.isLetterOrDigit(c)) tieneCaracterEspecial = true;
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneCaracterEspecial;
    }
    public static boolean esNumero(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        for (char c : cadena.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public int calcularEdad(String fechaNacimientoStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, hoy);
        return periodo.getYears();
    }

    public boolean validarMayorDeEdad(String fechaNacimientoStr) {
        int edad = calcularEdad(fechaNacimientoStr);
        return edad >= 18;
    }
}