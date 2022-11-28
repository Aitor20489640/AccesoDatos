package Ej10_JDBC_y_SQLite;

import java.nio.file.Path;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of("Unidad02/Ej10_JDBC_y_SQLite/formula1.db");
        Connection connection;
        PreparedStatement sentence;
        ResultSet resultSet;
        int limitAge = 0;
        boolean ok;
        Scanner sc = new Scanner(System.in);
        String selectEj1 = "Select Name, sum(Points) as points from Drivers " +
                "inner join Results " +
                "on Drivers.DriverID = Results.DriverID " +
                "group by Name " +
                "order by points desc";
        String selectEj4 = """
                CREATE TABLE IF NOT EXISTS Teams (
                Constructor TEXT NOT NULL,
                Chasis TEXT NOT NULL,
                PowerUnit TEXT NOT NULL,
                PRIMARY KEY ( Constructor )
                );""";

        try
        {
            connection = SQLHandler.conectarBD("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, selectEj1);
            resultSet = SQLHandler.executeQuery(sentence);
            //mostrarClasificacionFinal(resultSet);

            sentence.close();
            resultSet.close();
            connection.close();

            /*do {
                try {
                    System.out.print("Dime el limite de edad minima de los pilotos a mostrar: ");
                    limitAge = sc.nextInt();
                    ok = true;
                } catch (InputMismatchException mismatchException) {
                    ok = false;
                    System.out.println("Escribe un numero");
                }
            }while (!ok);

            String selectEj2 = "Select Name, " +
                    "cast((strftime('%Y', 'now') - strftime('%Y', DateOfBirth)) - (strftime('%m-%d', 'now') < strftime('%m-%d', DateOfBirth) )as int) AS age " +
                    "from Drivers " +
                    "where age >= " + limitAge + " "+
                    "order by age desc";

            connection = SQLHandler.conectarBD("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, selectEj2);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarPilotos30AnyosOMas(resultSet);

            sentence.close();
            resultSet.close();
            connection.close();*/

            connection = SQLHandler.conectarBD("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, selectEj4);
            resultSet = SQLHandler.executeQuery(sentence);

            System.out.println(resultSet.getString(1));

            sentence.close();
            resultSet.close();
            connection.close();

            String insertValues = SQLHandler.insertValues();
            connection = SQLHandler.conectarBD("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, insertValues);
            resultSet = SQLHandler.executeQuery(sentence);

            System.out.println(resultSet.getString(1));

            sentence.close();
            resultSet.close();
            connection.close();

            String selectTest = "SELECT Constructor, Chasis, PowerUnit FROM Teams ORDER BY Constructor";



        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public static void mostrarClasificacionFinal(ResultSet resultSet) {
        try {
            System.out.println("Nombre\tPuntos");
            System.out.println("--------------");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + "\t" + resultSet.getString("points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mostrarPilotos30AnyosOMas(ResultSet resultSet) {
        try {
            System.out.println("Nombre\tEdad");
            System.out.println("-----------");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + "\t" + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
