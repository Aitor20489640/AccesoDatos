package Ej09_TratamientoJSON;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej09 {
    public static void main(String[] args) {
        List<ResultadoNormal> raceResults = new ArrayList<>();
        List<ResultadoSprint> sprintResults = new ArrayList<>();
        ArrayList<Resultado> allRacesResults = new ArrayList<>();
        List<Carrera> allRaces = new ArrayList<>();
        List<Driver> allDrivers = new ArrayList<>();
        List<Team> allTeams = new ArrayList<>();
        Path pathRace = Path.of("Unidad01/Ej09_TratamientoJSON/formula1_2021season_raceResults.csv");
        Path pathSprint = Path.of("Unidad01/Ej09_TratamientoJSON/formula1_2021season_sprintQualifyingResults.csv");
        Path pathCircuit = Path.of("Unidad01/Ej09_TratamientoJSON/formula1_2021season_calendar.xml");
        Path pathDrivers = Path.of("Unidad01/Ej09_TratamientoJSON/formula1_2021season_drivers.json");
        Path pathTeams = Path.of("Unidad01/Ej09_TratamientoJSON/formula1_2021season_teams.json");
        Carreras todosCarreras;


        //Tratamiento json
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            allTeams = objectMapper.readValue(pathTeams.toFile(), new TypeReference<List<Team>>(){});

            SimpleModule module = new SimpleModule("CustomTeamDeserializer", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(Driver.class, new CustomTeamDeserializer(Driver.class, allTeams));
            objectMapper.registerModule(module);
            allDrivers = objectMapper.readValue(pathDrivers.toFile(), new TypeReference<List<Driver>>(){});
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Fin del tratamiento json
        //Tratamiento xml
        try {
            todosCarreras = unmarshall(pathCircuit);
            allRaces = todosCarreras.getCircuitos();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //Fin del tratamiento xml
        //Tratamiento csv
        try (Stream<String> lineas = Files.lines(pathRace).skip(1)){
            List<Carrera> finalAllRaces = allRaces;
            List<Driver> finalAllDrivers = allDrivers;
            raceResults = lineas.map(line -> (ResultadoNormal) ResultadoNormal.crearCarrera(line, finalAllRaces, finalAllDrivers)).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (Stream<String> lineas = Files.lines(pathSprint).skip(1)){
            List<Carrera> finalAllRacesSprint = allRaces;
            List<Driver> finalAllDrivers1 = allDrivers;
            sprintResults = lineas.map(line -> (ResultadoSprint) ResultadoSprint.crearCarrera(line, finalAllRacesSprint, finalAllDrivers1)).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        allRacesResults.addAll(raceResults);
        allRacesResults.addAll(sprintResults);

        //Ejercicios csv

        System.out.println("-".repeat(20)+"Piloto Campeon"+"-".repeat(20));

        allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver().getDriver()),
                        Collectors.summingDouble(Resultado::getPoints)))
                .entrySet()
                .stream()
                .map(e -> new ShowingClass(e.getKey().get(0), e.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);




        System.out.println("-".repeat(20)+"Equipo Campeon"+"-".repeat(20));

        allRacesResults.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(Resultado::getPoints)))
                .entrySet()
                .stream()
                .map(t -> new ShowingClass(t.getKey().get(0), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Piloto con mas Victorias"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() == 1)
                .collect(Collectors.groupingBy(resultadoNormal -> resultadoNormal.getDriver().getDriver(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Equipo con mas Victorias"+"-".repeat(15));

        raceResults.stream().filter(t -> t.getPosition() == 1)
                .collect(Collectors.groupingBy(Resultado::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(t -> new ShowingClass(t.getKey(), t.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);


        System.out.println("-".repeat(15)+"Piloto con mas Podios"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(resultadoNormal -> resultadoNormal.getDriver().getDriver(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Equipo con mas Podios"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() >= 1 && d.getPosition() <= 3)
                .collect(Collectors.groupingBy(Resultado::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);



        System.out.println("-".repeat(15)+"Piloto con mas Poles"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(resultadoNormal -> resultadoNormal.getDriver().getDriver(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);


        System.out.println("-".repeat(15)+"Equipo con mas Poles"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(Resultado::getTeam, Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Piloto con mas Vueltas Rapidas"+"-".repeat(15));

        System.out.println("TO-DO");

        System.out.println("-".repeat(15)+"Piloto con mas Abandonos"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() == Resultado.NC)
                .collect(Collectors.groupingBy(resultadoNormal -> resultadoNormal.getDriver().getDriver(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(d -> new ShowingClass(d.getKey(), d.getValue())).toList()
                .stream().sorted((d1, d2) -> Double.compare(d2.value, d1.value)).limit(1).toList()
                .forEach(System.out::println);

        System.out.println("-".repeat(15)+"Cara a Cara en clasificación"+"-".repeat(15));

        System.out.println("TO-DO");

        System.out.println("-".repeat(15)+"Cara a Cara en carrera"+"-".repeat(15));

        System.out.println("TO-DO");

        //Ejercicios xml

        System.out.println("-".repeat(15)+"Lista ordenada con cada gran premio y su ganador"+"-".repeat(15));

        raceResults.stream().filter(d -> d.getPosition() == 1)
                .sorted(Comparator.comparingInt(d -> d.getTrack().getRound()))
                .forEach(p -> System.out.println("Ronda: " + p.getTrack().getRound() + " - Gran premio: " + p.getTrack().getGpName() + " - Ganador: " + p.getDriver().getDriver()));

        System.out.println("-".repeat(15)+"listado del número de grandes premios celebrados por país"+"-".repeat(15));

        allRaces.stream().collect(Collectors.groupingBy(Carrera::getCountry, Collectors.counting()))
                .entrySet()
                .stream()
                .map(c -> new ShowingClass(c.getKey(), c.getValue()))
                .forEach(System.out::println);

        //Ejercicios JSON

        System.out.println("-".repeat(15)+"Los pilotos de la competición que son, o han sido, campeones del mundo"+"-".repeat(15));

        allDrivers.stream().filter(f -> f.getWorldChampionships() >= 1).forEach(s -> System.out.println("Pilotos: " +s.getDriver() + " - campeonatos: " + s.getWorldChampionships()));

        System.out.println("-".repeat(15)+"Escuderías con pilotos de su país"+"-".repeat(15));

        List<String> listTeamDriverSameCountry = new ArrayList<>();
        Map<String, String> hashMapTeamCountry = allTeams.stream().collect(Collectors.toMap(Team::getTeam, Team::getBaseCountry));
        boolean ok;
        for (Map.Entry<String, String> e : hashMapTeamCountry.entrySet()) {
            ok = true;
            for (int i = 0; i < allDrivers.size() && ok; i++) {
                if (allDrivers.get(i).getTeam().getTeam().equalsIgnoreCase(e.getKey())) {
                    if (allDrivers.get(i).getCountry().equalsIgnoreCase(e.getValue())) {
                        listTeamDriverSameCountry.add(e.getKey());
                        ok = false;
                    }
                }
            }
        }
        listTeamDriverSameCountry.stream().distinct().forEach(System.out::println);

        System.out.println("-".repeat(15)+"Lista de suministradores de motores y a qué escuderías suministran"+"-".repeat(15));

        List<String> listSupplierTeams = allTeams.stream().map(Team::getPowerUnit).distinct().toList();
        for (String powerUnit : listSupplierTeams) {
            System.out.print(powerUnit + ": ");
            allTeams.stream().filter(p -> p.getPowerUnit().equalsIgnoreCase(powerUnit)).forEach(p -> System.out.print(p.getTeam() + ";"));
            System.out.println();
        }

    }

    public static Carreras unmarshall(Path ruta) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Carreras.class);
        return (Carreras) context.createUnmarshaller()
                .unmarshal(ruta.toFile());
    }


    private static class ShowingClass {
        protected String nombre;
        protected double value;

        public ShowingClass(String nombre, double value) {
            this.nombre = nombre;
            this.value = value;
        }

        @Override
        public String toString() {
            return "nombre='" + nombre + '\'' +
                    ", value=" + value;
        }
    }

    private static class FastestLap {
        protected String track;
        protected String nombre;
        protected int[] lap;

        public FastestLap(String track, String nombre, String lap) {
            this.track = track;
            this.nombre = nombre;
            if (lap.equals("N/A")) {
                this.lap = null;
            } else {
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
