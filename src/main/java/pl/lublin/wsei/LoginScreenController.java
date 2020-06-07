package pl.lublin.wsei;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginScreenController {
    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField loginTextArea;

    @FXML
    private TextField passwordTextArea;

    @FXML
    private Button loginButton;

    @FXML
    private Button createNewAccountButton;

    public void setErrorsLable(Label errorsLable) {
        this.errorsLable = errorsLable;
    }

    @FXML
    private Label errorsLable;

    public void createNewAccountOnAction() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("createNewAccount.fxml"));
        Pane pane = getPane(loader);
        CreateNewAccountController createNewAccountController = loader.getController();
        createNewAccountController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    public void loginButtonOnAction(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("menuScreen.fxml"));
        Pane pane = getPane(loader);
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    public Pane getPane(FXMLLoader loader) {
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }
}
