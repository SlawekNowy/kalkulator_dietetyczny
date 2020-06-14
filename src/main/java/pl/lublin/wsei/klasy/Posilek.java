package pl.lublin.wsei.klasy;

import java.util.HashMap;
import java.util.Map;

public class Posilek  {

    private HashMap<Produkt, Integer> skladnikiPosilku; //Key to Produkt, Value to ilość produktu w posiku.

    //poniżej są wartośći na bieżąco kalkulowane przez klasę
    private double iloscTluszczow = 0;
    private double iloscTluszczowNasyconych = 0;
    private double iloscBialek = 0;
    private double iloscWeglowodanow = 0;
    private double iloscCukrow = 0;
    private double iloscKCal = 0;


    public double getIloscTluszczow() {
        return iloscTluszczow;
    }

    public HashMap<Produkt, Integer> getSkladnikiPosilku() {
        return skladnikiPosilku;
    }

    public double getIloscTluszczowNasyconych() {
        return iloscTluszczowNasyconych;
    }

    public double getIloscBialek() {
        return iloscBialek;
    }

    public double getIloscWeglowodanow() {
        return iloscWeglowodanow;
    }

    public double getIloscCukrow() {
        return iloscCukrow;
    }

    public double getIloscKCal() {
        return iloscKCal;
    }


    public Posilek() {
        this.skladnikiPosilku = new HashMap<>();
    }

    public Posilek(HashMap<Produkt, Integer> skladniki) {
        this.skladnikiPosilku = skladniki;
        usunPusteProdukty();
        kalkuluj();
    }


    public void dodajProdukty(Produkt produkt, int ilosc) {
        if (ilosc <= 0) {
            System.out.println("Nieprawidłowa wartość");
            return;
        }
        zmienProdukty(produkt, ilosc);

    }

    public void usunProdukty(Produkt produkt, int ilosc) {
        if (ilosc >= 0) {
            System.out.println("Nieprawidłowa wartość");
            return;
        }
        zmienProdukty(produkt, -ilosc);

    }


    private void zmienProdukty(Produkt produkt, int ilosc) {
        if (skladnikiPosilku.containsKey(produkt)) {
            skladnikiPosilku.replace(produkt, skladnikiPosilku.get(produkt) + ilosc);
        } else {
            skladnikiPosilku.put(produkt, ilosc);
        }

        //Produkt po zmianie może mieć wartość 0. Usuńmy ją.
        usunPustyProdukt(produkt);
        kalkuluj();
    }


    private void usunPusteProdukty() {
        //Automatyczny cleanup
        for (Map.Entry<Produkt, Integer> wpis : skladnikiPosilku.entrySet()) {
            Produkt produkt = wpis.getKey();
            usunPustyProdukt(produkt);
        }
    }

    private void usunPustyProdukt(Produkt produkt) {
        if (skladnikiPosilku.get(produkt) == 0) {
            skladnikiPosilku.remove(produkt);
        }
    }


    private void kalkuluj() {
        //reset wartosci
        this.iloscBialek = 0;
        this.iloscCukrow = 0;
        this.iloscTluszczow = 0;
        this.iloscTluszczowNasyconych = 0;
        this.iloscWeglowodanow = 0;
        this.iloscKCal = 0;
        for (Map.Entry<Produkt, Integer> wpis : skladnikiPosilku.entrySet()) {
            Produkt produkt = wpis.getKey();
            int ilosc = wpis.getValue();
            this.iloscBialek += produkt.getIloscBialek() * ilosc;
            this.iloscKCal += produkt.getIloscKCal() * ilosc;
            this.iloscWeglowodanow += produkt.getIloscWeglowodanow() * ilosc;
            this.iloscTluszczow += produkt.getIloscTluszczow() * ilosc;
            this.iloscTluszczowNasyconych += produkt.getIloscTluszczowNasyconych() * ilosc;
            this.iloscCukrow += produkt.getIloscCukrow() * ilosc;
        }

    }


}
