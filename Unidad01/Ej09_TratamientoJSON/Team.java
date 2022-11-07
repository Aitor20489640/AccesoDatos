package Ej09_TratamientoJSON;

public class Team {
    private String team;
    private String fullTeamName;
    private String baseCity;
    private String baseCountry;
    private String teamChief;
    private String technicalChief;
    private String chassis;
    private String powerUnit;
    private int firstTeamEntry;
    private int worldChampionships;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFullTeamName() {
        return fullTeamName;
    }

    public void setFullTeamName(String fullTeamName) {
        this.fullTeamName = fullTeamName;
    }

    public String getBaseCity() {
        return baseCity;
    }

    public void setBaseCity(String baseCity) {
        this.baseCity = baseCity;
    }

    public String getBaseCountry() {
        return baseCountry;
    }

    public void setBaseCountry(String baseCountry) {
        this.baseCountry = baseCountry;
    }

    public String getTeamChief() {
        return teamChief;
    }

    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    public String getTechnicalChief() {
        return technicalChief;
    }

    public void setTechnicalChief(String technicalChief) {
        this.technicalChief = technicalChief;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        this.powerUnit = powerUnit;
    }

    public int getFirstTeamEntry() {
        return firstTeamEntry;
    }

    public void setFirstTeamEntry(int firstTeamEntry) {
        this.firstTeamEntry = firstTeamEntry;
    }

    public int getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(int worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team='" + team + '\'' +
                ", fullTeamName='" + fullTeamName + '\'' +
                ", baseCity='" + baseCity + '\'' +
                ", baseCountry='" + baseCountry + '\'' +
                ", teamChief='" + teamChief + '\'' +
                ", technicalChief='" + technicalChief + '\'' +
                ", chassis='" + chassis + '\'' +
                ", powerUnit='" + powerUnit + '\'' +
                ", firstTeamEntry=" + firstTeamEntry +
                ", worldChampionships=" + worldChampionships +
                '}';
    }
}
