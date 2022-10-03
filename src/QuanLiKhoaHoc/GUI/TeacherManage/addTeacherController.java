package QuanLiKhoaHoc.GUI.TeacherManage;
import QuanLiKhoaHoc.BUS.TeacherManage.TeacherBus;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class addTeacherController implements Initializable {
    public TeacherBus teacherBUS = new TeacherBus();
    public Stage primaryStage;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtDateOfBirth;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;

    public void handle (){
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addTeacher();
                alertSuccess();
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage = (Stage) btnCancel.getScene().getWindow();
                primaryStage.close();
            }
        });
    }
    public void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Thêm giáo viên thành công!");
        alert.showAndWait();
        primaryStage = (Stage) btnCancel.getScene().getWindow();
        primaryStage.close();
    }
    public void addTeacher(){
        Date dayNow =new Date(2022, 12, 20);
        Person person = teacherBUS.addTeacher(txtFirstName.getText(),txtLastName.getText(),txtEmail.getText(),txtPhoneNumber.getText(),txtDateOfBirth.getText());
        PersonRole personRole = teacherBUS.addTeacherRole(person.getPersonId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handle();
    }
}
