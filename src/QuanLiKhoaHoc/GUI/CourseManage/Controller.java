package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import QuanLiKhoaHoc.GUI.StudentManage.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {
//    @FXML
//    private AnchorPane mainPane;
//    @FXML
//    private HBox hBoxTool;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRefresh;
    @FXML
    private ChoiceBox<String> courseType;
    @FXML
    private SplitPane splitPane;


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
    private ObservableList<String> typeCourse = FXCollections.observableArrayList(
            new String("Online"),
            new String("Onsite")
    );
    private ObservableList<Course> allCourseList;
    private  ObservableList<OnsiteCourse> onsiteTableViewList;
    private ObservableList<OnlineCourse> onlineTableViewList;
    private CourseManageBUS courseManageBUS = new CourseManageBUS();
    private ObservableList<OnlineTableView> onlineTableViews;
    private ObservableList<OnsiteTableView> onsiteTableViews;
    private static OnlineTableView selectedRowOnline = null ;
    private static OnsiteTableView selectedRowOnsite = null;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Handle();
        showOnlineCourseList();
        showOnsiteCourseList();

        courseType.setItems(typeCourse);
        courseType.getSelectionModel().selectFirst();
        chooseTypeOfCourse();

    }

    private void chooseTypeOfCourse(){
        if (courseType.getSelectionModel().getSelectedItem().equals("Onsite")){
            splitPane.setDividerPositions(0);
        }else {
            splitPane.setDividerPositions(1);
        }
    }


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

    public  OnlineTableView selectRowOnline(){
        if (onlineTableView.getSelectionModel().getSelectedIndex()<0)
            return null;
        selectedRowOnline = onlineTableView.getSelectionModel().getSelectedItem();
        return selectedRowOnline;

    }
    public  OnsiteTableView selectRowOnsite(){
        if (onsiteTableView.getSelectionModel().getSelectedIndex()<0)
            return null;
        selectedRowOnsite = onsiteTableView.getSelectionModel().getSelectedItem();
        return selectedRowOnsite;

    }
    public void Handle(){
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showOnsiteCourseList();
                showOnlineCourseList();
            }
        });
        courseType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfCourse();
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                selectedRowOnline = selectRowOnline();
                if(selectedRowOnline != null){
                    Assignment idOfAssignment= courseManageBUS.getIdAssignment(selectedRowOnline.courseIdOnlineTableColumn);
                    if (idOfAssignment!=null){
                        alert("Thông báo", "Vui lòng hủy phân công trước khi xóa");

                    }
                    else{
                        Course onlineCourse = courseManageBUS.deleteOnlineCourseTest(selectedRowOnline.getCourseIdOnlineTableColumn());
                        if (onlineCourse != null){
                            Course course = courseManageBUS.deleteCourseOnline(onlineCourse);
                            if (course != null){
                                alert("Thông báo", "Xóa thành công");
                                System.out.println(course);
                                showOnlineCourseList();
                            }
                            else {
                                alert("Thông báo", "Thất bại");
                            }
                    }

                    }
                }
                selectedRowOnsite = selectRowOnsite();
                if(selectedRowOnsite != null){
                    Assignment idOfAssignment= courseManageBUS.getIdAssignment(selectedRowOnsite.courseIdOnsiteTableColumn);
                    if (idOfAssignment!=null){
                        alert("Thông báo", "Vui lòng hủy phân công trước khi xóa");

                    }
                    else {
                        Course onsiteCourse = courseManageBUS.deleteOnsiteCourseTest(selectRowOnsite().getCourseIdOnsiteTableColumn());

                        if (onsiteCourse != null){
                            Course course = courseManageBUS.deleteCourseOnline(onsiteCourse);
                            if (course != null){
                                alert("Thông báo", "Xóa thành công");
                                System.out.println("Xoa Thanh cong");
                                showOnsiteCourseList();
                            }
                            else {
                                alert("Thông báo", "Thất bại");
                            }

                        }

                    }
                }
            }
        });

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                FXMLLoader loader = new FXMLLoader();
               Stage newstage = new Stage();
               MainAddCourse screen = new MainAddCourse();
               Stage oldstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//               AddCourseController addCourseController = loader.getController();
