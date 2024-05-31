module com.example.cursedcryptor {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.yaml.snakeyaml;

    opens client to javafx.fxml;
    exports client;
}