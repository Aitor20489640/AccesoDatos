package Ej07_TratamientoCSV;

public class CarreraSprint extends Carrera{

    public CarreraSprint(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, int points) {
        super(track, position, no, driver, team, startingGrid, laps, time, points);
    }

    @Override
    public String toString() {
        return "CarreraSprint{" +
                "NC=" + NC +
                ", DQ=" + DQ +
                ", track='" + track + '\'' +
                ", position=" + position +
                ", no=" + no +
                ", driver='" + driver + '\'' +
                ", team='" + team + '\'' +
                ", startingGrid=" + startingGrid +
                ", laps=" + laps +
                ", time='" + time + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public Carrera crearCarrera(String[] linea) {
        return null;
    }
}
