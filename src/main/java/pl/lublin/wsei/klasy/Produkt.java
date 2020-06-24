package pl.lublin.wsei.klasy;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pl.lublin.wsei.core.AppHelper;
import pl.lublin.wsei.core.SQLManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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


    public static Set<Produkt> getAllProducts() {
        String query = "SELECT * FROM wartosci_odzywcze";
        SQLManager manager = SQLManager.initConnection();
        try {

            PreparedStatement statement = manager.connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            HashSet<Produkt> out = new HashSet<>();
            while (rs.next()) {
                //(ID,Nazwa,Bialko,Weglowodany,Cukry,Tluszcze,Tluszcze_nasycone,kalorie)
                Produkt produkt = new Produkt(rs.getString("Nazwa"),
                        rs.getFloat("Tluszcze"),
                        rs.getFloat("Tluszcze_nasycone"),
                        rs.getFloat("Bialko"),
                        rs.getFloat("Weglowodany"),
                        rs.getFloat("Cukry"),
                        rs.getFloat("kalorie"));
                out.add(produkt);
            }
            manager.shutdownConnection();
            return out;

            /*
            Profil profil  = new Profil();
            profil.setImie(rs.getString("Imie"));
            profil.setDrugieImie(rs.getString("Drugie_imie"));
            profil.setNazwisko(rs.getString("Nazwisko"));
            profil.setDataUrodzenia(rs.getObject("Data_urodzenia", LocalDate.class));
            profil.ID = rs.getInt("ID");
            return profil;
            */


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return null;
    }


    //nie używać!!!!
    public int getID() {
        return ID;
    }
    //nie używać!!!!
    public void  setID(int ID) {
        this.ID = ID;
    }
}
