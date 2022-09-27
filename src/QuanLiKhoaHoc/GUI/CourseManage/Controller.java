package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
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
// KHai bao OnsiteCourse TableView
    @FXML
    private  TableView<OnsiteTableView> onsiteTableView;
    @FXML
    private TableColumn<OnsiteTableView, Integer> courseIdOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, String> courseNameOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, String> courseDescriptionOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Date> dateCreateOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Date> dateStartOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Date> dateEndOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Integer> onsiteCourseIdOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Integer> lessonQuantityOnsiteTableColumn;
    @FXML
    private TableColumn<OnsiteTableView, Date> dayOccurOnsiteTableColumn;

    private ObservableList<Course> allCourseList;
    private  ObservableList<OnsiteCourse> onsiteTableViewList;
    private ObservableList<OnlineCourse> onlineTableViewList;
    private CourseManageBUS courseManageBUS = new CourseManageBUS();
    private ObservableList<OnlineTableView> onlineTableViews;
    private ObservableList<OnsiteTableView> onsiteTableViews;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        showOnlineCourseList();
        showOnsiteCourseList();
    }
    // Lay tat ca du lieu trong db course
    public ObservableList<Course> getAllCourseList(){
        return allCourseList= courseManageBUS.getAllCourseList();
    }
    // Lay tat ca du lieu trong db Onlinecourse
    public ObservableList<OnlineCourse> getOnlineCourseList(){
        return onlineTableViewList= courseManageBUS.getAllOnlineCourseList();
    }
    // Lay tat ca du lieu trong db Onsitecourse
    public ObservableList<OnsiteCourse> getOnsiteCourseList(){
        return onsiteTableViewList = courseManageBUS.getAllOnsiteCourseList();
    }

    //Lay du lieu tu class merge cua Onsite
    public ObservableList<OnsiteTableView> getOnsiteTableView(){
        CourseManageBUS.allCourseList = courseManageBUS.getAllCourseList();
        onsiteTableViews = FXCollections.observableArrayList();
        allCourseList = getAllCourseList();
        onsiteTableViewList = getOnsiteCourseList();
        for (OnsiteCourse onsiteCourse : onsiteTableViewList){
            OnsiteTableView onsiteTableView = new OnsiteTableView();
            onsiteTableView.onsiteCourseIdOnsiteTableColumn = onsiteCourse.getOnsiteCoureId();
            onsiteTableView.courseIdOnsiteTableColumn = onsiteCourse.getCourseId();
            onsiteTableView.lessonQuantityOnsiteTableColumn = onsiteCourse.getLessonQuantity();
            onsiteTableView.dayOccurOnsiteTableColumn = onsiteCourse.getDayOccur();
            for (Course course: allCourseList){
                if (onsiteCourse.getCourseId() == course.getCourseId()){
                    onsiteTableView.courseNameOnsiteTableColumn = course.getCourseName();
                    onsiteTableView.courseDescriptionOnsiteTableColumn = course.getCourseDescription();
                    onsiteTableView.dateCreateOnsiteTableColumn = course.getDateCreate();
                    onsiteTableView.dateStartOnsiteTableColumn = course.getDateStart();
                    onsiteTableView.dateEndOnsiteTableColumn = course.getDateEnd();
                }
            }
        }
        return onsiteTableViews;
    }
    //Lay du lieu tu class merge cua Online
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
//                allCourseList.remove(course);
                break;
            }
        }
        return onlineTableViews;
    }

    //Do du lieu ra bang Onsite
    public void showOnsiteCourseList(){
        onsiteTableViews = getOnsiteTableView();
        courseIdOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Integer>("courseIdOnsiteTableColumn"));
        courseNameOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, String>("courseNameOnsiteTableColumn"));
        courseDescriptionOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, String>("courseDescriptionOnsiteTableColumn"));
        dateCreateOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Date>("dateCreateOnsiteTableColumn"));
        dateStartOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Date>("dateStartOnsiteTableColumn"));
        dateEndOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Date>("dateEndOnsiteTableColumn"));
        onsiteCourseIdOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Integer>("onsiteCourseIdOnsiteTableColumn"));
        lessonQuantityOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Integer>("lessonQuantityOnsiteTableColumn"));
        dayOccurOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<OnsiteTableView, Date>("dayOccurOnsiteTableColumn"));

        onsiteTableView.setItems(onsiteTableViews);
    }
    //Do du lieu ra bang Onsite
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

