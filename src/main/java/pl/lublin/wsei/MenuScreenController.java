package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuScreenController {
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private Button addMealButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button exitButton;

    @FXML
    void addMealOnAction() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pl.lublin.wsei/addMealScreen.fxml"));
        Pane pane = getPane(loader);
        AddMealScreenController addMealScreenController = loader.getController();
        addMealScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    @FXML
    void addProductOnAction() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pl.lublin.wsei/addProductScreen.fxml"));
        Pane pane = getPane(loader);
        AddProductController addProductController = loader.getController();
        addProductController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    @FXML
    void exitOnAction() {
        mainScreenController.loadLoginScreen();
    }

    @FXML
    void statisticsOnAction() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pl.lublin.wsei/statisticsScreen.fxml"));
        Pane pane = getPane(loader);
        StatisticsController statisticsController = loader.getController();
        statisticsController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    private Pane getPane(FXMLLoader loader) {
        Pane pane = null;
        try {
            pane = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        return pane;
    }
}