//               addCourseController.Handle();
               newstage.initModality(Modality.WINDOW_MODAL);
               newstage.initOwner(oldstage);

                try {
                    screen.start(newstage);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
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
            onsiteTableViews.add(onsiteTableView);

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
            }
            onlineTableViews.add(onlineTableView);

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

    public LocalDate getDateCreateOnlineTableView() {
        return dateCreateOnlineTableView;
    }

    public void setDateCreateOnlineTableView(LocalDate dateCreateOnlineTableView) {
        this.dateCreateOnlineTableView = dateCreateOnlineTableView;
    }

    public LocalDate getDateStartOnlineTableView() {
        return dateStartOnlineTableView;
    }

    public void setDateStartOnlineTableView(LocalDate dateStartOnlineTableView) {
        this.dateStartOnlineTableView = dateStartOnlineTableView;
    }

    public LocalDate getDateEndOnlineTableView() {
        return dateEndOnlineTableView;
    }

    public void setDateEndOnlineTableView(LocalDate dateEndOnlineTableView) {
        this.dateEndOnlineTableView = dateEndOnlineTableView;
    }

    public OnlineTableView() {
    }
    public OnlineTableView(int courseIdOnlineTableColumn, int onlineCourseIdOnlineTableColumn) {
        this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
        this.onlineCourseIdOnlineTableColumn = onlineCourseIdOnlineTableColumn;
    }

    int courseIdOnlineTableColumn, onlineCourseIdOnlineTableColumn;

    public OnlineTableView(int courseIdOnlineTableColumn) {
        this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
    }

    String courseNameOnlineTableColumn, courseDescriptionOnlineTableColumn, courseImageOnlineTableColumn, courseUrlOnlineTableColumn;
        LocalDate dateCreateOnlineTableView, dateStartOnlineTableView, dateEndOnlineTableView;
    }
    //Tao class moi merge 2 bang chung lai (Onsite + course)
    public class OnsiteTableView    {

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



        int courseIdOnsiteTableColumn;

        public OnsiteTableView(int courseIdOnsiteTableColumn, int onsiteCourseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
            this.onsiteCourseIdOnsiteTableColumn = onsiteCourseIdOnsiteTableColumn;
        }
        public OnsiteTableView(){}

        public OnsiteTableView(int courseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
        }

        int onsiteCourseIdOnsiteTableColumn;
        int lessonQuantityOnsiteTableColumn;
        String courseDescriptionOnsiteTableColumn, courseNameOnsiteTableColumn;

        public LocalDate getDateStartOnsiteTableColumn() {
            return dateStartOnsiteTableColumn;
        }

        public void setDateStartOnsiteTableColumn(LocalDate dateStartOnsiteTableColumn) {
            this.dateStartOnsiteTableColumn = dateStartOnsiteTableColumn;
        }

        public LocalDate getDateEndOnsiteTableColumn() {
            return dateEndOnsiteTableColumn;
        }

        public void setDateEndOnsiteTableColumn(LocalDate dateEndOnsiteTableColumn) {
            this.dateEndOnsiteTableColumn = dateEndOnsiteTableColumn;
        }

        public LocalDate getDayOccurOnsiteTableColumn() {
            return dayOccurOnsiteTableColumn;
        }

        public void setDayOccurOnsiteTableColumn(LocalDate dayOccurOnsiteTableColumn) {
            this.dayOccurOnsiteTableColumn = dayOccurOnsiteTableColumn;
        }

        public LocalDate getDateCreateOnsiteTableColumn() {
            return dateCreateOnsiteTableColumn;
        }

        public void setDateCreateOnsiteTableColumn(LocalDate dateCreateOnsiteTableColumn) {
            this.dateCreateOnsiteTableColumn = dateCreateOnsiteTableColumn;
        }

        LocalDate dateStartOnsiteTableColumn, dateEndOnsiteTableColumn, dayOccurOnsiteTableColumn, dateCreateOnsiteTableColumn;
    }

}
