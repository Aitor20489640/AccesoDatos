package Ej09_TratamientoJSON;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;

@XmlRootElement(name = "calendar")
public class Carreras {
    @XmlElement(name = "race")
    private ArrayList<Carrera> carreras = new ArrayList<>();

    public Carreras() {
    }

    @XmlTransient
    public ArrayList<Carrera> getCircuitos() {
        return carreras;
    }

    public void setCircuitos(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

}
