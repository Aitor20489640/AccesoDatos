package Ej08_TratamientoXML;

import java.util.List;

public class CarreraSprint extends Carrera {

    public CarreraSprint(Circuito track, int position, int no, String driver, String team, int startingGrid, int laps, String time, double points) {
        super(track, position, no, driver, team, startingGrid, laps, time, points);
    }

    @Override
    public String toString() {
        return "CarreraSprint{" +
                "track='" + track + '\'' +
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



    public static Carrera crearCarrera(String line, List<Circuito> listCircuito) {
        String[] linea = line.split(",");
        int pos;
        if (linea[1].equals("NC")) pos = Carrera.NC;
        else if (linea[1].equals("DQ")) pos = Carrera.DQ;
        else pos = Integer.parseInt(linea[1]);
        Circuito circuito = null;
        for (Circuito circuito1 : listCircuito) {
            if (linea[0].equals(circuito1.getCountry())){
                circuito = circuito1;
            }
        }


        return new CarreraSprint(
                circuito,
                pos,
                Integer.parseInt(linea[2]),
                linea[3],
                linea[4],
                Integer.parseInt(linea[5]),
                Integer.parseInt(linea[6]),
                linea[7],
                Double.parseDouble(linea[8])
                );
    }
}
