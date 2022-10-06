package QuanLiKhoaHoc.GUI.StudentManage;

import QuanLiKhoaHoc.BUS.StudentManage.StudentBUS;
import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.CourseRegister;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public StudentBUS studentBUS = new StudentBUS();
    ObservableList<Person> studentList,studentSearch;
    @FXML
    private TableView<Person> studentTableView;
    @FXML
    private TableColumn<Person, Integer> studentID;
    @FXML
    private TableColumn<Person, String> studentHo;
    @FXML
    private TableColumn<Person, String> studentTen;
    @FXML
    private TableColumn<Person, Integer> studentSDT;
    @FXML
    private TableColumn<Person, Date> studentDate;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnEdit;

    public static Person selectedRow = null;

    public ObservableList<Person> getStudentList() {
        studentList = FXCollections.observableArrayList();
        return studentList = studentBUS.getStudentList();
    }

    public void showStudentList() {
        studentList = getStudentList();
        studentID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PersonId"));
        studentHo.setCellValueFactory(new PropertyValueFactory<Person, String>("FirstName"));
        studentTen.setCellValueFactory(new PropertyValueFactory<Person, String>("LastName"));
        studentSDT.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PhoneNumber"));
        studentDate.setCellValueFactory(new PropertyValueFactory<Person, Date>("DateOfBirth"));
        studentTableView.setItems(studentList);
    }

    public ObservableList<Person> getStudentSearch() {
        studentSearch = FXCollections.observableArrayList();
        return studentSearch = (ObservableList<Person>) studentBUS.getStudentSearch(Integer.parseInt(txtSearch.getText()));
    }

    public void showStudentSearch(int id) {
        studentSearch = getStudentSearch();
        studentID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PersonId"));
        studentHo.setCellValueFactory(new PropertyValueFactory<Person, String>("FirstName"));
        studentTen.setCellValueFactory(new PropertyValueFactory<Person, String>("LastName"));
        studentSDT.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PhoneNumber"));
        studentDate.setCellValueFactory(new PropertyValueFactory<Person, Date>("DateOfBirth"));
        studentTableView.setItems(studentSearch);
    }


    public void handle() {
        btnAddStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage newstage = new Stage();
                main srceen = new main();
                //ngăn tương tác với dashboard
                Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                newstage.initModality(Modality.WINDOW_MODAL);
                newstage.initOwner(oldStage);
                //chạy newStage
                try {
                    srceen.start(newstage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showStudentList();
            }
        });
        btnDeleteStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedRow = selectRow();
                if (selectedRow != null) {
                    CourseRegister personExist = studentBUS.getPersonFromCourseRegisterByID(selectedRow.getPersonId());
                    if(personExist==null){
                        Person person = new Person(selectedRow.getPersonId(), selectedRow.getFirstName(), selectedRow.getLastName(),
                                selectedRow.getEmail(), selectedRow.getPhoneNumber(), selectedRow.getDateOfBirth(), selectedRow.getPersonImage());
                        studentBUS.deletePersonRole(selectedRow.getPersonId());
                        studentBUS.deletePerson(person);
                        System.out.println("Xóa thành công");
                        Alert("Thành công", "Xóa sinh viên thành công");
                    }else {
                        Alert("Lỗi","Sinh viên đang được chấm điểm trong 1 lớp nào đó");
                    }
                } else {
                    Alert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện huỷ");
                }
            }
        });
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showStudentSearch(Integer.parseInt(txtSearch.getText()));
            }
        });

        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedRow = selectRow();
                if(selectedRow == null){
                    Alert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện sửa");
                }
                else{
                    Stage newstage = new Stage();
                    EditStudentMain screen = new EditStudentMain();
                    // ngăn tương tác với dashboard
                    Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    newstage.initModality(Modality.WINDOW_MODAL);
                    newstage.initOwner(oldStage);
                    // chạy newStage
                    try {
                        screen.start(newstage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void Alert(String title, String Message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        DialogPane root = alert.getDialogPane();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Main/main.css")).toExternalForm());
        root.getScene().setFill(Color.TRANSPARENT);
        Stage dialogStage = (Stage) root.getScene().getWindow();
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText(Message);
        alert.setHeaderText(null);
        ButtonType okBtn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okBtn);
        alert.show();
    }

    public Person selectRow() {
        if (studentTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRow = studentTableView.getSelectionModel().getSelectedItem();
        return selectedRow;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudentList();
        handle();
    }
}