//Tao class moi merge 2 bang chung lai (Online + course)
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
    //Tao class moi merge 2 bang chung lai (Onsite + course)
    public class OnsiteTableView{

        public int getCourseIdOnsiteTableColumn() {
            return courseIdOnsiteTableColumn;
        }

        public void setCourseIdOnsiteTableColumn(int courseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
        }

        public int getOnsiteCourseIdOnsiteTableColumn() {
            return onsiteCourseIdOnsiteTableColumn;
        }

        public void setOnsiteCourseIdOnsiteTableColumn(int onsiteCourseIdOnsiteTableColumn) {
            this.onsiteCourseIdOnsiteTableColumn = onsiteCourseIdOnsiteTableColumn;
        }

        public int getLessonQuantityOnsiteTableColumn() {
            return lessonQuantityOnsiteTableColumn;
        }

        public void setLessonQuantityOnsiteTableColumn(int lessonQuantityOnsiteTableColumn) {
            this.lessonQuantityOnsiteTableColumn = lessonQuantityOnsiteTableColumn;
        }

        public String getCourseDescriptionOnsiteTableColumn() {
            return courseDescriptionOnsiteTableColumn;
        }

        public void setCourseDescriptionOnsiteTableColumn(String courseDescriptionOnsiteTableColumn) {
            this.courseDescriptionOnsiteTableColumn = courseDescriptionOnsiteTableColumn;
        }

        public String getCourseNameOnsiteTableColumn() {
            return courseNameOnsiteTableColumn;
        }

        public void setCourseNameOnsiteTableColumn(String courseNameOnsiteTableColumn) {
            this.courseNameOnsiteTableColumn = courseNameOnsiteTableColumn;
        }

        public Date getDateStartOnsiteTableColumn() {
            return dateStartOnsiteTableColumn;
        }

        public void setDateStartOnsiteTableColumn(Date dateStartOnsiteTableColumn) {
            this.dateStartOnsiteTableColumn = dateStartOnsiteTableColumn;
        }

        public Date getDateEndOnsiteTableColumn() {
            return dateEndOnsiteTableColumn;
        }

        public void setDateEndOnsiteTableColumn(Date dateEndOnsiteTableColumn) {
            this.dateEndOnsiteTableColumn = dateEndOnsiteTableColumn;
        }

        public Date getDayOccurOnsiteTableColumn() {
            return dayOccurOnsiteTableColumn;
        }

        public void setDayOccurOnsiteTableColumn(Date dayOccurOnsiteTableColumn) {
            this.dayOccurOnsiteTableColumn = dayOccurOnsiteTableColumn;
        }

        public Date getDateCreateOnsiteTableColumn() {
            return dateCreateOnsiteTableColumn;
        }

        public void setDateCreateOnsiteTableColumn(Date dateCreateOnsiteTableColumn) {
            this.dateCreateOnsiteTableColumn = dateCreateOnsiteTableColumn;
        }

        int courseIdOnsiteTableColumn, onsiteCourseIdOnsiteTableColumn, lessonQuantityOnsiteTableColumn;
        String courseDescriptionOnsiteTableColumn, courseNameOnsiteTableColumn;
        Date dateStartOnsiteTableColumn, dateEndOnsiteTableColumn, dayOccurOnsiteTableColumn, dateCreateOnsiteTableColumn;
    }

}
