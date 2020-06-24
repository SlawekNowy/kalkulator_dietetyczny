module pl.lublin.wsei {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.core;
    requires mysql.connector.java;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens pl.lublin.wsei to javafx.fxml;
    exports pl.lublin.wsei;
    opens pl.lublin.wsei.core to com.fasterxml.jackson.databind;
    opens pl.lublin.wsei.klasy to com.fasterxml.jackson.databind;
}