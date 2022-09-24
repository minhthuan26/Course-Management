package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.DTO.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
//    @FXML
//    private AnchorPane mainPane;
//    @FXML
//    private HBox hBoxTool;
//    @FXML
//    private Button btnAdd;
//    @FXML
//    private Button btnDelete;
//    @FXML
//    private Button btnEdit;

    @FXML
    private ComboBox<String> comboboxSelectCourse;
    @FXML
    private ListView listView;
    ObservableList<String> list = FXCollections.observableArrayList("Online","Onsite");
    @FXML
    private TableView<Course> tableView;
    @FXML
    private TableColumn<Course, Integer> courseId;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, String> courseDescription;
    @FXML
    private TableColumn<Course, String> courseImage;
    @FXML
    private TableColumn<Course, Date> dateStart;
    @FXML
    private TableColumn<Course, Date> dateEnd;
    @FXML
    private TableColumn<Course, Date> dateCreate;

    private ObservableList<Course> coursesList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        comboboxSelectCourse.setItems(list);

        coursesList = FXCollections.observableArrayList(
//                new Course(1,"JavaFX","Thiet ke giao dien voi JavaFX","",);
        );
    }
    public void comboBoxChanged(){

    }
}
