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
    private Label carbohydrates;

    @FXML
    private Label proteins;

    @FXML
    private Label fats;

    @FXML
    private Button addProductButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Label errorsLabel;

    @FXML
    void addProductOnAction() {

    }
    @FXML
    void  goBackOnAction(){
        mainScreenController.loadMenuScreen();
    }
}
