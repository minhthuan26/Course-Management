package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {
    @FXML
    private AnchorPane addCoursePane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox checkboxOnsite;

    @FXML
    private TextField courseNameTextField;

    @FXML
    private DatePicker dateEndtPicker;

    @FXML
    private DatePicker dateOccurPicker;

    @FXML
    private DatePicker dateStartPicker;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField lessonQuantityTextField;

    @FXML
    private SplitPane splitPane;

    @FXML
    private TextField urlTextField;

    private CourseManageBUS courseManageBUS = new CourseManageBUS();
    public Stage primaryStage;
     private static boolean checkOnsite;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Default();
        Handle();
    }


    public void Handle (){

        checkboxOnsite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkOnsite = checkboxOnsite.isSelected();
                    if (checkOnsite) {
                        splitPane.setDividerPositions(0);
                    } else {
                        splitPane.setDividerPositions(1);
                    }
                }

        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String courseName = courseNameTextField.getText();
                String courseDesc = descriptionTextField.getText();
                LocalDate dateEnd = LocalDate.parse(dateEndtPicker.getEditor().getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dateStart = LocalDate.parse(dateStartPicker.getEditor().getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dateCreate = LocalDate.now();
                Course course = courseManageBUS.addCourse(courseName, courseDesc, dateCreate,dateStart,dateEnd);
                if (course!=null){
                    System.out.println("Done");
                    if (checkOnsite){
                        int lesson = Integer.parseInt( lessonQuantityTextField.getText());
                        LocalDate dateOccur = LocalDate.parse(dateOccurPicker.getEditor().getText(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        Course onsiteCourse = courseManageBUS.addOnsiteCourse(course.getCourseId(),lesson,   dateOccur  );
                        if (onsiteCourse!=null){
                            System.out.println("Done onsite");
                            primaryStage = (Stage) btnSave.getScene().getWindow();
                            primaryStage.close();
                        }
                        else {
                            System.out.println("fail");
                            course = courseManageBUS.deleteCourseOnsite(course);

                        }
                    }
                    else {
                        Course onlineCourse = courseManageBUS.addOnlineCourse(course.getCourseId(),urlTextField.getText());
                        if (onlineCourse!=null){
                            System.out.println("Done online");
                            primaryStage = (Stage) btnSave.getScene().getWindow();
                            primaryStage.close();


                        }else {
                            System.out.println("Fail");
                            course = courseManageBUS.deleteCourseOnline(course);

                        }
                    }

                }else {
                    System.out.println("Fail");
                }
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
}
