package Ej10_JDBC_y_SQLite;

import java.nio.file.Path;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of("Unidad02/Ej10_JDBC_y_SQLite/formula1.db");
        String host = "f12019.ci66saah1axn.us-east-1.rds.amazonaws.com:3306";
        String bbdd = "f1_2019";
        String usuario = "admin";
        String passwd = "aadd1234";
        Connection connection;
        PreparedStatement sentence;
        ResultSet resultSet;
        int limitAge = 0;
        boolean ok;
        Scanner sc = new Scanner(System.in);
        String selectEj1 = """
                Select Name, sum(Points) as points from Drivers
                inner join Results
                on Drivers.DriverID = Results.DriverID
                group by Name
                order by points desc""";

        String selectEj4 = """
                CREATE TABLE IF NOT EXISTS Teams (
                Constructor TEXT NOT NULL,
                Chasis TEXT NOT NULL,
                PowerUnit TEXT NOT NULL,
                PRIMARY KEY ( Constructor )
                );""";

        try
        {
            connection = SQLHandler.conectarDBSQLITE("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, selectEj1);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarClasificacionFinal(resultSet);

            sentence.close();
            resultSet.close();

            String selectEj2 = "Select Name, " +
                    "cast((strftime('%Y', 'now') - strftime('%Y', DateOfBirth)) - (strftime('%m-%d', 'now') < strftime('%m-%d', DateOfBirth) )as int) AS age " +
                    "from Drivers " +
                    "where age >= ? "+
                    "order by age desc";

            sentence = SQLHandler.prepareStatement(connection, selectEj2);
            sentence.setInt(1, 30);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarPilotos30AnyosOMas(resultSet);

            sentence.close();
            resultSet.close();

            do {
                try {
                    System.out.print("Dime el limite de edad minima de los pilotos a mostrar: ");
                    limitAge = sc.nextInt();
                    ok = true;
                } catch (InputMismatchException mismatchException) {
                    ok = false;
                    System.out.println("Escribe un numero");
                }
            }while (!ok);

            sentence = SQLHandler.prepareStatement(connection, selectEj2);
            sentence.setInt(1, limitAge);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarPilotos30AnyosOMas(resultSet);

            sentence.close();
            resultSet.close();

            sentence = SQLHandler.prepareStatement(connection, selectEj4);
            SQLHandler.executeSentence(sentence);

            sentence.close();

            //String insertValues = SQLHandler.insertValues();
            String insertValues = """
                    INSERT INTO Teams VALUES
                    ('Mercedes','F1 W10 EQ Power+','Mercedes M10 EQ Power+'),
                    ('Red Bull Racing','RB15','Honda RA619H'),
                    ('Ferrari','SF90','Ferrari 064'),
                    ('McLaren','MCL34','Renault E-Tech 19'),
                    ('Toro Rosso','STR14','Honda RA619H'),
                    ('Renault','R.S.19','Renault E-Tech 19'),
                    ('Racing Point','RP19','BWT Mercedes'),
                    ('Alfa Romeo','C38','Ferrari 064'),
                    ('Haas','VF-19','Ferrari 064'),
                    ('Williams','FW42','Mercedes M10 EQ Power+');""";
            connection = SQLHandler.conectarDBSQLITE("jdbc:sqlite:" + ruta);
            sentence = SQLHandler.prepareStatement(connection, insertValues);
            SQLHandler.executeSentence(sentence);

            sentence.close();

            String selectTest = "SELECT Constructor, Chasis, PowerUnit FROM Teams ORDER BY Constructor";

            sentence = SQLHandler.prepareStatement(connection, selectTest);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarValoresInsertadosEnTeams(resultSet);

            sentence.close();
            resultSet.close();

            System.out.println("---------------------------------------MariaDB------------------------------------------------");

            connection = SQLHandler.conectarDBMARIADB("jdbc:mariadb://" + host + "/" + bbdd, usuario, passwd);
            sentence = SQLHandler.prepareStatement(connection, selectEj1);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarClasificacionFinal(resultSet);

            sentence.close();
            resultSet.close();

            String selectEj2MariaDb = """
                    Select Name,
                    TIMESTAMPDIFF(YEAR, DateOfBirth, CURDATE())
                    from Drivers
                    where TIMESTAMPDIFF(YEAR, DateOfBirth, CURDATE()) >= ?
                    order by TIMESTAMPDIFF(YEAR, DateOfBirth, CURDATE()) desc""";

            sentence = SQLHandler.prepareStatement(connection, selectEj2MariaDb);
            sentence.setInt(1, 30);
            resultSet = SQLHandler.executeQuery(sentence);
            mostrarPilotos30AnyosOMasMariaDB(resultSet);

            sentence.close();
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public static void mostrarClasificacionFinal(ResultSet resultSet) {
        try {
            System.out.format("%20s%10s\n", "Nombre", "Puntos");
            System.out.println("--------------------------------------");

            while (resultSet.next()) {
                System.out.format("%20s%10d\n",resultSet.getString("name"), resultSet.getInt("points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mostrarPilotos30AnyosOMas(ResultSet resultSet) {
        try {
            System.out.format("%20s%10s\n", "Nombre","Edad");
            System.out.println("--------------------------------------");

            while (resultSet.next()) {
                System.out.format("%20s%10d\n",resultSet.getString("name"), resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarPilotos30AnyosOMasMariaDB(ResultSet resultSet) {
        try {
            System.out.format("%20s%10s\n", "Nombre","Edad");
            System.out.println("--------------------------------------");

            while (resultSet.next()) {
                System.out.format("%20s%10d\n",resultSet.getString("name"), resultSet.getInt("TIMESTAMPDIFF(YEAR, DateOfBirth, CURDATE())"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarValoresInsertadosEnTeams(ResultSet resultSet) {
        try {
            System.out.format("%15s%20s%25s\n","Constructor","Chasis","Power Unit");
            System.out.println("-------------------------------------------------------");
            while (resultSet.next()) {
                System.out.format("%15s%20s%25s\n", resultSet.getString("Constructor"), resultSet.getString("Chasis"), resultSet.getString("PowerUnit"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
