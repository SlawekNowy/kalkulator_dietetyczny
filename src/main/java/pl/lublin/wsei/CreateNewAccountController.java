package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewAccountController {
    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField loginTextArea;

    @FXML
    private TextField passwordTextArea;

    @FXML
    private Button createNewAccountButton;

    @FXML
    private Label errorsLable;

    @FXML
    private Label succsesLable;

    @FXML
    private Button goBackButton;

    @FXML
    void createNewAccountOnAction() {

    }

    @FXML
    void goBackButtonOnAction() {
        mainScreenController.loadLoginScreen();
    }

}
