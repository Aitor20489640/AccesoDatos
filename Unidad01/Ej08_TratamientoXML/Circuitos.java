package Ej08_TratamientoXML;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;

@XmlRootElement(name = "calendar")
public class Circuitos {
    @XmlElement(name = "race")
    private ArrayList<Circuito> circuitos = new ArrayList<>();

    public Circuitos() {
    }

    @XmlTransient
    public ArrayList<Circuito> getCircuitos() {
        return circuitos;
    }

    public void setCircuitos(ArrayList<Circuito> circuitos) {
        this.circuitos = circuitos;
    }

}
