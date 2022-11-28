package Ej10_JDBC_y_SQLite;

import java.sql.*;
import java.util.Scanner;

public class SQLHandler {

    public static Connection conectarBD(String database) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(database);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }

    public static PreparedStatement prepareStatement(Connection connection, String query) {
        PreparedStatement sentence;

        try {
            sentence = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sentence;
    }

    public static ResultSet executeQuery(PreparedStatement sentence) {
        ResultSet resultSet;

        try {
            resultSet = sentence.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    /*public static void showQuery(ResultSet resultSet, String campos) {

        String[] listaCampos = campos.split(",");
        try {
            while (resultSet.next()){
                System.out.println(resultSet.getString("name") + "\t" + resultSet.getString("points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static String insertValues() {
        StringBuilder select = new StringBuilder("INSERT INTO");
        Scanner sc = new Scanner(System.in);
        boolean ok;

        System.out.print("Escribe el nombre de la tabla: ");
        select.append(sc.nextLine());
        select.append(" VALUES\n(");
        do {
            System.out.println("Escribe los datos que quieres introducir: ");
            select.append(sc.nextLine());
            select.append(")");
            System.out.println("Quieres seguir introduciendo? S/n: ");
            ok = (sc.nextLine().equalsIgnoreCase("S"));
            if (ok) {
                select.append(",\n(");
            } else {
                select.append(";");
            }
        }while (ok);

        return select.toString();
    }
}
