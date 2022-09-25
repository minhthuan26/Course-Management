package QuanLiKhoaHoc.GUI.StudentManage;

import QuanLiKhoaHoc.BUS.StudentManage.StudentBUS;
import QuanLiKhoaHoc.DTO.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public StudentBUS studentBUS = new StudentBUS();
    ObservableList<Person> studentList;
    @FXML
    private TableView<Person> studentTableView;
    @FXML
    private TableColumn<Person,Integer> studentID;
    @FXML
    private TableColumn<Person,String> studentHo;
    @FXML
    private TableColumn<Person,String> studentTen;
    @FXML
    private TableColumn<Person,Integer> studentSDT;
    @FXML
    private TableColumn<Person, Date> studentDate;
    @FXML
    private Button btnAddStudent;
    public ObservableList<Person> getStudentList(){
        studentList= FXCollections.observableArrayList();
        return studentList= studentBUS.getStudentList();
    }
    public void showStudentList(){
        studentList= getStudentList();
        studentID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PersonId"));
        studentHo.setCellValueFactory(new PropertyValueFactory<Person, String>("FirstName"));
        studentTen.setCellValueFactory(new PropertyValueFactory<Person, String>("LastName"));
        studentSDT.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PhoneNumber"));
        studentDate .setCellValueFactory(new PropertyValueFactory<Person, Date>("DateOfBirth"));
        studentTableView.setItems(studentList);
    }

    public void handle (){
        btnAddStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage newstage = new Stage();
                main sreen = new main();
                //ngăn tương tác với dashboard
                Stage oldStage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                newstage.initModality(Modality.WINDOW_MODAL);
                newstage.initOwner(oldStage);
                //chạy newStage
                try {
                    sreen.start(newstage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        showStudentList();
        handle();
    }
}
