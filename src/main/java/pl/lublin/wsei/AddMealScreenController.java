package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddMealScreenController {
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private ChoiceBox<?> productChoiceBox;

    @FXML
    private TextField productWeight;

    @FXML
    private Button addProductButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button clearButton;

    @FXML
    private Label addPruductLable;

    @FXML
    private Label carbohydrates;

    @FXML
    private Label sugars;

    @FXML
    private Label proteins;

    @FXML
    private Label fats;

    @FXML
    private Label saturatedFats;

    @FXML
    void addProductButtonOnAction() {

    }

    @FXML
    void addProductOnAction() {

    }

    @FXML
    void clearButtonOnAction() {

    }

    @FXML
    void  goBackOnAction(){
        mainScreenController.loadMenuScreen();
    }
}
