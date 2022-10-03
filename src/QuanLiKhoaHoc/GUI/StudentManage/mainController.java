package QuanLiKhoaHoc.GUI.StudentManage;

import QuanLiKhoaHoc.BUS.StudentManage.StudentBUS;
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

public class mainController implements Initializable {
    private StudentBUS studentBUS = new StudentBUS();
    private Stage primaryStage;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDateOfBirth;



    public void handle(){
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addStudent();
                alertSuccess();

            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) btnCancel.getScene().getWindow();
                primaryStage.close();
            }
        });
    }
    public void alertSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thêm học sinh thành công!");
        alert.showAndWait();
        primaryStage = (Stage) btnCancel.getScene().getWindow();
        primaryStage.close();
    }
    public void addStudent(){
//        Date dayNow =new Date(2022, 12, 20);
        Person person = studentBUS.addStudent(txtFirstName.getText(),txtLastName.getText(),txtEmail.getText(),txtSDT.getText(),txtDateOfBirth.getText());
        PersonRole personRole = studentBUS.addStudentRole(person.getPersonId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handle();
    }
}
