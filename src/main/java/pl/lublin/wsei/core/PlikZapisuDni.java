package pl.lublin.wsei.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.lublin.wsei.klasy.Dzien;
import pl.lublin.wsei.klasy.Profil;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class PlikZapisuDni{
    //Sprawdzane przez interfejs
    private static final long serialVersionUID = 1L;
    private static final String sciezkaPliku = "zapisDni.xml";
    private Profil profil;
    private LinkedList<Dzien> listaDni;
    // transient - to NIE będzie serializowane
    private transient boolean initialized;


    PlikZapisuDni() {
        listaDni = new LinkedList<>();
    }


    public LinkedList<Dzien> getListaDni() {
        return listaDni;
    }

    public void dodajDzien(Dzien dzien) {
        listaDni.add(dzien);
    }

    public void dodajDni(List<Dzien> dni) {
        listaDni.addAll(dni);
    }
    public void dodajDni(Dzien[] dni) {
        Collections.addAll(listaDni,dni);
    }


    public void zapiszPlik () throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("plik_zapisu.xml"),this);

    }
    public static PlikZapisuDni odczytajPlik() throws FileNotFoundException {
        File file = new File("plik_zapisu.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        try {

            return xmlMapper.readValue(xml, PlikZapisuDni.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }

    private static String inputStreamToString(FileInputStream fileInputStream) {

            String text = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            return text;

    }


}
