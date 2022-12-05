package Ej11_DAO.dao.sql;

import Ej11_DAO.dao.CircuitoDAO;
import Ej11_DAO.models.Circuito;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLiteCircuitoDAOImpl implements CircuitoDAO {
    final String FINDALL = "SELECT * FROM Tracks";
    final String FINDBYID = "SELECT * FROM Tracks WHERE TrackID = ?";
    final String SAVE = "INSERT INTO Tracks (TrackID, Country, DateOfRace) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE Tracks SET Country = ?, DateOfRace = ? WHERE TrackID = ?";
    final String DELETE = "DELETE FROM Tracks WHERE TrackID = ?";

    private HikariDataSource dataSource;

    public SQLiteCircuitoDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Circuito> findAll() {
        List<Circuito> circuitos = new ArrayList<>();
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                circuitos.add(convertToCircuito(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return circuitos;
    }

    @Override
    public Circuito findById(Integer id) {
        Circuito circuito = null;
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                circuito = convertToCircuito(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return circuito;
    }

    @Override
    public void save(Circuito circuito) {
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setInt(1, circuito.getRonda());
            sentencia.setString(2, circuito.getPais());
            sentencia.setString(3, circuito.getFechaCarreraString());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Circuito circuito) {
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(UPDATE)) {
            sentencia.setString(1, circuito.getPais());
            sentencia.setString(2, circuito.getFechaCarreraString());
            sentencia.setInt(3, circuito.getRonda());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(DELETE)) {
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public Circuito convertToCircuito(ResultSet rs) {
        try {
            String date = rs.getString("DateOfRace");
            int indexOf = date.indexOf(" ");
            return new Circuito(
                    rs.getInt("TrackID"),
                    rs.getString("Country"),
                    LocalDate.parse(date.substring(0, indexOf))
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
