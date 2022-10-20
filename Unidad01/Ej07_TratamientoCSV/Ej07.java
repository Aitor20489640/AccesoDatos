package Ej07_TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;
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

        System.out.println("-".repeat(20)+"Piloto Campeon"+"-".repeat(20));

        Map<List<String>, Double> mapDriver = allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver()),
                        Collectors.summingDouble(Carrera::getPoints)
                ));

        mapDriver.entrySet()
                .stream()
                .map(e -> new Driver(e.getKey().get(0), e.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);


        System.out.println("-".repeat(20)+"Equipo Campeon"+"-".repeat(20));

        Map<List<String>, Double> mapTeam = allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(Carrera::getPoints)
                ));

        mapTeam.entrySet()
                .stream()
                .map(t -> new Driver(t.getKey().get(0), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Piloto con mas Victorias"+"-".repeat(15));

        Map<String, Long> countVictoriesD = raceResults.stream().filter(d -> d.getPosition() == 1)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()));

        countVictoriesD.entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Equipo con mas Victorias"+"-".repeat(15));

        Map<String, Long> countVictoriesT = raceResults.stream().filter(t -> t.getPosition() == 1)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()));

        countVictoriesT.entrySet()
                .stream()
                .map(t -> new Driver(t.getKey(), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);


        System.out.println("-".repeat(15)+"Piloto con mas Podios"+"-".repeat(15));

        Map<String, Long> countPodiumD = raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()));

        countPodiumD.entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Equipo con mas Podios"+"-".repeat(15));

        Map<String, Long> countPodiumT = raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()));

        countPodiumT.entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Piloto con mas Poles"+"-".repeat(15));

        Map<String, Long> countPolesD = raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()));

        countPolesD.entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Equipo con mas Poles"+"-".repeat(15));

        Map<String, Long> countPolesT = raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()));

        countPolesT.entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Piloto con mas Vueltas Rapidas"+"-".repeat(15));

        List<CarreraNormal> raceResultsLaps = new ArrayList<>((Collection) raceResults);










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
            return "nombre='" + nombre + '\'' +
                    ", value=" + value;
        }
    }

    private static class FastestLap{
        protected String nombre;
        protected LocalTime lap;

        public FastestLap(String nombre, String lap) {
            this.nombre = nombre;
            this.lap = LocalTime.parse(lap);
        }

        @Override
        public String toString() {
            return "nombre='" + nombre + '\'' +
                    ", lap='" + lap;
        }
        public boolean compare(LocalTime t1, LocalTime t2) {
            return t1.isBefore(t2);
        }
    }


}
