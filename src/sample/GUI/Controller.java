package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.BUS.sampleBUS;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Stage primaryStage;

    @FXML
    private AnchorPane main;

    @FXML
    private Button exitButton;

    @FXML
    private Button send;

    @FXML
    private TextField name;

    @FXML
    private Label result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) main.getScene().getWindow();
                primaryStage.close();
            }
        });

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String yourName = name.getText();
                sampleBUS bus = new sampleBUS();
                result.setText(bus.Hello(yourName));
                System.out.println(result.getText());
            }
        });
    }
}
