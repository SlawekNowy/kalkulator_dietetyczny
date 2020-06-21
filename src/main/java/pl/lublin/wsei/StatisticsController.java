package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class StatisticsController {
    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private Button todayButton;

    @FXML
    private Button lastWeekButton;

    @FXML
    private Button lastMonthButton;

    @FXML
    private DatePicker fromDataPicker;

    @FXML
    private DatePicker toDataPicker;

    @FXML
    private Label errorsDataLabel;

    @FXML
    private Button countButton;

    @FXML
    private Label carbohydratesGrams;

    @FXML
    private Label proteinsGrams;

    @FXML
    private Label fatsGrams;

    @FXML
    private Label carbohydratesPerCent;

    @FXML
    private Label proteinsPerCent;

    @FXML
    private Label fatsPerCent;

    @FXML
    private Button goBackButton;

    @FXML
    void countButtonOnAction() {

    }

    @FXML
    void goBackOnAction() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    void lastMonthOnAction() {

    }

    @FXML
    void lastWeekOnAction() {

    }

    @FXML
    void todayOnAction() {

    }

}
