package pl.lublin.wsei.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pl.lublin.wsei.klasy.Dzien;
import pl.lublin.wsei.klasy.Profil;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class PlikZapisuDni{
    //Sprawdzane przez interfejs
    private static final long serialVersionUID = 1L;
    static final String sciezkaPliku = "zapisDni.json";
    private Profil profil;
    private final TreeSet<Dzien> listaDni;


    PlikZapisuDni() {
        listaDni = new TreeSet<>();
    }


    /*
     * UNSAFE AF!!!: możliwa bezpośrenia manipulacja zawartością
     *
     *
     */
    public TreeSet<Dzien> getListaDni() {
        return listaDni;
    }

    public boolean dodajDzien(Dzien dzien) {
        return listaDni.add(dzien);
    }

    public boolean dodajDni(List<Dzien> dni) {
        return listaDni.addAll(dni);
    }
    public boolean dodajDni(Dzien[] dni) {
        return Collections.addAll(listaDni,dni);
    }



    void zapiszPlik () throws IOException {

        JsonMapper jsonMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        jsonMapper.writeValue(new File(sciezkaPliku),this);

    }
    public static PlikZapisuDni odczytajPlik() throws FileNotFoundException {
        File file = new File(sciezkaPliku);
        JsonMapper jsonMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        String xml = inputStreamToString(new FileInputStream(file));
        try {
            return jsonMapper.readValue(xml, PlikZapisuDni.class);
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


    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
