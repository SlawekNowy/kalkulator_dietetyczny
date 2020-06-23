package pl.lublin.wsei;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import pl.lublin.wsei.core.SQLManager;
import pl.lublin.wsei.klasy.Produkt;


import java.util.Set;


public class AddMealScreenController {
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private ChoiceBox<Produkt> productChoiceBox;

    @FXML
    private Spinner<Double> productWeight;

    @FXML
    private Label carbohydrates;

    @FXML
    private Label proteins;

    @FXML
    private Label fats;

    @FXML
    private Label sugars;

    @FXML
    private Label saturatedFats;


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
    void updateValues() {
        Produkt produkt = productChoiceBox.getValue();
        Double wagaProduktu = productWeight.getValue();
        if (wagaProduktu < 0) {
            return;
        }
        carbohydrates.setText(String.format("%.2f", produkt.getIloscWeglowodanow() * (wagaProduktu / 100)) + " g");

        sugars.setText(String.format("%.2f", produkt.getIloscCukrow() * (wagaProduktu / 100)) + " g");

        proteins.setText(String.format("%.2f", produkt.getIloscBialek() * (wagaProduktu / 100)) + " g");

        fats.setText(String.format("%.2f", produkt.getIloscTluszczow() * (wagaProduktu / 100) )+ " g");

        saturatedFats.setText(String.format("%.2f", produkt.getIloscTluszczowNasyconych() * (wagaProduktu / 100))+ " g");
    }

    @FXML
    void goBackOnAction() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void initialize() {
        //bierz wszystkie produkty
        SQLManager manager = SQLManager.initConnection();
        Set<Produkt> produkty = Produkt.getAllProducts();
        manager.shutdownConnection();
        productChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Produkt object) {
                return object.getNazwa();
            }

            @Override
            public Produkt fromString(String string) {
                //noinspection OptionalGetWithoutIsPresent
                return produkty.stream()
                        .filter(produkt -> produkt.getNazwa().equals(string))
                        .findFirst().get();
            }
        });
        if (produkty == null) {
            Produkt p = new Produkt();
            p.setNazwa("Brak produktu");
            productChoiceBox.setValue(p);
            return;
        }
        productChoiceBox.getItems().addAll(produkty);
        productChoiceBox.getSelectionModel().select(0);

        carbohydrates.setText("0 g");
        sugars.setText(" 0 g");
        fats.setText("0 g");
        saturatedFats.setText("0 g");
        proteins.setText("0 g");
        productWeight.getStyleClass().clear();
       // productWeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100000000, 0, 1)););


        //haha this is called twice
        productWeight.valueProperty().addListener((observable, oldValue, newValue) -> {

            Produkt produkt = productChoiceBox.getValue();
            if (newValue < 0) {
                return;
            }
            carbohydrates.setText(String.format("%.2f", produkt.getIloscWeglowodanow() * (newValue / 100)) + " g");

            sugars.setText(String.format("%.2f", produkt.getIloscCukrow() * (newValue / 100)) + " g");

            proteins.setText(String.format("%.2f", produkt.getIloscBialek() * (newValue / 100)) + " g");

            fats.setText(String.format("%.2f", produkt.getIloscTluszczow() * (newValue / 100) )+ " g");

            saturatedFats.setText(String.format("%.2f", produkt.getIloscTluszczowNasyconych() * (newValue / 100))+ " g");

            //updateValues();

        });
        //JANK Kod powyżej nie będzie aktywowany jeżeli nie zrobię tego hacka.
        /*
        productWeight.focusedProperty().addListener((observable, oldValue, newValue) -> {
             if (!newValue) {
                 productWeight.commitValue(); // won't change value, but will commit editor
        }
        });
        */
    }
}

