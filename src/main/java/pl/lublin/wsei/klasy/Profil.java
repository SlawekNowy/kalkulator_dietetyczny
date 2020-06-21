package pl.lublin.wsei.klasy;

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
import java.util.Date;

import static javax.xml.crypto.dsig.DigestMethod.SHA3_256;

public class Profil extends SQLSerializable {

    private String imie;
    private String drugieImie;
    private String nazwisko;
    private LocalDate dataUrodzenia;


    public Profil() {

    }

    public Profil(String imie, String nazwisko, LocalDate dataUrodzenia) {
        new Profil(imie, "", nazwisko, dataUrodzenia);
    }

    public Profil(String imie, String drugieImie, String nazwisko, LocalDate dataUrodzenia) {
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

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }


    private int ID;

    //Dni też serializować?

    protected void modify(OperationType operationType,String login,String salt,String hashHasla) {
        //return null;
        //Na początek konstruujemy szablon
        String templateQuery = "";
        switch (operationType) {
            case INSERT:
                templateQuery = "INSERT INTO uzytkownicy " +
                        "(Imie,Drugie_imie,Nazwisko,Data_urodzenia,login,salt,hashHasla) VALUES " +
                        "(?,?,?,?,?,?,?)";
                break;
            case UPDATE:
                templateQuery = "UPDATE uzytkownicy " +
                        "SET" +
                        " Imie=?," +
                        " Drugie_imie=?"+
                        " Nazwisko =?"+
                        " Data_urodzenia=?" +
                        " WHERE Id="+ID;
                break;
            case DELETE:
                templateQuery = "DELETE FROM uzytkownicy WHERE Id="+ID;
                break;
        }
        SQLManager manager = SQLManager.initConnection();


        switch (operationType) {
            case INSERT:
                try {
                    PreparedStatement outVar = manager.connection.prepareStatement(templateQuery, Statement.RETURN_GENERATED_KEYS);
                    outVar.setString(1,imie);
                    outVar.setString(2,drugieImie);
                    outVar.setString(3,nazwisko);
                    outVar.setObject(4,dataUrodzenia);
                    outVar.setString(5,login);
                    outVar.setString(6,salt);
                    outVar.setString(7,hashHasla);
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
                    outVar.setString(1,imie);
                    outVar.setString(2,drugieImie);
                    outVar.setString(3,nazwisko);
                    outVar.setObject(4, dataUrodzenia);
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

    public void dodajUzytkownikaDoBazy(String login,String salt,String hashHasla) {
        modify(OperationType.INSERT,login,salt,hashHasla);
    }
    //@Override
    public static Profil getObjectFromDB(String login,String haslo) {
        String query = "SELECT * FROM uzytkownicy WHERE login = ?";
        SQLManager manager = SQLManager.initConnection();
        try {

            PreparedStatement statement = manager.connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,login);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.absolute(1);
            String salt = rs.getString("salt");
            String checksum = rs.getString("hashHasla");
            haslo = salt+haslo;

            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA3-512");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            assert digest != null;
            byte[] hashHaslo = digest.digest(haslo.getBytes(StandardCharsets.UTF_8));
            String hashHasloStr = AppHelper.bytesToHex(hashHaslo);
            haslo =null; //należy to usunąć tak szybko jak to możliwe
            if (!hashHasloStr.equals(checksum)) {
                return null;
            }
            Profil profil  = new Profil();
            profil.setImie(rs.getString("Imie"));
            profil.setDrugieImie(rs.getString("Drugie_imie"));
            profil.setNazwisko(rs.getString("Nazwisko"));
            profil.setDataUrodzenia(rs.getObject("Data_urodzenia",LocalDate.class));
            profil.ID = rs.getInt("ID");
            return profil;



        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return null;
    }
}
