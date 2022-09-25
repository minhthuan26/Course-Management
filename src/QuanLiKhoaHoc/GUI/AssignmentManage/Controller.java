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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final AssignmentBUS assignmentBUS = new AssignmentBUS();
    private ObservableMap<Integer, String> teacherList;
    private ObservableMap<Integer, String> onsiteCourseList;
    private ObservableMap<Integer, String> onlineCourseList;
    private ObservableMap<Integer, String> allCourseList;
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
        showAssignmentList();
        Handle();
    }

    public void setDefault(){
        courseTypeChoiceBtn.setItems(types);
        courseTypeChoiceBtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();
        ObservableList<String> teacherNameList = FXCollections.observableArrayList();
        teacherList = getTeacherList();
        for(Map.Entry<Integer, String> teacher : teacherList.entrySet()){
            teacherNameList.add(teacher.getKey() + "_" + teacher.getValue());
        }
        teacherChoiceBtn.setItems(teacherNameList);
        teacherChoiceBtn.getSelectionModel().select(0);
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

    public ObservableMap<Integer, String> getTeacherList(){
        AssignmentBUS.teacherList = assignmentBUS.getTeacherList();
        teacherList = FXCollections.observableHashMap();
        for(Person person : AssignmentBUS.teacherList){
            teacherList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        }
        return teacherList;
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
                Assignment assignment = new Assignment(getSelectedCourseId(), getSelectedTeacherId());
                if(assignmentBUS.setAssignment(assignment) != null){
                    System.out.println("Thêm thành công");
                    setDefault();
                    showAssignmentList();
                }

                else
                    System.out.println("Thêm thật bại");
            }
        });

        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setDefault();
                showAssignmentList();
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Assignment assignment = new Assignment(selectRow().CourseId, selectRow().PersonId);
                if(assignmentBUS.deleteAssignment(assignment) != null){
                    System.out.println("Huỷ thành công");
                    setDefault();
                    showAssignmentList();
                }

                else
                    System.out.println("Huỷ thật bại");
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
        ObservableMap<Integer, String> allCourseListTmp = allCourseList;
        ObservableMap<Integer, String> teacherListTmp = teacherList;

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
            for(Map.Entry<Integer, String> teacher : teacherListTmp.entrySet()){
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
        return Integer.parseInt(courseChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    private int getSelectedTeacherId(){
        return Integer.parseInt(teacherChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    public AssignmentTableView selectRow(){
        if (assignmentTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRow = assignmentTableView.getSelectionModel().getSelectedItem();
        return selectedRow;
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
