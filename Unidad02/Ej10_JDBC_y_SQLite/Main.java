package Ej10_JDBC_y_SQLite;

import java.nio.file.Path;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of("Unidad02/Ej10_JDBC_y_SQLite/formula1.db");
        mostrarClasificacionFinal(ruta);





    }

    public static Connection conectarBD(String database) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }

    public static ResultSet executeQuery(Connection connection, String query) {
        PreparedStatement sentence = null;
        ResultSet resultSet = null;

        try {
            sentence = connection.prepareStatement(query);
            resultSet = sentence.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public static void showQuery(ResultSet resultSet, String campos) {

        String[] listaCampos = campos.split(",");
        try {
            while (resultSet.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarClasificacionFinal(Path ruta) {
        Connection connection = null;
        PreparedStatement sentence = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:" + ruta);

            String select = "Select Name, sum(Points) as points from Drivers " +
                    "inner join Results " +
                    "on Drivers.DriverID = Results.DriverID " +
                    "group by Name " +
                    "order by points desc";
            sentence = connection.prepareStatement(select);

            ResultSet resultSet = sentence.executeQuery();

            System.out.println("Nombre\tPuntos");
            System.out.println("--------------");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + "\t" + resultSet.getString("points"));
            }

            resultSet.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void mostrarPilotos30AnyosOMas(Path ruta) {

    }
}
