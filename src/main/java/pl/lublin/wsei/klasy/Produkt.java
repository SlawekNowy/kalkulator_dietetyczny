package pl.lublin.wsei.klasy;


import pl.lublin.wsei.core.SQLManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Produkt extends SQLSerializable {
    private String nazwa;
    private String marka;
    private int ID;

    //sporo Boilerplatu, Można to podzielić na mniejszą klasę
    private float iloscTluszczow;
    private float iloscTluszczowNasyconych=0;
    private float iloscBialek;
    private float iloscWeglowodanow;
    private float iloscCukrow=0;
    private float iloscKCal;

    public Produkt(String nazwa, float iloscTluszczow, float iloscTluszczowNasyconych, float iloscBialek, float iloscWeglowodanow, float iloscCukrow, float iloscKCal) {
        this.nazwa = nazwa;
        this.iloscTluszczow = iloscTluszczow;
        this.iloscTluszczowNasyconych = iloscTluszczowNasyconych;
        this.iloscBialek = iloscBialek;
        this.iloscWeglowodanow = iloscWeglowodanow;
        this.iloscCukrow = iloscCukrow;
        this.iloscKCal = iloscKCal;
    }

    public Produkt() {

    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public float getIloscTluszczow() {
        return iloscTluszczow;
    }

    public void setIloscTluszczow(float iloscTluszczow) {
        this.iloscTluszczow = iloscTluszczow;
    }

    public float getIloscTluszczowNasyconych() {
        return iloscTluszczowNasyconych;
    }

    public void setIloscTluszczowNasyconych(float iloscTluszczowNasyconych) {
        this.iloscTluszczowNasyconych = iloscTluszczowNasyconych;
    }

    public float getIloscBialek() {
        return iloscBialek;
    }

    public void setIloscBialek(float iloscBialek) {
        this.iloscBialek = iloscBialek;
    }

    public float getIloscWeglowodanow() {
        return iloscWeglowodanow;
    }

    public void setIloscWeglowodanow(float iloscWeglowodanow) {
        this.iloscWeglowodanow = iloscWeglowodanow;
    }

    public float getIloscCukrow() {
        return iloscCukrow;
    }

    public void setIloscCukrow(float iloscCukrow) {
        this.iloscCukrow = iloscCukrow;
    }

    public float getIloscKCal() {
        return iloscKCal;
    }

    public void setIloscKCal(float iloscKCal) {
        this.iloscKCal = iloscKCal;
    }


    private void modify(OperationType operationType) {
        String templateQuery = "";
        switch (operationType) {
            case INSERT:
                templateQuery = "INSERT INTO wartosci_odzywcze " +
                        "(Nazwa,Bialko,Weglowodany,Cukry,Tluszcze,Tluszcze_nasycone,kalorie) VALUES " +
                        "(?,?,?,?,?,?,?)";
                break;
            case UPDATE:
                templateQuery = "UPDATE wartosci_odzywcze " +
                        "SET" +
                        " Nazwa=?," +
                        " Bialko=?" +
                        " Weglowodany =?" +
                        " Cukry=?" +
                        " WHERE Id=" + ID;
                break;
            case DELETE:
                templateQuery = "DELETE FROM wartosci_odzywcze WHERE Id=" + ID;
                break;
        }
        SQLManager manager = SQLManager.initConnection();


        switch (operationType) {
            case INSERT:
                try {
                    PreparedStatement outVar = manager.connection.prepareStatement(templateQuery, Statement.RETURN_GENERATED_KEYS);
                    outVar.setString(1, nazwa);
                    outVar.setFloat(2, iloscBialek);
                    outVar.setFloat(3, iloscWeglowodanow);
                    outVar.setFloat(4, iloscCukrow);
                    outVar.setFloat(5, iloscTluszczow);
                    outVar.setFloat(6, iloscTluszczowNasyconych);
                    outVar.setFloat(7, iloscKCal);
                    outVar.execute();
                    ResultSet rs = outVar.getGeneratedKeys();
                    if (rs.next()) {
                        ID = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                break;
            case UPDATE:
                try {
                    PreparedStatement outVar = manager.connection.prepareStatement(templateQuery);
                    outVar.setString(1, nazwa);
                    outVar.setFloat(2, iloscBialek);
                    outVar.setFloat(3, iloscWeglowodanow);
                    outVar.setFloat(4, iloscCukrow);
                    outVar.execute();
                } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                break;
            case DELETE:
                try {
                    PreparedStatement outVar = manager.connection.prepareStatement(templateQuery);
                    outVar.execute();
                } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                break;
        }
        manager.shutdownConnection();
    }
    public void dodajProduktDoBazy() {
        modify(OperationType.INSERT);
    }


    public static Produkt getObjectFromDB() {
        return null;
    }
}
