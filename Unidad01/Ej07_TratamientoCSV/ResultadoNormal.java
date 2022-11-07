package Ej07_TratamientoCSV;

public class ResultadoNormal extends Resultado {
    private boolean extraPoint;
    private String fastestLap;

    public ResultadoNormal(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, double points, boolean extraPoint, String fastestLap) {
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


    public static Resultado crearCarrera(String line) {
        String[] linea = line.split(",");
        int pos;
        if (linea[1].equals("NC")) pos = Resultado.NC;
        else if (linea[1].equals("DQ")) pos = Resultado.DQ;
        else pos = Integer.parseInt(linea[1]);

        return new ResultadoNormal(
                linea[0],
                pos,
                Integer.parseInt(linea[2]),
                linea[3],
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
