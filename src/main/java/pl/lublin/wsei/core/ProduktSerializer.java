package pl.lublin.wsei.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.lublin.wsei.klasy.Produkt;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class ProduktSerializer extends JsonSerializer<Produkt> {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void serialize(Produkt value, JsonGenerator gen, SerializerProvider serializers) throws IOException {


        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.writeValueAsString(value);
    }
}
