package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    // Khai bao OnlineCourse TableView
    @FXML
    private TableView<OnlineTableView> onlineTableView;
    @FXML
    private TableColumn<OnlineTableView, Integer> courseIdOnlineTableColumn;
    @FXML
    private TableColumn<OnlineTableView, String> courseNameOnlineTableColumn;
    @FXML
    private TableColumn<OnlineTableView, String> courseDescriptionOnlineTableColumn;
    @FXML
    private TableColumn<OnlineTableView, Date> dateCreateOnlineTableView;
    @FXML
    private TableColumn<OnlineTableView, Date> dateStartOnlineTableView;
    @FXML
    private TableColumn<OnlineTableView, Date> dateEndOnlineTableView;
    @FXML
    private TableColumn<OnlineTableView, Integer> onlineCourseIdOnlineTableColumn;
    @FXML
    private TableColumn<OnlineTableView, Integer> courseUrlOnlineTableColumn;

    private ObservableList<Course> allCourseList;
    private ObservableList<OnlineCourse> onlineTableViewList;
    private CourseManageBUS courseManageBUS = new CourseManageBUS();
    private ObservableList<OnlineTableView> onlineTableViews;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        showOnlineCourseList();
    }

    public ObservableList<Course> getAllCourseList(){
        return allCourseList= courseManageBUS.getAllCourseList();
    }
    public ObservableList<OnlineCourse> getOnlineCourseList(){
        return onlineTableViewList= courseManageBUS.getAllOnlineCourseList();
    }
    public void showOnlineCourseList(){
        onlineTableViews = getOnlineTableView();
        courseIdOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Integer>("courseIdOnlineTableColumn"));
        courseNameOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<OnlineTableView, String>("courseNameOnlineTableColumn"));
        courseDescriptionOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<OnlineTableView, String>("courseDescriptionOnlineTableColumn"));
        dateCreateOnlineTableView.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Date>("dateCreateOnlineTableView"));
        dateStartOnlineTableView.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Date>("dateStartOnlineTableView"));
        dateEndOnlineTableView.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Date>("dateEndOnlineTableView"));
        onlineCourseIdOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Integer>("onlineCourseIdOnlineTableColumn"));
        courseUrlOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<OnlineTableView, Integer>("courseUrlOnlineTableColumn"));
        onlineTableView.setItems(onlineTableViews);
    }

    public ObservableList<OnlineTableView> getOnlineTableView(){
        CourseManageBUS.allCourseList = courseManageBUS.getAllCourseList();
        onlineTableViews = FXCollections.observableArrayList();
        allCourseList = getAllCourseList();
        onlineTableViewList = getOnlineCourseList();
        for (OnlineCourse onlineCourse : onlineTableViewList){
            OnlineTableView onlineTableView = new OnlineTableView();
            onlineTableView.courseIdOnlineTableColumn = onlineCourse.getCourseId();
            onlineTableView.onlineCourseIdOnlineTableColumn = onlineCourse.getOnlineCourseId();
            onlineTableView.courseUrlOnlineTableColumn = onlineCourse.getCourseUrl();
            for (Course course : allCourseList){
                if (course.getCourseId()==onlineCourse.getCourseId()){
                    onlineTableView.dateCreateOnlineTableView = course.getDateCreate();
                    onlineTableView.dateStartOnlineTableView = course.getDateStart();
                    onlineTableView.dateEndOnlineTableView = course.getDateEnd();
                    onlineTableView.courseNameOnlineTableColumn = course.getCourseName();
                    onlineTableView.courseDescriptionOnlineTableColumn = course.getCourseDescription();
                    onlineTableView.courseImageOnlineTableColumn = course.getCourseImage();
                }
                onlineTableViews.add(onlineTableView);
                allCourseList.remove(course);
                break;
            }
        }
         return onlineTableViews;
    }

    public class OnlineTableView{
        public int getCourseIdOnlineTableColumn() {
            return courseIdOnlineTableColumn;
        }

        public void setCourseIdOnlineTableColumn(int courseIdOnlineTableColumn) {
            this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
        }

        public int getOnlineCourseIdOnlineTableColumn() {
            return onlineCourseIdOnlineTableColumn;
        }

        public void setOnlineCourseIdOnlineTableColumn(int onlineCourseIdOnlineTableColumn) {
            this.onlineCourseIdOnlineTableColumn = onlineCourseIdOnlineTableColumn;
        }

        public String getCourseNameOnlineTableColumn() {
            return courseNameOnlineTableColumn;
        }

        public void setCourseNameOnlineTableColumn(String courseNameOnlineTableColumn) {
            this.courseNameOnlineTableColumn = courseNameOnlineTableColumn;
        }

        public String getCourseDescriptionOnlineTableColumn() {
            return courseDescriptionOnlineTableColumn;
        }

        public void setCourseDescriptionOnlineTableColumn(String courseDescriptionOnlineTableColumn) {
            this.courseDescriptionOnlineTableColumn = courseDescriptionOnlineTableColumn;
        }

        public String getCourseImageOnlineTableColumn() {
            return courseImageOnlineTableColumn;
        }

        public void setCourseImageOnlineTableColumn(String courseImageOnlineTableColumn) {
            this.courseImageOnlineTableColumn = courseImageOnlineTableColumn;
        }

        public String getCourseUrlOnlineTableColumn() {
            return courseUrlOnlineTableColumn;
        }

        public void setCourseUrlOnlineTableColumn(String courseUrlOnlineTableColumn) {
            this.courseUrlOnlineTableColumn = courseUrlOnlineTableColumn;
        }

        public Date getDateCreateOnlineTableView() {
            return dateCreateOnlineTableView;
        }

        public void setDateCreateOnlineTableView(Date dateCreateOnlineTableView) {
            this.dateCreateOnlineTableView = dateCreateOnlineTableView;
        }

        public Date getDateStartOnlineTableView() {
            return dateStartOnlineTableView;
        }

        public void setDateStartOnlineTableView(Date dateStartOnlineTableView) {
            this.dateStartOnlineTableView = dateStartOnlineTableView;
        }

        public Date getDateEndOnlineTableView() {
            return dateEndOnlineTableView;
        }

        public void setDateEndOnlineTableView(Date dateEndOnlineTableView) {
            this.dateEndOnlineTableView = dateEndOnlineTableView;
        }

        int courseIdOnlineTableColumn, onlineCourseIdOnlineTableColumn;
        String courseNameOnlineTableColumn, courseDescriptionOnlineTableColumn, courseImageOnlineTableColumn, courseUrlOnlineTableColumn;
        Date dateCreateOnlineTableView, dateStartOnlineTableView, dateEndOnlineTableView;
    }
}
