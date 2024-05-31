package client;

import converterYAML.Convertor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("clientgui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.getIcons().add(new Image(Client.class.getResource("icon/icon.jpg").toExternalForm()));
        stage.setTitle("encryptor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}