module pl.lublin.wsei {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pl.lublin.wsei to javafx.fxml;
    exports pl.lublin.wsei;
}