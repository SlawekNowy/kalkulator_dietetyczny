package pl.lublin.wsei.klasy;

import java.util.Date;

public class Profil {

    private String imie;
    private String drugieImie;
    private String nazwisko;
    private Date dataUrodzenia;

    public Profil() {

    }

    public Profil(String imie, String nazwisko, Date dataUrodzenia) {
        new Profil(imie, "", nazwisko, dataUrodzenia);
    }

    public Profil(String imie, String drugieImie, String nazwisko, Date dataUrodzenia) {
        this.imie = imie;
        this.drugieImie = drugieImie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDrugieImie() {
        return drugieImie;
    }

    public void setDrugieImie(String drugieImie) {
        this.drugieImie = drugieImie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
