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

public class EditCourseOnlineController implements Initializable {
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
    private TextField urlTextField;
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
        courseNameTextField.setText(controller.selectedRowOnline.getCourseNameOnlineTableColumn());
        descriptionTextField.setText(controller.selectedRowOnline.getCourseDescriptionOnlineTableColumn());
        urlTextField.setText(controller.selectedRowOnline.getCourseUrlOnlineTableColumn());
        dateEndtPicker.setValue(controller.selectedRowOnline.getDateEndOnlineTableView());
        dateStartPicker.setValue(controller.selectedRowOnline.getDateStartOnlineTableView());

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
                Course course = courseManageBUS.editCourse(courseName,courseDesc,dateCreate,dateStart,dateEnd, controller.selectedRowOnline.getCourseIdOnlineTableColumn());
                if (course!= null){
                    String url = urlTextField.getText();
                    Course online = courseManageBUS.editOnline(url,controller.selectedRowOnline.getCourseIdOnlineTableColumn());
                    if (online!=null){
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
