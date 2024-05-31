package client;

import converterYAML.Convertor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;

public class ClientController {

    public Button OpenButton;
    public Button CloseButton;
    public Button FileExplorer;
    public TextField FileTyper;
    public TextArea OutputArea;

    @FXML
    protected void onOpenButtonClick(ActionEvent event) {
        OpenButton.setDisable(true);
        CloseButton.setDisable(false);

        onStart();
    }

    @FXML
    protected void onCloseButtonClick(ActionEvent event) {
        OpenButton.setDisable(false);
        CloseButton.setDisable(true);
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onFileExplorerClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

        Stage stage = (Stage) FileExplorer.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            FileTyper.setText(file.getAbsolutePath());
        }
    }

    private void onStart(){
        System.out.println("Client started");
        try {
            String filepath = FileTyper.getText();
            if (filepath.isEmpty()) {
                filepath = "src/main/resources/test.txt";
            }

            Convertor.YamlWriter(filepath, "message.yml");

            int portnumber = 50000;

            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();

            byte[] buffer = new byte[1024];

            File yamlfile = new File("message.yml");
            FileInputStream stream = new FileInputStream(yamlfile);
            int bytesRead = stream.read(buffer);

            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, address, portnumber);

            socket.send(packet);
            System.out.println("package sent");

            //Получение датаграммы
            byte[] bufferT = new byte[1024];
            DatagramPacket packetT = new DatagramPacket(bufferT, bufferT.length);
            socket.receive(packetT);

            String message = new String(packetT.getData());
            OutputArea.setText(message);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
