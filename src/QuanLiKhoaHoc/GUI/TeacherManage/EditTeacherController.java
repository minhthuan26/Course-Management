package QuanLiKhoaHoc.GUI.TeacherManage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherController implements Initializable {

    @FXML
    private AnchorPane windowPane;
    @FXML
    private AnchorPane wrapPane;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField idTxtField;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField lastNameTxtField;
    @FXML
    private TextField birthTxtField;
    @FXML
    private TextField phoneTxtField;

    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Handle();
        setDefault();
    }

    public void Handle(){
        windowPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        windowPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage = (Stage) wrapPane.getScene().getWindow();
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) cancelBtn.getScene().getWindow();
                primaryStage.close();
            }
        });

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    public void setDefault(){
        phoneTxtField.setText(Controller.selectedRow.getPhoneNumber());
        birthTxtField.setText(Controller.selectedRow.getDateOfBirth().toString());
        lastNameTxtField.setText(Controller.selectedRow.getLastName());
        firstNameTxtField.setText(Controller.selectedRow.getFirstName());
        idTxtField.setText(String.valueOf(Controller.selectedRow.getPersonId()));
    }
}
