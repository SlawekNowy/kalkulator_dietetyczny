package pl.lublin.wsei.klasy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dzien implements Serializable,Comparable<Dzien> {
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate timestamp = LocalDate.now();
    private LinkedList<Posilek> posilki;

    public Dzien(List<Posilek> posilki) {
        this.posilki = new LinkedList<>();
        this.posilki.addAll(posilki);
    }

    public Dzien() {
        this.posilki = new LinkedList<>();
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String printTimeStamp() {
        return timestamp.getYear() + "-" + timestamp.getMonthValue() + "-" + timestamp.getDayOfMonth();
    }
    public List<Posilek> getPosilki() {
        return posilki;
    }
    public void dodajPosilek(Posilek posilek) {
        posilki.add(posilek);
    }
    public void dodajPosilki(List<Posilek> posilki) {
        this.posilki.addAll(posilki);
    }
    public void dodajPosilki(Posilek[] posilki) {
        Collections.addAll(this.posilki,posilki);
    }

    @Override
    public int compareTo(Dzien o) {
        return timestamp.compareTo(o.timestamp);
    }
}
