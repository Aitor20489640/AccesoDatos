package Ej11_DAO.dao.sql;

import Ej11_DAO.dao.PilotoDAO;
import Ej11_DAO.models.Piloto;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLitePilotoDAOImpl implements PilotoDAO {
    final String FINDALL = "SELECT * FROM Drivers";
    final String FINDBYID = "SELECT * FROM Drivers WHERE DriverID = ?";
    final String SAVE = "INSERT INTO Drivers (DriverID, Name, DateOfBirth, Team) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE Drivers SET Name = ?, DateOfBirth = ?, Team = ? WHERE DriverID = ?";
    final String DELETE = "DELETE FROM Drivers WHERE DriverID = ?";

    private HikariDataSource dataSource;

    public SQLitePilotoDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Piloto> findAll() {
        List<Piloto> pilotos = new ArrayList<>();
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                pilotos.add(convertToPiloto(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return pilotos;
    }

    @Override
    public Piloto findById(Integer id) {
        Piloto piloto = null;
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                piloto = convertToPiloto(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return piloto;
    }

    @Override
    public void save(Piloto piloto) {
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setInt(1, piloto.getNumero());
            sentencia.setString(2, piloto.getNombre());
            sentencia.setString(3, piloto.getFechaNacimientoString());
            sentencia.setString(4, piloto.getEquipo().getNombre());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Piloto piloto) {
        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(UPDATE)) {
            sentencia.setString(1, piloto.getNombre());
            sentencia.setString(2, piloto.getFechaNacimientoString());
            sentencia.setString(3, piloto.getEquipo().getNombre());
            sentencia.setInt(4, piloto.getNumero());
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

    public Piloto convertToPiloto(ResultSet rs) {

        try {
            String date = rs.getString("DateOfBirth");
            int indexOf = date.indexOf(" ");
            return new Piloto(
                    rs.getString("Name"),
                    rs.getInt("DriverID"),
                    new SQLiteEscuderiaDAOImpl(dataSource).findById(rs.getString("Team")),
                    LocalDate.parse(date.substring(0, indexOf)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
