package pl.lublin.wsei.core;

import pl.lublin.wsei.klasy.Dzien;
import pl.lublin.wsei.klasy.Posilek;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveDataManager {
    public static PlikZapisuDni plikZapisu;
    public static void inicjalizujPlik() {
        try {
            plikZapisu = PlikZapisuDni.odczytajPlik();
        } catch (FileNotFoundException e) {
            plikZapisu = new PlikZapisuDni();
            plikZapisu.setProfil(AppHelper.zalogowanyProfil);
        }
    }
    public static void dodajPosilek(Posilek posilek) {
        List<Dzien> dni = plikZapisu.getListaDni().stream().filter(o ->
            o.getTimestamp().compareTo(LocalDate.now()) == 0
        ).collect(Collectors.toList());
        //w tym momencie może być co najwyżej jedna iteracja daty
        Dzien dzien;
        if (dni.size()==1) {
            dzien = dni.get(0);
            plikZapisu.getListaDni().remove(dzien);
        } else {
            dzien = new Dzien();
        }

        dzien.dodajPosilek(posilek);
        plikZapisu.getListaDni().add(dzien);
    }
    public static void zapiszPlik() throws IOException {
        plikZapisu.zapiszPlik();
    }
}
