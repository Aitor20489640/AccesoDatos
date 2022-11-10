package Ej09_TratamientoJSON;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CustomTeamDeserializer extends StdDeserializer<Driver> {

    private List<Team> escuderias;

    public CustomTeamDeserializer(Class<?> vc, List<Team> escuderias) {
        super(vc);
        this.escuderias = escuderias;
    }

    @Override
    public Driver deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Driver driver = new Driver();
        String date;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        JsonNode driverNode, abbNode, numberNode, teamNode, countryNode, worldChampNode, dateBirthNode, placeBirthNode;

        driverNode = node.get("driver");
        driver.setDriver(driverNode.asText());

        abbNode = node.get("abbreviation");
        driver.setAbbreviation(abbNode.asText());

        numberNode = node.get("number");
        driver.setNumber(numberNode.asInt());

        teamNode = node.get("team");
        String team = teamNode.asText();

        for (Team equipo : escuderias) {
            if (equipo.getFullTeamName().contains(team)) {
                driver.setTeam(equipo);
            }
        }

        countryNode = node.get("country");
        driver.setCountry(countryNode.asText());

        worldChampNode = node.get("worldChampionships");
        driver.setWorldChampionships(worldChampNode.asInt());

        dateBirthNode = node.get("dateBirth");
        date = dateBirthNode.asText();
        driver.setDateBirth(LocalDate.parse(date, format));

        placeBirthNode = node.get("placeBirth");
        driver.setPlaceBirth(placeBirthNode.asText());



        return driver;
    }


}
