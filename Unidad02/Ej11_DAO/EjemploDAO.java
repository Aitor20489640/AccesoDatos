package Ej11_DAO;

import Ej11_DAO.dao.sql.SQLiteDAOManagerImpl;
import Ej11_DAO.models.Circuito;
import Ej11_DAO.models.Escuderia;
import Ej11_DAO.models.Piloto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EjemploDAO {
    public static void main(String[] args) {

        SQLiteDAOManagerImpl mundial = new SQLiteDAOManagerImpl();

        System.out.println("\nMostrar todas");
        System.out.println("-------------------------------");
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo una");
        System.out.println("-------------------------------");
        System.out.println(mundial.getEscuderiaDAO().findById("Mercedes"));

        System.out.println("\nInsertar una escudería");
        System.out.println("-------------------------------");
        Escuderia seat = new Escuderia("Seat", "Seat-2019", "Seat 4 Latas");
        mundial.getEscuderiaDAO().save(seat);
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar una escudería");
        System.out.println("-------------------------------");
        seat.setChasis("Seat-2023");
        seat.setMotor("Seat TDI 2.0");
        mundial.getEscuderiaDAO().update(seat);
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar una escudería");
        System.out.println("-------------------------------");
        mundial.getEscuderiaDAO().deleteById("Seat");
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar todos");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo uno");
        System.out.println("-------------------------------");
        System.out.println(mundial.getPilotoDAO().findById(44));

        System.out.println("\nInsertar un piloto");
        System.out.println("-------------------------------");
        Piloto piloto = new Piloto("Numero Gracioso",69, mundial.getEscuderiaDAO().findById("Mercedes"), LocalDate.parse("2001-10-29", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mundial.getPilotoDAO().save(piloto);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un piloto");
        System.out.println("-------------------------------");
        piloto.setEquipo(mundial.getEscuderiaDAO().findById("Red Bull Racing"));
        piloto.setNombre("Im cool");
        mundial.getPilotoDAO().update(piloto);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un piloto");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().deleteById(69);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar todos");
        System.out.println("-------------------------------");
        mundial.getCircuitoDao().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo uno");
        System.out.println("-------------------------------");
        System.out.println(mundial.getCircuitoDao().findById(5));

        System.out.println("\nInsertar un piloto");
        System.out.println("-------------------------------");
        Circuito circuito = new Circuito(22, "España", LocalDate.parse("2001-10-29", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mundial.getCircuitoDao().save(circuito);
        mundial.getCircuitoDao().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un piloto");
        System.out.println("-------------------------------");
        circuito.setPais("Madrid");
        mundial.getCircuitoDao().update(circuito);
        mundial.getCircuitoDao().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un piloto");
        System.out.println("-------------------------------");
        mundial.getCircuitoDao().deleteById(22);
        mundial.getCircuitoDao().findAll().forEach(System.out::println);
    }
}
