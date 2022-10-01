package QuanLiKhoaHoc.GUI.StudentManage;

import QuanLiKhoaHoc.BUS.StudentManage.StudentBUS;
import QuanLiKhoaHoc.DTO.Assignment;
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
    ObservableList<Person> studentList;
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
    private Button btnRefresh;

    private static Person selectedRow = null;

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
                    Person person = new Person(selectedRow.getPersonId(), selectedRow.getFirstName(), selectedRow.getLastName(),
                            selectedRow.getEmail(), selectedRow.getPhoneNumber(), selectedRow.getDateOfBirth(), selectedRow.getPersonImage());
                    studentBUS.deletePersonRole(selectedRow.getPersonId());
                    studentBUS.deletePerson(person);
                    System.out.println("Xóa thành công");
                    Alert("Thành công", "Xóa sinh viên thành công");
//                    showStudentList();
                } else {
                    Alert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện huỷ");
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
