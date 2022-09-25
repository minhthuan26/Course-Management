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

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    AssignmentBUS assignmentBUS = new AssignmentBUS();
    ObservableMap<Integer, String> teacherList;
    ObservableMap<Integer, String> onsiteCourseList;
    ObservableMap<Integer, String> onlineCourseList;
    ObservableMap<Integer, String> allCourseList;
    @FXML
    private ChoiceBox<String> courseTypeChoiceBtn;

    @FXML
    private ChoiceBox<String> courseChoiceBtn;

    @FXML
    private ChoiceBox<String> teacherChoiceBtn;

    @FXML
    private Button assignBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setDefault();
        Handle();
    }

    public void setDefault(){
        ObservableList<String> types = FXCollections.observableArrayList(
                new String("Onsite"),
                new String("Online")
        );
        courseTypeChoiceBtn.setItems(types);
        courseTypeChoiceBtn.getSelectionModel().select(0);

        ObservableList<String> teacherNameList = FXCollections.observableArrayList();
        teacherList = getTeacherList();
        for(Map.Entry<Integer, String> teacher : teacherList.entrySet()){
            teacherNameList.add(teacher.getKey() + "_" + teacher.getValue());
        }
        teacherChoiceBtn.setItems(teacherNameList);
        teacherChoiceBtn.getSelectionModel().select(0);

        chooseTypeOfCourse();
    }

    public ObservableMap<Integer, String> getOnsiteList(){
        AssignmentBUS.allCourseList = assignmentBUS.getAllCourseList();
        AssignmentBUS.onsiteCourseList = assignmentBUS.getOnsiteCourseList();
        onsiteCourseList = FXCollections.observableHashMap();
        for(OnsiteCourse onsiteCourse : AssignmentBUS.onsiteCourseList){
            for(Course course : AssignmentBUS.allCourseList)
                if(course.getCourseId() == onsiteCourse.getCourseId())
                    onsiteCourseList.put(course.getCourseId(), course.getCourseName());
        }
        return onsiteCourseList;
    }

    public ObservableMap<Integer, String> getOnlineList(){
        AssignmentBUS.allCourseList = assignmentBUS.getAllCourseList();
        AssignmentBUS.onlineCourseList = assignmentBUS.getOnlineCourseList();
        onlineCourseList = FXCollections.observableHashMap();
        for(OnlineCourse onlineCourse : AssignmentBUS.onlineCourseList){
            for(Course course : AssignmentBUS.allCourseList)
                if(course.getCourseId() == onlineCourse.getCourseId())
                    onlineCourseList.put(course.getCourseId(), course.getCourseName());
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
                Assignment assignment = new Assignment(getCourseId(), getTeacherId());
                if(assignmentBUS.setAssignment(assignment) != null)
                    System.out.println("Thêm thành công");
                else
                    System.out.println("Thêm thật bại");
            }
        });
    }

    private int getCourseId(){
        return Integer.parseInt(courseChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    private int getTeacherId(){
        return Integer.parseInt(teacherChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }
}
