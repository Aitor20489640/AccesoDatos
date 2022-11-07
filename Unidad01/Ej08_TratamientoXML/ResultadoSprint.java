package Ej08_TratamientoXML;

import java.util.List;

public class ResultadoSprint extends Resultado {

    public ResultadoSprint(Carrera track, int position, int no, String driver, String team, int startingGrid, int laps, String time, double points) {
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



    public static Resultado crearCarrera(String line, List<Carrera> listCarrera) {
        String[] linea = line.split(",");
        int pos;
        if (linea[1].equals("NC")) pos = Resultado.NC;
        else if (linea[1].equals("DQ")) pos = Resultado.DQ;
        else pos = Integer.parseInt(linea[1]);
        Carrera carrera = null;
        for (Carrera carrera1 : listCarrera) {
            if (linea[0].equals(carrera1.getGpName())){
                carrera = carrera1;
            }
        }


        return new ResultadoSprint(
                carrera,
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
