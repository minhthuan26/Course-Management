package QuanLiKhoaHoc.GUI.AssignmentManage;

import QuanLiKhoaHoc.BUS.AssignmentManage.AssignmentBUS;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final AssignmentBUS assignmentBUS = new AssignmentBUS();
    private final ObservableList<String> types = FXCollections.observableArrayList(
            new String("Onsite"),
            new String("Online")
    );
    private static AssignmentBUS.AssignmentTableView selectedRow = null;

    @FXML
    private ChoiceBox<String> courseTypeChoiceBtn;

    @FXML
    private ChoiceBox<String> courseChoiceBtn;

    @FXML
    private ChoiceBox<String> teacherChoiceBtn;

    @FXML
    private Button assignBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<AssignmentBUS.AssignmentTableView> assignmentTableView;

    @FXML
    private TableColumn<AssignmentBUS.AssignmentTableView, Integer> courseIdTableColumn;

    @FXML
    private TableColumn<AssignmentBUS.AssignmentTableView, Integer> teacherIdTableColumn;

    @FXML
    private TableColumn<AssignmentBUS.AssignmentTableView, String> courseNameTableColumn;

    @FXML
    private TableColumn<AssignmentBUS.AssignmentTableView, String> teacherNameTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setDefault();
        Handle();
    }

    public void setDefault(){
        courseTypeChoiceBtn.setItems(types);
        courseTypeChoiceBtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();

        teacherChoiceBtn.setItems(assignmentBUS.getTeacherAssignmentListToGUI());
        teacherChoiceBtn.getSelectionModel().select(0);
        showAssignmentList();
    }

    public void chooseTypeOfCourse(){

        if(courseTypeChoiceBtn.getSelectionModel().getSelectedItem().equals("Onsite")){
            courseChoiceBtn.setItems(assignmentBUS.getOnsiteList());
            courseChoiceBtn.getSelectionModel().select(0);
        }
        else{
            courseChoiceBtn.setItems(assignmentBUS.getOnlineList());
            courseChoiceBtn.getSelectionModel().select(0);
        }
    }

    public void Handle(){
        courseTypeChoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfCourse();
            }
        });

        assignBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                int courseId = getSelectedCourseId();
                int teacherId =  getSelectedTeacherId();
                if(courseId < 0){
                    System.out.println("Thêm thất bại");
                    alert("Thông báo", "Tất cả các lớp đã được phân công giảng dạy");
                }
                else if(teacherId < 0){
                    System.out.println("Thêm thất bại");
                    alert("Thông báo", "Tất cả các giảng viên đã được phân công giảng dạy");
                }
                else{
                    Assignment assignment = new Assignment(courseId, teacherId);
                    if(assignmentBUS.setAssignment(assignment) != null){
                        System.out.println("Thêm thành công");
                        alert("Thông báo", "Thêm thành công");
                        setDefault();
                    }
                    else{
                        System.out.println("Thêm thất bại");
                        errorAlert("Lỗi", "Thêm thất bại");
                    }
                }
            }
        });

        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setDefault();
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedRow = selectedRow();
                if(selectedRow != null){
                    Assignment assignment = new Assignment(selectedRow.getCourseId(), selectedRow.getPersonId());
                    if(assignmentBUS.deleteAssignment(assignment) != null){
                        System.out.println("Huỷ thành công");
                        alert("Thông báo", "Đã huỷ phân công");
                        setDefault();
                    }
                    else{
                        System.out.println("Huỷ thất bại");
                        errorAlert("Lỗi", "Huỷ phân công thất bại");
                    }

                }
                else
                    errorAlert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện huỷ");
            }
        });
    }

    public void showAssignmentList(){
        ObservableList<AssignmentBUS.AssignmentTableView> assignmentTableViewList = assignmentBUS.getAssignmentTableViewList();
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentBUS.AssignmentTableView, Integer>("CourseId"));
        teacherIdTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentBUS.AssignmentTableView, Integer>("PersonId"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentBUS.AssignmentTableView, String>("CourseName"));
        teacherNameTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentBUS.AssignmentTableView, String>("PersonName"));
        assignmentTableView.setItems(assignmentTableViewList);
    }

    private int getSelectedCourseId(){
        if(courseChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(courseChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    private int getSelectedTeacherId(){
        if(teacherChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(teacherChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    public AssignmentBUS.AssignmentTableView selectedRow(){
        if (assignmentTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRow = assignmentTableView.getSelectionModel().getSelectedItem();
        return selectedRow;
    }

    private void errorAlert(String title, String Message){
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

    //hiển thị thông báo
    private void alert(String title, String Message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
}
