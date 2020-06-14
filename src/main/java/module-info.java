module pl.lublin.wsei {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.core;
    requires mysql.connector.java;

    opens pl.lublin.wsei to javafx.fxml;
    exports pl.lublin.wsei;
}