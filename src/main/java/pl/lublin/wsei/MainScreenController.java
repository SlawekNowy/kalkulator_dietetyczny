package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainScreenController {
    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize(){
        loadLoginScreen();
    }

    public void loadLoginScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("loginScreen.fxml"));
        Pane pane = getPane(loader);
        LoginScreenController loginScreenController = loader.getController();
        loginScreenController.setMainScreenController(this);
        setScreen(pane);
    }

    public void loadMenuScreen(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pl.lublin.wsei/menuScreen.fxml"));
        Pane pane = getPane(loader);
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setMainScreenController(this);
        setScreen(pane);
    }

    public Pane getPane(FXMLLoader loader) {
        Pane pane = null;
        try {
            pane = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        return pane;
    }

    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

}
