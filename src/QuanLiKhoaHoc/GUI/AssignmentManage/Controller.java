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
    private ObservableMap<Integer, String> teacherAssignmentList;
    private ObservableMap<Integer, String> onsiteCourseList;
    private ObservableMap<Integer, String> onlineCourseList;
    private ObservableMap<Integer, String> allCourseList;
    private ObservableMap<Integer, String> allTeacherList;
    private ObservableList<AssignmentTableView> assignmentTableViewList;
    private final ObservableList<String> types = FXCollections.observableArrayList(
            new String("Onsite"),
            new String("Online")
    );
    private static AssignmentTableView selectedRow = null;

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
    private TableView<AssignmentTableView> assignmentTableView;

    @FXML
    private TableColumn<AssignmentTableView, Integer> courseIdTableColumn;

    @FXML
    private TableColumn<AssignmentTableView, Integer> teacherIdTableColumn;

    @FXML
    private TableColumn<AssignmentTableView, String> courseNameTableColumn;

    @FXML
    private TableColumn<AssignmentTableView, String> teacherNameTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setDefault();
        Handle();
    }

    public void setDefault(){
        courseTypeChoiceBtn.setItems(types);
        courseTypeChoiceBtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();
        ObservableList<String> teacherNameList = FXCollections.observableArrayList();
        teacherAssignmentList = getTeacherAssignmentList();
        for(Map.Entry<Integer, String> teacher : teacherAssignmentList.entrySet()){
            teacherNameList.add(teacher.getKey() + "_" + teacher.getValue());
        }
        teacherChoiceBtn.setItems(teacherNameList);
        teacherChoiceBtn.getSelectionModel().select(0);
        showAssignmentList();
    }

    public ObservableMap<Integer, String> getOnsiteList(){
        AssignmentBUS.allCourseList = assignmentBUS.getAllCourseList();
        AssignmentBUS.onsiteCourseList = assignmentBUS.getOnsiteCourseList();
        onsiteCourseList = FXCollections.observableHashMap();
        for(OnsiteCourse onsiteCourse : AssignmentBUS.onsiteCourseList){
            for(Course course : AssignmentBUS.allCourseList)
                if(course.getCourseId() == onsiteCourse.getCourseId()){
                    onsiteCourseList.put(course.getCourseId(), course.getCourseName());
                    break;
                }

        }
        return onsiteCourseList;
    }

    public ObservableMap<Integer, String> getOnlineList(){
        AssignmentBUS.allCourseList = assignmentBUS.getAllCourseList();
        AssignmentBUS.onlineCourseList = assignmentBUS.getOnlineCourseList();
        onlineCourseList = FXCollections.observableHashMap();
        for(OnlineCourse onlineCourse : AssignmentBUS.onlineCourseList){
            for(Course course : AssignmentBUS.allCourseList)
                if(course.getCourseId() == onlineCourse.getCourseId()){
                    onlineCourseList.put(course.getCourseId(), course.getCourseName());
                    break;
                }

        }
        return onlineCourseList;
    }

    public ObservableMap<Integer, String> getAllCourseList(){
        AssignmentBUS.allCourseList = assignmentBUS.getAllCourseList();
        allCourseList = FXCollections.observableHashMap();
        for(Course course : AssignmentBUS.allCourseList)
            allCourseList.put(course.getCourseId(), course.getCourseName());
        return allCourseList;
    }

    public ObservableMap<Integer, String> getAllTeacherList(){
        AssignmentBUS.allTeacherList = assignmentBUS.getAllTeacherList();
        allTeacherList = FXCollections.observableHashMap();
        for(Person person : AssignmentBUS.allTeacherList)
            allTeacherList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        return allTeacherList;
    }

    public ObservableMap<Integer, String> getTeacherAssignmentList(){
        AssignmentBUS.teacherAssignmentList = assignmentBUS.getTeacherAssignmentList();
        teacherAssignmentList = FXCollections.observableHashMap();
        for(Person person : AssignmentBUS.teacherAssignmentList){
            teacherAssignmentList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        }
        return teacherAssignmentList;
    }

    private void chooseTypeOfCourse(){

        if(courseTypeChoiceBtn.getSelectionModel().getSelectedItem().equals("Onsite")){
            ObservableList<String> onsiteCourseNameList = FXCollections.observableArrayList();
            onsiteCourseList = getOnsiteList();
            for(Map.Entry<Integer, String> onsiteCourse : onsiteCourseList.entrySet()){
                onsiteCourseNameList.add(onsiteCourse.getKey() + "_" + onsiteCourse.getValue());
            }
            courseChoiceBtn.setItems(onsiteCourseNameList);
            courseChoiceBtn.getSelectionModel().select(0);
        }
        else{
            ObservableList<String> onlineCourseNameList = FXCollections.observableArrayList();
            onlineCourseList = getOnlineList();
            for(Map.Entry<Integer, String> onlineCourse : onlineCourseList.entrySet()){
                onlineCourseNameList.add(onlineCourse.getKey() + "_" + onlineCourse.getValue());
            }
            courseChoiceBtn.setItems(onlineCourseNameList);
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
                    System.out.println("Thêm thật bại");
                    alert("Thông báo", "Tất cả các lớp đã được phân công giảng dạy");
                }
                else if(teacherId < 0){
                    System.out.println("Thêm thật bại");
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
                        System.out.println("Thêm thật bại");
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
                selectedRow = selectRow();
                if(selectedRow != null){
                    Assignment assignment = new Assignment(selectedRow.CourseId, selectedRow.PersonId);
                    if(assignmentBUS.deleteAssignment(assignment) != null){
                        System.out.println("Huỷ thành công");
                        alert("Thông báo", "Đã huỷ phân công");
                        setDefault();
                    }
                    else{
                        System.out.println("Huỷ thật bại");
                        errorAlert("Lỗi", "Huỷ phân công thất bại");
                    }

                }
                else
                    errorAlert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện huỷ");
            }
        });
    }

    public void showAssignmentList(){
        assignmentTableViewList = getAssignmentTableViewList();
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentTableView, Integer>("CourseId"));
        teacherIdTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentTableView, Integer>("PersonId"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentTableView, String>("CourseName"));
        teacherNameTableColumn.setCellValueFactory(new PropertyValueFactory<AssignmentTableView, String>("PersonName"));
        assignmentTableView.setItems(assignmentTableViewList);
    }

    public ObservableList<AssignmentTableView> getAssignmentTableViewList(){
        AssignmentBUS.assignmentList = assignmentBUS.getAssignmentList();
        assignmentTableViewList = FXCollections.observableArrayList();
        allCourseList = getAllCourseList();
        allTeacherList = getAllTeacherList();
        ObservableMap<Integer, String> allCourseListTmp = allCourseList;
        ObservableMap<Integer, String> allTeacherListTmp = allTeacherList;

        for(Assignment assignment : AssignmentBUS.assignmentList){
            AssignmentTableView assignmentTableView = new AssignmentTableView();
            for(Map.Entry<Integer, String> course : allCourseListTmp.entrySet()){
                if(assignment.getCourseId() == course.getKey()){
                    assignmentTableView.CourseId = course.getKey();
                    assignmentTableView.CourseName = course.getValue();
//                    allCourseListTmp.remove(course.getKey());
                    break;
                }
            }
            for(Map.Entry<Integer, String> teacher : allTeacherListTmp.entrySet()){
                if(assignment.getPersonId() == teacher.getKey()){
                    assignmentTableView.PersonId = teacher.getKey();
                    assignmentTableView.PersonName = teacher.getValue();
//                    teacherListTmp.remove(teacher.getKey());
                    break;
                }
            }
            assignmentTableViewList.add(assignmentTableView);
        }
        return assignmentTableViewList;
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

    public AssignmentTableView selectRow(){
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

    public static class AssignmentTableView{
        private int CourseId, PersonId;
        private String CourseName, PersonName;

        public int getCourseId() {
            return CourseId;
        }

        public void setCourseId(int courseId) {
            CourseId = courseId;
        }

        public int getPersonId() {
            return PersonId;
        }

        public void setPersonId(int personId) {
            PersonId = personId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String courseName) {
            CourseName = courseName;
        }

        public String getPersonName() {
            return PersonName;
        }

        public void setPersonName(String personName) {
            PersonName = personName;
        }

        @Override
        public String toString() {
            return "AssignmentTableView{" +
                    "CourseId=" + CourseId +
                    ", PersonId=" + PersonId +
                    ", CourseName='" + CourseName + '\'' +
                    ", PersonName='" + PersonName + '\'' +
                    '}';
        }
    }
}
