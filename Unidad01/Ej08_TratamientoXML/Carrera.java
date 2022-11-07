package Ej08_TratamientoXML;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carrera {
    @XmlAttribute(name = "round")
    private int round;
    @XmlElement(name = "country")
    private String country;
    @XmlElement(name = "city")
    private String city;
    @XmlElement(name = "circuitname")
    private String circuitName;
    @XmlElement(name = "gpname")
    private String gpName;
    @XmlElement(name = "racedate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date raceDate;
    @XmlElement(name = "firstgp")
    private int firstGp;
    @XmlElement(name = "numberoflaps")
    private int numLaps;
    @XmlElement(name = "circuitlength")
    private double circuitLength;
    @XmlElement(name = "racedistance")
    private double raceDistance;
    @XmlElement(name = "laprecord")
    private String lapRecord;
    @XmlElement(name = "recordowner")
    private String recordOwner;
    @XmlElement(name = "recordyear")
    private int recordYear;
    @XmlElement(name = "turns")
    private int turns;
    @XmlElement(name = "drszones")
    private int drsZones;

    public Carrera() {

    }

    public Carrera(int round, String country, String city, String circuitName, String gpName, Date raceDate, int firstGp, int numLaps, double circuitLength, double raceDistance, String lapRecord, String recordOwner, int recordYear, int turns, int drsZones) {
        this.round = round;
        this.country = country;
        this.city = city;
        this.circuitName = circuitName;
        this.gpName = gpName;
        this.raceDate = raceDate;
        this.firstGp = firstGp;
        this.numLaps = numLaps;
        this.circuitLength = circuitLength;
        this.raceDistance = raceDistance;
        this.lapRecord = lapRecord;
        this.recordOwner = recordOwner;
        this.recordYear = recordYear;
        this.turns = turns;
        this.drsZones = drsZones;
    }

    public Carrera(String gpName) {
        this.gpName = gpName;
    }


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getRaceDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
        return dateFormat.format(raceDate);
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public int getFirstGp() {
        return firstGp;
    }

    public void setFirstGp(int firstGp) {
        this.firstGp = firstGp;
    }

    public int getnumLaps() {
        return numLaps;
    }

    public void setnumLaps(int numLaps) {
        this.numLaps = numLaps;
    }

    public double getCircuitLength() {
        return circuitLength;
    }

    public void setCircuitLength(double circuitLength) {
        this.circuitLength = circuitLength;
    }

    public double getRaceDistance() {
        return raceDistance;
    }

    public void setRaceDistance(double raceDistance) {
        this.raceDistance = raceDistance;
    }

    public String getLapRecord() {
        return lapRecord;
    }

    public void setLapRecord(String lapRecord) {
        this.lapRecord = lapRecord;
    }

    public String getRecordOwner() {
        return recordOwner;
    }

    public void setRecordOwner(String recordOwner) {
        this.recordOwner = recordOwner;
    }

    public int getRecordYear() {
        return recordYear;
    }

    public void setRecordYear(int recordYear) {
        this.recordYear = recordYear;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getDrsZones() {
        return drsZones;
    }

    public void setDrsZones(int drsZones) {
        this.drsZones = drsZones;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "round=" + round +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", circuitName='" + circuitName + '\'' +
                ", gpName='" + gpName + '\'' +
                ", raceDate=" + getRaceDate() +
                ", firstGp=" + firstGp +
                ", numLaps=" + numLaps +
                ", circuitLength=" + circuitLength +
                ", raceDistance=" + raceDistance +
                ", lapRecord='" + lapRecord + '\'' +
                ", recordOwner='" + recordOwner + '\'' +
                ", recordYear=" + recordYear +
                ", turns=" + turns +
                ", drsZones=" + drsZones +
                '}';
    }
}
