package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.Course;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditCourseOnsiteController implements Initializable {
    @FXML
    private AnchorPane addCoursePane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField courseNameTextField ;

    @FXML
    private DatePicker dateEndtPicker;

    @FXML
    private DatePicker dateStartPicker;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker dateOccurPicker;

    @FXML
    private TextField lessonQuanTextField;
    private CourseManageBUS.OnlineTableView onlineTableView;
    public Stage primaryStage;
    public Controller controller = new Controller();
    private final CourseManageBUS courseManageBUS = new CourseManageBUS();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
        Handle();
    }
    public void show(){
        courseNameTextField.setText(controller.selectedRowOnsite.getCourseNameOnsiteTableColumn());
        descriptionTextField.setText(controller.selectedRowOnsite.getCourseDescriptionOnsiteTableColumn());
        lessonQuanTextField.setText(String.valueOf(controller.selectedRowOnsite.getLessonQuantityOnsiteTableColumn()));
        dateEndtPicker.setValue(controller.selectedRowOnsite.getDateEndOnsiteTableColumn());
        dateStartPicker.setValue(controller.selectedRowOnsite.getDateStartOnsiteTableColumn());
        dateOccurPicker.setValue(controller.selectedRowOnsite.getDayOccurOnsiteTableColumn());

    }
    public void Handle(){
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage = (Stage) btnCancel.getScene().getWindow();
                primaryStage.close();
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String courseName = courseNameTextField.getText();
                String courseDesc = descriptionTextField.getText();
                LocalDate dateEnd = LocalDate.parse(dateEndtPicker.getEditor().getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dateStart = LocalDate.parse(dateStartPicker.getEditor().getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dateCreate = LocalDate.now();
                Course course = courseManageBUS.editCourse(courseName,courseDesc,dateCreate,dateStart,dateEnd, controller.selectedRowOnsite.getCourseIdOnsiteTableColumn());
                if (course!= null){
                    LocalDate occur = LocalDate.parse(dateOccurPicker.getEditor().getText(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    int lesson = Integer.parseInt(lessonQuanTextField.getText());
                    Course onsite = courseManageBUS.editOnsite(lesson,occur, controller.selectedRowOnsite.getCourseIdOnsiteTableColumn());
                    if (onsite!=null){
                        System.out.println("Thanh cong");
                    }
                    else {
                        System.out.println("That Bai Online");
                    }
                }
                else {
                    System.out.println("That bai Course");
                }
                primaryStage = (Stage) btnCancel.getScene().getWindow();
                primaryStage.close();
            }
        });


    }
}
