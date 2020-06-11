package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.lublin.wsei.core.AppHelper;
import pl.lublin.wsei.klasy.Profil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateNewAccountController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField loginTextArea;

    @FXML
    private PasswordField passwordTextArea;

    @FXML
    private Button createNewAccountButton;

    @FXML
    private Label errorsLable;

    @FXML
    private Label succsesLable;

    @FXML
    private Button goBackButton;
    @FXML
    private TextField nameTextArea;

    @FXML
    private TextField secondNameTextArea;
    @FXML
    private TextField nazwiskoTextArea;

    @FXML
    private DatePicker datePicker;


    @FXML
    void createNewAccountOnAction() {
        //TODO utwórz wpis. NIE WOLNO WPISAĆ HASŁA BEZPOŚREDNIO DO BAZY.
        String hasloDoHashowania = passwordTextArea.getText();
        SecureRandom secureRNG = new SecureRandom();
        byte[] salt = new byte[24];
        secureRNG.nextBytes(salt);
        MessageDigest digest = null;
        hasloDoHashowania = AppHelper.bytesToHex(salt) + hasloDoHashowania; //tzw saltowanie hasła
        try {
             digest = MessageDigest.getInstance("SHA3-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
        byte[] hashHaslo = digest.digest(hasloDoHashowania.getBytes(StandardCharsets.UTF_8));
        String hashHasloStr = AppHelper.bytesToHex(hashHaslo);
        Profil profil = new Profil(nameTextArea.getText(),secondNameTextArea.getText(),nazwiskoTextArea.getText(),null);
        LocalDate localDate = datePicker.getValue();
        profil.setDataUrodzenia(localDate);
        profil.dodajUzytkownikaDoBazy(loginTextArea.getText(),AppHelper.bytesToHex(salt),hashHasloStr);


    }

    @FXML
    void goBackButtonOnAction() {
        mainScreenController.loadLoginScreen();
    }




}
