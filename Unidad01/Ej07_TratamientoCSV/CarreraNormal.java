package Ej07_TratamientoCSV;

public class CarreraNormal extends Carrera{
    private int extraPoint;
    private String fastestLap;

    public CarreraNormal(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, int points, int extraPoint, String fastestLap) {
        super(track, position, no, driver, team, startingGrid, laps, time, points);
        this.extraPoint = extraPoint;
        this.fastestLap = fastestLap;
    }

    public int getExtraPoint() {
        return extraPoint;
    }

    public void setExtraPoint(int extraPoint) {
        this.extraPoint = extraPoint;
    }

    public String getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(String fastestLap) {
        this.fastestLap = fastestLap;
    }

    @Override
    public String toString() {
        return "CarreraNormal{" +
                "extraPoint=" + extraPoint +
                ", fastestLap='" + fastestLap + '\'' +
                "} " + super.toString();
    }

    @Override
    public Carrera crearCarrera(String[] linea) {

        return null;
    }
}
