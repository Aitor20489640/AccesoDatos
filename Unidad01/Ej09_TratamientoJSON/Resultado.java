package Ej09_TratamientoJSON;

public class Resultado {
    protected static final int NC = -1;//Non-classified finish
    protected static final int DQ = 0;//Driver disqualified
    protected Carrera track;
    protected int position;

    protected int no;
    protected Driver driver;
    protected String team;
    protected int startingGrid;
    protected int laps;
    protected String time;
    protected double points;

    public Resultado(Carrera track, int position, int no, Driver driver, String team, int startingGrid, int laps, String time, double points) {
        this.track = track;
        this.position = position;
        this.no = no;
        this.driver = driver;
        this.team = team;
        this.startingGrid = startingGrid;
        this.laps = laps;
        this.time = time;
        this.points = points;
    }

    public Carrera getTrack() {
        return track;
    }

    public void setTrack(Carrera track) {
        this.track = track;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getStartingGrid() {
        return startingGrid;
    }

    public void setStartingGrid(int startingGrid) {
        this.startingGrid = startingGrid;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getNC() {
        return NC;
    }

    public int getDQ() {
        return DQ;
    }
}
