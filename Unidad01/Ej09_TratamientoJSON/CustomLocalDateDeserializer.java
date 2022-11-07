package Ej09_TratamientoJSON;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CustomLocalDateDeserializer() {
        this(null);
    }

    public CustomLocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        try {
            return LocalDate.parse(date, format);
        } catch (DateTimeException e) {
            throw new RuntimeException(e);
        }
    }
}
