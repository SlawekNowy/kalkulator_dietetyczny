module pl.lublin.wsei {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;
    requires mysql.connector.java;
    requires com.esotericsoftware.kryo;

    opens pl.lublin.wsei to javafx.fxml;
    exports pl.lublin.wsei;
    opens pl.lublin.wsei.core to com.esotericsoftware.kryo;
    opens pl.lublin.wsei.klasy to com.esotericsoftware.kryo;
}