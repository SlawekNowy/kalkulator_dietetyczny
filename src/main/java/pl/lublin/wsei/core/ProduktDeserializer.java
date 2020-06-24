package pl.lublin.wsei.core;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lublin.wsei.klasy.Produkt;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class ProduktDeserializer extends KeyDeserializer {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        Reader reader = new StringReader(key);
        return mapper.readValue(reader, Produkt.class);
    }
}
