package Ej07_TratamientoCSV;

public abstract class Carrera {
    protected final int NC = -1;//Non-classified finish
    protected final int DQ = 0;//Driver disqualified
    protected String track;
    protected int position;
    protected int no;
    protected String driver;
    protected String team;
    protected int startingGrid;
    protected int laps;
    protected String time;
    protected int points;

    public Carrera(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, int points) {
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

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNC() {
        return NC;
    }

    public int getDQ() {
        return DQ;
    }

    public abstract Carrera crearCarrera(String[] linea);
}
