package Ej11_DAO.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Circuito {
    private int ronda;
    private String pais;
    private LocalDate fechaCarrera;

    public Circuito(int ronda, String pais, LocalDate fechaCarrera) {
        this.ronda = ronda;
        this.pais = pais;
        this.fechaCarrera = fechaCarrera;
    }

    //Getters
    public int getRonda() {
        return ronda;
    }

    public String getPais() {
        return pais;
    }

    public String  getFechaCarreraString() {
        return LocalDateTime.of(fechaCarrera, LocalTime.of(0, 0, 0)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
    //Setters
    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setFechaCarrera(LocalDate fechaCarrera) {
        this.fechaCarrera = fechaCarrera;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "round=" + ronda +
                ", ciudad='" + pais + '\'' +
                ", fechaCarrera=" + fechaCarrera.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                "'}";
    }
}
