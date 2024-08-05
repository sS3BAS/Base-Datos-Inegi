/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vistainegi.conexion;

public class EditarInformacionModelo {

    private Connection con;

    public EditarInformacionModelo() {
        conexion cn = new conexion();
        con = cn.getConnection();
    }

    public List<String> getEstados() {
        List<String> estados = new ArrayList<>();
        String sql = "SELECT nom_entidad FROM entidades";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                estados.add(rs.getString("nom_entidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    public List<String> getMunicipios(String estado) {
        List<String> municipios = new ArrayList<>();
        String query = "SELECT m.nom_municipio FROM municipios m JOIN entidades e ON m.idEntidad = e.idEntidad WHERE e.nom_entidad = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, estado);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                municipios.add(resultSet.getString("nom_municipio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return municipios;
    }

    public List<String> getLocalidades(String municipio) {
        List<String> localidades = new ArrayList<>();
        String query = "SELECT l.nom_localidad FROM localidades l JOIN municipios m ON l.idMunicipio = m.idMunicipio WHERE m.nom_municipio = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, municipio);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                localidades.add(resultSet.getString("nom_localidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidades;
    }
    
    public int obtenerIdEntidad(String nombreEntidad) {
        int idEntidad = -1;
        String query = "SELECT idEntidad FROM entidades WHERE nom_entidad = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreEntidad);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idEntidad = rs.getInt("idEntidad");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEntidad;
    }

    public int obtenerIdMunicipio(String nombreMunicipio) {
        int idMunicipio = -1;
        String query = "SELECT idMunicipio FROM municipios WHERE nom_municipio = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreMunicipio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idMunicipio = rs.getInt("idMunicipio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMunicipio;
    }

    public int obtenerIdLocalidad(String nombreLocalidad) {
        int idLocalidad = -1;
        String query = "SELECT idLocalidad FROM localidades WHERE nom_localidad = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreLocalidad);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idLocalidad = rs.getInt("idLocalidad");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idLocalidad;
    }

   public boolean insertarCenso(int idEntidad, int idMunicipio, int idLocalidad, String nom_localidad, int poblacionTotal, int poblacionMasculina, int poblacionFemenina, int noEscuelas, int noViviendas) {
    String checkQuery = "SELECT COUNT(*) FROM localidades WHERE idEntidad = ? AND idMunicipio = ? AND idLocalidad = ?";
    String insertQuery = "INSERT INTO censo_pob (idEntidad, idMunicipio, idLocalidad, nom_localidad, pob_total, poblacion_m, poblacion_f, no_escuelas, no_viviendas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
        checkStmt.setInt(1, idEntidad);
        checkStmt.setInt(2, idMunicipio);
        checkStmt.setInt(3, idLocalidad);
        
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                // La combinación de idEntidad, idMunicipio e idLocalidad existe
                try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, idEntidad);
                    insertStmt.setInt(2, idMunicipio);
                    insertStmt.setInt(3, idLocalidad);
                    insertStmt.setString(4, nom_localidad);
                    insertStmt.setInt(5, poblacionTotal);
                    insertStmt.setInt(6, poblacionMasculina);
                    insertStmt.setInt(7, poblacionFemenina);
                    insertStmt.setInt(8, noEscuelas);
                    insertStmt.setInt(9, noViviendas);

                    int rowsInserted = insertStmt.executeUpdate();
                    return rowsInserted > 0;
                }
            } else {
                // La combinación no existe
                System.err.println("La combinación de idEntidad, idMunicipio e idLocalidad no existe en la tabla localidades.");
                return false;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
