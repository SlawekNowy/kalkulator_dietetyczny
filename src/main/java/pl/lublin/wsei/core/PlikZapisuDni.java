package pl.lublin.wsei.core;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import pl.lublin.wsei.klasy.Dzien;
import pl.lublin.wsei.klasy.Posilek;
import pl.lublin.wsei.klasy.Produkt;
import pl.lublin.wsei.klasy.Profil;



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class PlikZapisuDni{

    //Sprawdzane przez interfejs
    private static final long serialVersionUID = 1L;
    //to nie będzie serializowane
    private static final transient Kryo kryo = new Kryo();
    static final String sciezkaPliku = "zapisDni.bin";
    private Profil profil;
    private final TreeSet<Dzien> listaDni;


    PlikZapisuDni() {
        listaDni = new TreeSet<>();
        //tylko to będzie serializowanie i NIC WIĘCEJ!!!
        kryo.register(PlikZapisuDni.class);
        kryo.register(TreeSet.class);
        kryo.register(LinkedList.class);
        kryo.register(HashMap.class);
        kryo.register(Dzien.class);
        kryo.register(Posilek.class);
        kryo.register(Produkt.class);
        kryo.register(Profil.class);
        kryo.register(LocalDate.class);

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


    /*
         Output output = new Output(new FileOutputStream("file.bin"));
          kryo.writeObject(output, object);
          output.close();

          Input input = new Input(new FileInputStream("file.bin"));
          SomeClass object2 = kryo.readObject(input, SomeClass.class);
          input.close();
         */
    void zapiszPlik () throws IOException {

        /*
        JsonMapper jsonMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        jsonMapper.writeValue(new File(sciezkaPliku),this);

         */
        Output output = new Output(new FileOutputStream(sciezkaPliku));
        kryo.writeObject(output, this);
        output.close();

    }
    public static PlikZapisuDni odczytajPlik() throws FileNotFoundException {
        //return null;
        /*
        File file = new File(sciezkaPliku);
        JsonMapper jsonMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        String xml = inputStreamToString(new FileInputStream(file));
        try {
            return jsonMapper.readValue(xml, PlikZapisuDni.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
        */
        kryo.register(PlikZapisuDni.class);
        kryo.register(TreeSet.class);
        kryo.register(LinkedList.class);
        kryo.register(HashMap.class);
        kryo.register(Dzien.class);
        kryo.register(Posilek.class);
        kryo.register(Produkt.class);
        kryo.register(Profil.class);
        Input input = new Input(new FileInputStream(sciezkaPliku));
        PlikZapisuDni object2 = kryo.readObject(input, PlikZapisuDni.class);
        input.close();
        return object2;
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
