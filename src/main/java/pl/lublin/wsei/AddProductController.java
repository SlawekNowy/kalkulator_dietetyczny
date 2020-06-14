package pl.lublin.wsei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.lublin.wsei.klasy.Produkt;

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
    private TextField carbohydrates;

    @FXML
    private TextField sugars;

    @FXML
    private TextField fats;

    @FXML
    private TextField kcalInHundredGrams;

    @FXML
    private Button addProductButton;

    @FXML
    private Label errorsKcalLabel;

    @FXML
    private Label successLable;

    @FXML
    private TextField proteins;

    @FXML
    private TextField saturatedFats;


    @FXML
    void addProductOnAction() {
        errorsKcalLabel.setText("");
        Produkt produkt = null;
        try{
            produkt = new Produkt(nameProduct.getText(),Float.parseFloat(fats.getText()),
                    Float.parseFloat(saturatedFats.getText()), Float.parseFloat(proteins.getText()),
                    Float.parseFloat(carbohydrates.getText()),Float.parseFloat(sugars.getText()),
                    Float.parseFloat(kcalInHundredGrams.getText()));
        }catch (NumberFormatException ex){
            errorsKcalLabel.setText("Niepoprawny format danych");
        }
        produkt.dodajProduktDoBazy();
        successLable.setText("Dodano "+ produkt.getNazwa());
        nameProduct.clear();
        carbohydrates.clear();
        proteins.clear();
        fats.clear();
        kcalInHundredGrams.clear();
        saturatedFats.clear();
        sugars.clear();
    }

    @FXML
    void goBackOnAction() {
        mainScreenController.loadMenuScreen();
    }

}
