package QuanLiKhoaHoc.GUI.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane wrapPane;

    private Stage primaryStage;

    @FXML
    private Button minimizeWindowBtn;

    @FXML
    private Button resizeWindowBtn;

    @FXML
    private Button closeWindowBtn;

    @FXML
    private Button teacherManageBtn;

    @FXML
    private Button studentManageBtn;

    @FXML
    private Button assignmentManageBtn;

    @FXML
    private Button courseManageBtn;

    @FXML
    private Button resultManageBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Handle();
    }

    public void Handle(){
        closeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.close();
            }
        });

        minimizeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.setIconified(true);
            }
        });

        resizeWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                if(primaryStage.isMaximized())
                    primaryStage.setMaximized(false);
                else
                    primaryStage.setMaximized(true);
            }
        });
    }
}
