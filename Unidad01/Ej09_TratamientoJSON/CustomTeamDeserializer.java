package Ej09_TratamientoJSON;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CustomTeamDeserializer extends StdDeserializer<Team> {

    private List<Team> escuderias;

    public CustomTeamDeserializer(){
        this(null);
    }

    public CustomTeamDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomTeamDeserializer(Class<?> vc, List<Team> escuderias) {
        super(vc);
        this.escuderias = escuderias;
    }

    @Override
    public Team deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String team = jsonParser.getText();
        AtomicReference<Team> escuderia = null;

        escuderias.forEach(t -> {
            if (t.equals(team))
                escuderia.set(t);
        });

        return escuderia.get();
    }


}
