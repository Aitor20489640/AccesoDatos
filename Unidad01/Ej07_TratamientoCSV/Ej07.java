package Ej07_TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej07 {
    public static void main(String[] args) {
        List<Carrera> raceResults = new ArrayList<>();
        List<Carrera> sprintResults = new ArrayList<>();
        ArrayList<Carrera> allRacesResults = new ArrayList<>();
        Path pathRace = Path.of("Unidad01/Ej07_TratamientoCSV/formula1_2021season_raceResults.csv");
        Path pathSprint = Path.of("Unidad01/Ej07_TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");

        try (Stream<String> lineas = Files.lines(pathRace).skip(1)){
            raceResults = lineas.map(CarreraNormal::crearCarrera).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (Stream<String> lineas = Files.lines(pathSprint).skip(1)){
            sprintResults = lineas.map(CarreraSprint::crearCarrera).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        allRacesResults.addAll(raceResults);
        allRacesResults.addAll(sprintResults);

        Map<List<String>, Double> mapDriver = allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver()),
                        Collectors.summingDouble(Carrera::getPoints)
                ));

        List<Driver> results = mapDriver.entrySet()
                .stream()
                .map(e -> new Driver(e.getKey().get(0), e.getValue())).toList();

        List<Driver> champion = results.stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).toList();

        champion.forEach(System.out::println);

        System.out.println("-".repeat(20));

        Map<List<String>, Double> mapTeam = allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(Carrera::getPoints)
                ));

        List<Driver> resultsTeam = mapTeam.entrySet()
                .stream()
                .map(t -> new Driver(t.getKey().get(0), t.getValue())).toList();

        List<Driver> championTeam = resultsTeam.stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).toList();

        championTeam.forEach(System.out::println);








    }

    private static class Driver {
        protected String nombre;
        protected double value;

        public Driver (String nombre, double value) {
            this.nombre = nombre;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Driver{" +
                    "nombre='" + nombre + '\'' +
                    ", value=" + value +
                    '}';
        }
    }


}
