package QuanLiKhoaHoc.GUI.TeacherManage;

import QuanLiKhoaHoc.BUS.StudentManage.StudentBUS;
import QuanLiKhoaHoc.BUS.TeacherManage.TeacherBus;
import QuanLiKhoaHoc.DTO.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TeacherBus teacherBus= new TeacherBus();
    ObservableList<Person> teacherList;
    @FXML
    private TableView<Person> teacherTableView;
    @FXML
    private TableColumn<Person,Integer> teacherID;
    @FXML
    private TableColumn<Person,String> teacherHo;
    @FXML
    private TableColumn<Person,String> teacherTen;
    @FXML
    private TableColumn<Person,Integer> teacherSDT;
    @FXML
    private TableColumn<Person, Date> teacherDate;
    public ObservableList<Person> getTeacherList(){
        teacherList= FXCollections.observableArrayList();
        return teacherList= teacherBus.getTeacherList();
    }
    public void showTeacherList(){
        teacherList= getTeacherList();
        teacherID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PersonId"));
        teacherHo.setCellValueFactory(new PropertyValueFactory<Person, String>("FirstName"));
        teacherTen.setCellValueFactory(new PropertyValueFactory<Person, String>("LastName"));
        teacherSDT.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PhoneNumber"));
        teacherDate .setCellValueFactory(new PropertyValueFactory<Person, Date>("DateOfBirth"));
        teacherTableView.setItems(teacherList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        getTeacherList();
        showTeacherList();
    }
}
