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
    private Label sugarsGrams;

    @FXML
    private Label saturatedFatsGrams;

    @FXML
    private Label sugarsPerCent;

    @FXML
    private Label saturatedFatsPerCent;

    @FXML
    void countButtonOnAction() {

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

    @FXML
    void goBackOnAction() {
        mainScreenController.loadMenuScreen();
    }


}
