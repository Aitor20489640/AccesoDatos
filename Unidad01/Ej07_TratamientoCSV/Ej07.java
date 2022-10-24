package Ej07_TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej07 {
    public static void main(String[] args) {
        List<CarreraNormal> raceResults = new ArrayList<>();
        List<CarreraSprint> sprintResults = new ArrayList<>();
        ArrayList<Carrera> allRacesResults = new ArrayList<>();
        Path pathRace = Path.of("Unidad01/Ej07_TratamientoCSV/formula1_2021season_raceResults.csv");
        Path pathSprint = Path.of("Unidad01/Ej07_TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");

        try (Stream<String> lineas = Files.lines(pathRace).skip(1)){
            raceResults = lineas.map(line -> (CarreraNormal) CarreraNormal.crearCarrera(line)).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (Stream<String> lineas = Files.lines(pathSprint).skip(1)){
            sprintResults = lineas.map(line -> (CarreraSprint) CarreraSprint.crearCarrera(line)).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        allRacesResults.addAll(raceResults);
        allRacesResults.addAll(sprintResults);

        System.out.println("-".repeat(20)+"Piloto Campeon"+"-".repeat(20));

        allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver()),
                        Collectors.summingDouble(Carrera::getPoints)))
                .entrySet()
                .stream()
                .map(e -> new Driver(e.getKey().get(0), e.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);




        System.out.println("-".repeat(20)+"Equipo Campeon"+"-".repeat(20));

        allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(Carrera::getPoints)))
                .entrySet()
                .stream()
                .map(t -> new Driver(t.getKey().get(0), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Piloto con mas Victorias"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() == 1)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Equipo con mas Victorias"+"-".repeat(15));

        raceResults.stream().filter(t -> t.getPosition() == 1)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(t -> new Driver(t.getKey(), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);




        System.out.println("-".repeat(15)+"Piloto con mas Podios"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Equipo con mas Podios"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Piloto con mas Poles"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);


        System.out.println("-".repeat(15)+"Equipo con mas Poles"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(Carrera::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Piloto con mas Vueltas Rapidas"+"-".repeat(15));

        System.out.println("TO-DO");

        System.out.println("-".repeat(15)+"Piloto con mas Abandonos"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() == Carrera.NC)
                .collect(Collectors.groupingBy(Carrera::getDriver, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new Driver(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Cara a Cara en clasificación"+"-".repeat(15));

        System.out.println("TO-DO");

        System.out.println("-".repeat(15)+"Cara a Cara en carrera"+"-".repeat(15));

        System.out.println("TO-DO");

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
        protected String track;
        protected String nombre;
        protected int[] lap;

        public FastestLap(String track, String nombre, String lap) {
            this.track = track;
            this.nombre = nombre;
            if (lap.equals("N/A")){
                this.lap = null;
            }else {
                this.lap = formatLaps(lap);
            }

        }

        @Override
        public String toString() {
            return "track='" + track + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", lap=" + Arrays.toString(lap);
        }

        public boolean compare(int[] t1, int[] t2) {
            return Arrays.compare(t1, t2) == -1;
        }

        public static int[] formatLaps(String lap) {
            int minutes = Integer.parseInt(lap.split(":")[0]);
            int seconds = Integer.parseInt(lap.split(":")[1].split("\\.")[0]);
            int miliSeconds = Integer.parseInt(lap.split(":")[1].split("\\.")[1]);
            return new int[]{minutes, seconds, miliSeconds};
        }
    }


}
