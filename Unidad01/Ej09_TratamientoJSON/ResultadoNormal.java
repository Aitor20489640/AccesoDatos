package Ej09_TratamientoJSON;

import java.util.List;

public class ResultadoNormal extends Resultado {
    private boolean extraPoint;
    private String fastestLap;

    public ResultadoNormal(Carrera track, int position, int no, Driver driver, String team, int startingGrid, int laps, String time, double points, boolean extraPoint, String fastestLap) {
        super(track, position, no, driver, team, startingGrid, laps, time, points);
        this.extraPoint = extraPoint;
        this.fastestLap = fastestLap;
    }

    public boolean getExtraPoint() {
        return extraPoint;
    }

    public void setExtraPoint(boolean extraPoint) {
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
                "track='" + track + '\'' +
                ", position=" + position +
                ", no=" + no +
                ", driver='" + driver + '\'' +
                ", team='" + team + '\'' +
                ", startingGrid=" + startingGrid +
                ", laps=" + laps +
                ", time='" + time + '\'' +
                ", points=" + points +
                ", extraPoint=" + extraPoint + '\'' +
                ", fastestLap='" + fastestLap +
                '}';
    }


    public static Resultado crearCarrera(String line, List<Carrera> listCarrera, List<Driver> driverList) {
        String[] linea = line.split(",");
        int pos;
        if (linea[1].equals("NC")) pos = Resultado.NC;
        else if (linea[1].equals("DQ")) pos = Resultado.DQ;
        else pos = Integer.parseInt(linea[1]);
        Carrera carrera = null;
        Driver corredor = null;
        for (Carrera carrera1 : listCarrera) {
            if (linea[0].equals(carrera1.getGpName())){
                carrera = carrera1;
            }
        }
        for (Driver driverF : driverList) {
            if (linea[3].equals(driverF.getDriver())){
                corredor = driverF;
            }
        }


        return new ResultadoNormal(
                carrera,
                pos,
                Integer.parseInt(linea[2]),
                corredor,
                linea[4],
                Integer.parseInt(linea[5]),
                Integer.parseInt(linea[6]),
                linea[7],
                Double.parseDouble(linea[8]),
                (linea[9].equals("Yes")),
                linea[10]
        );
    }
}
