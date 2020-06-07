package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProductController {
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField nameProduct;

    @FXML
    private Button goBackButton;

    @FXML
    private Label errorsNameProductLabel;

    @FXML
    private TextField carbohydratesPerCent;

    @FXML
    private TextField proteinsPerCent;

    @FXML
    private TextField fatsPerCent;

    @FXML
    private TextField kcalInHundredGrams;

    @FXML
    private Button addProductButton;

    @FXML
    private Label errorsCarbohydratesLable;

    @FXML
    private Label errorsProteinsLabel;

    @FXML
    private Label errorsFatsLabel;

    @FXML
    private Label errorsKcalLabel;

    @FXML
    void addProductOnAction() {

    }

    @FXML
    void goBackOnAction() {
        mainScreenController.loadMenuScreen();
    }

}
