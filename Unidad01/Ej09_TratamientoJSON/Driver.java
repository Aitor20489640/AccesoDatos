package Ej09_TratamientoJSON;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Driver {
    private String driver;
    private String abbreviation;
    private int number;
    private Team team;
    private String country;
    private int worldChampionships;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dateBirth;
    private String placeBirth;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(int worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public String getDateBirth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("es", "ES"));
        return dateBirth.format(format);

    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driver='" + driver + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", number=" + number +
                ", team=" + team +
                ", country='" + country + '\'' +
                ", worldChampionships=" + worldChampionships +
                ", dateBirth=" + getDateBirth() +
                ", placeBirth='" + placeBirth + '\'' +
                '}';
    }
}
