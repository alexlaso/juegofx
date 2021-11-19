module com.example.juegardo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.juegardo to javafx.fxml;
    exports com.example.juegardo;
}