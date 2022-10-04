package QuanLiKhoaHoc.GUI.CourseManage;

import QuanLiKhoaHoc.BUS.CourseManage.CourseManageBUS;
import QuanLiKhoaHoc.DTO.*;
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
    private Button btnEdit;
    @FXML
    private ChoiceBox<String> courseType;
    @FXML
    private SplitPane splitPane;





    // Khai bao OnlineCourse TableView
    @FXML
    private TableView<CourseManageBUS.OnlineTableView> onlineTableView;
    @FXML
    public TableColumn<CourseManageBUS.OnlineTableView, Integer> courseIdOnlineTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, String> courseNameOnlineTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, String> courseDescriptionOnlineTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Date> dateCreateOnlineTableView;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Date> dateStartOnlineTableView;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Date> dateEndOnlineTableView;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Integer> onlineCourseIdOnlineTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Integer> courseUrlOnlineTableColumn;
    // KHai bao OnsiteCourse TableView
    @FXML
    private TableView<CourseManageBUS.OnsiteTableView> onsiteTableView;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Integer> courseIdOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, String> courseNameOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, String> courseDescriptionOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Date> dateCreateOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Date> dateStartOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Date> dateEndOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Integer> onsiteCourseIdOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Integer> lessonQuantityOnsiteTableColumn;
    @FXML
    private TableColumn<CourseManageBUS.OnsiteTableView, Date> dayOccurOnsiteTableColumn;





    ///////////////////////////////////////////////
    @FXML
    private ChoiceBox<String> searchChoiceBoxbtn;

    @FXML
    private Button searchbtn;
    private final ObservableList<String> searchTypes = FXCollections.observableArrayList(
            new String("Khoá học")
    );

    private final ObservableList<String> typeCourse = FXCollections.observableArrayList(
            new String("Online"),
            new String("Onsite")
    );

    private final CourseManageBUS courseManageBUS = new CourseManageBUS();
    public static CourseManageBUS.OnlineTableView selectedRowOnline = null;
    public static CourseManageBUS.OnsiteTableView selectedRowOnsite = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Handle();
        showOnlineCourseList(courseManageBUS.getOnlineTableView());
        showOnsiteCourseList(courseManageBUS.getOnsiteTableView());

        courseType.setItems(typeCourse);
        courseType.getSelectionModel().selectFirst();

        searchChoiceBoxbtn.setItems(searchTypes);
        searchChoiceBoxbtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();
        chooseTypeOfSearch();

    }

    private void chooseTypeOfCourse() {
        if (courseType.getSelectionModel().getSelectedItem().equals("Onsite")) {
            splitPane.setDividerPositions(0);
//            courseType.setItems(courseManageBUS.getOnsiteList());
//            courseType.getSelectionModel().select(0);
        } else {
            splitPane.setDividerPositions(1);
//            courseType.setItems(courseManageBUS.getOnlineList());
//            courseType.getSelectionModel().select(0);
        }

    }

    public void chooseTypeOfSearch(){
        if(courseType.getSelectionModel().getSelectedItem().equals("Onsite")){
            searchChoiceBoxbtn.setItems(courseManageBUS.getOnsiteList());
            searchChoiceBoxbtn.getSelectionModel().select(0);
        }
        else {
            searchChoiceBoxbtn.setItems(courseManageBUS.getOnlineList());
            searchChoiceBoxbtn.getSelectionModel().select(0);
        }
    }
    public int getSelectedSearchValue(){
        if(searchChoiceBoxbtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(searchChoiceBoxbtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    private void alert(String title, String Message) {
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

    public CourseManageBUS.OnlineTableView selectRowOnline() {
        if (onlineTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRowOnline = onlineTableView.getSelectionModel().getSelectedItem();
        return selectedRowOnline;

    }

    public CourseManageBUS.OnsiteTableView selectRowOnsite() {
        if (onsiteTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRowOnsite = onsiteTableView.getSelectionModel().getSelectedItem();
        return selectedRowOnsite;

    }

    public int getSelectedCourseId(){
        if(searchChoiceBoxbtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(searchChoiceBoxbtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }
//       public int editOnlineId = selectRowOnline().getCourseIdOnlineTableColumn();
//       public String editOnlineName= selectRowOnline().getCourseNameOnlineTableColumn();
//      public  String editOnlineDescription= selectRowOnline().getCourseDescriptionOnlineTableColumn();
//       public LocalDate editOnlineStart = selectRowOnline().getDateStartOnlineTableView();
//       public LocalDate editOnlineEnd = selectRowOnline().getDateEndOnlineTableView();
//       public String editOnlineUrl = selectRowOnline().getCourseUrlOnlineTableColumn();

//    public void chooseOfCourse(){
//        if(courseType.getSelectionModel().getSelectedItem().equals("Onsite")){
//            searchChoiceBoxbtn.setItems(courseManageBUS.getAllOnsiteCourseList());
//            searchChoiceBoxbtn.getSelectionModel().select(0);
//        }
//        else{
//            searchChoiceBoxbtn.setItems(resultBUS.getOnlineList());
//            searchChoiceBoxbtn.getSelectionModel().select(0);
//        }
//
//    }
    public void Handle() {
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showOnsiteCourseList(courseManageBUS.getOnsiteTableView());
                showOnlineCourseList(courseManageBUS.getOnlineTableView());
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                selectedRowOnline = selectRowOnline();
                if (selectedRowOnline != null) {
                    Assignment idOfAssignment = courseManageBUS.getIdAssignment(selectedRowOnline.getCourseIdOnlineTableColumn());
                    if (idOfAssignment != null) {
                        alert("Thông báo", "Vui lòng hủy phân công trước khi xóa");
                    }
                    CourseRegister courseExist = courseManageBUS.getCourseFromCourseRegisterByID(selectedRowOnline.getCourseIdOnlineTableColumn());
                    if (courseExist!=null){
                        alert("Thông báo","Khóa học đã được chấm điểm, không thể xóa");
                    }
                    else {
                        Course onlineCourse = courseManageBUS.deleteOnlineCourseTest(selectedRowOnline.getCourseIdOnlineTableColumn());
                        if (onlineCourse != null) {
                            Course course = courseManageBUS.deleteCourseOnline(onlineCourse);
                            if (course != null) {
                                alert("Thông báo", "Xóa thành công");
                                System.out.println(course);
                                showOnlineCourseList(courseManageBUS.getOnlineTableView());
                            } else {
                                alert("Thông báo", "Thất bại");
                            }
                        }

                    }
                }
                selectedRowOnsite = selectRowOnsite();
                if (selectedRowOnsite != null) {
                    Assignment idOfAssignment = courseManageBUS.getIdAssignment(selectedRowOnsite.getCourseIdOnsiteTableColumn());
                    if (idOfAssignment != null) {
                        alert("Thông báo", "Vui lòng hủy phân công trước khi xóa");

                    }
                    CourseRegister courseExist = courseManageBUS.getCourseFromCourseRegisterByID(selectedRowOnsite.getCourseIdOnsiteTableColumn());
                    if (courseExist!=null){
                        alert("Thông báo","Khóa học đã được chấm điểm, không thể xóa");
                    }
                    else {
                        Course onsiteCourse = courseManageBUS.deleteOnsiteCourseTest(selectRowOnsite().getCourseIdOnsiteTableColumn());

                        if (onsiteCourse != null) {
                            Course course = courseManageBUS.deleteCourseOnline(onsiteCourse);
                            if (course != null) {
                                alert("Thông báo", "Xóa thành công");
                                System.out.println("Xoa Thanh cong");
                                showOnsiteCourseList(courseManageBUS.getOnsiteTableView());
                            } else {
                                alert("Thông báo", "Thất bại");
                            }

                        }

                    }
                }
            }
        });
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedRowOnline = selectRowOnline();
                if (selectedRowOnline!=null){
                    Stage newstage = new Stage();
                    MainEditCourseOnline screen = new MainEditCourseOnline();
                    Stage oldstage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    newstage.initModality(Modality.WINDOW_MODAL);
                    newstage.initOwner(oldstage);
                    try {
                        screen.start(newstage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    FXMLLoader loader = new FXMLLoader();
//                    EditCourseOnlineController controller = (EditCourseOnlineController) loader.getController();
//                    String name = selectedRowOnline.getCourseNameOnlineTableColumn();
//                    String desc = selectedRowOnline.getCourseDescriptionOnlineTableColumn();
//                    String url = selectedRowOnline.getCourseUrlOnlineTableColumn();
//                    controller.show(name,desc,url);
                }
                selectedRowOnsite = selectRowOnsite();
                if (selectedRowOnsite!=null){
                    Stage newstage = new Stage();
                    MainEditCourseOnsite screen = new MainEditCourseOnsite();
                    Stage oldstage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    newstage.initModality(Modality.WINDOW_MODAL);
                    newstage.initOwner(oldstage);
                    try {
                        screen.start(newstage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


//                Stage stage x`= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("EditCourseOnlineGUI.fxml"));
//                Parent studentViewParent = null;
//                try {
//                    studentViewParent = loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(studentViewParent);
//                EditCourseOnlineController controller = loader.getController();
////                CourseManageBUS.OnlineTableView selected = onlineTableView.getSelectionModel().getSelectedItem();
////                controller.setStudent(selected);
//
//                stage.setScene(scene);

            }
        });
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                FXMLLoader loader = new FXMLLoader();
                Stage newstage = new Stage();
                MainAddCourse screen = new MainAddCourse();
                Stage oldstage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
        courseType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfCourse();
                chooseTypeOfSearch();
            }
        });

        searchbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(searchChoiceBoxbtn.getSelectionModel().getSelectedItem());
                if (courseType.getSelectionModel().getSelectedItem().equals("Onsite")){
                    showOnsiteCourseList(courseManageBUS.getOnsiteTableViewbyId(getSelectedCourseId()));
                }
                else {
                    showOnlineCourseList(courseManageBUS.getOnlineTableViewbyId(getSelectedCourseId()));
                }
            }
        });
//        searchChoiceBoxbtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                chooseTypeOfSearch();
//            }
//        });
    }

    //Do du lieu ra bang Onsite
    public void showOnsiteCourseList(ObservableList<CourseManageBUS.OnsiteTableView> onsiteTableViews) {
//        ObservableList<CourseManageBUS.OnsiteTableView> onsiteTableViews = courseManageBUS.getOnsiteTableView();
        courseIdOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Integer>("courseIdOnsiteTableColumn"));
        courseNameOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, String>("courseNameOnsiteTableColumn"));
        courseDescriptionOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, String>("courseDescriptionOnsiteTableColumn"));
        dateCreateOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Date>("dateCreateOnsiteTableColumn"));
        dateStartOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Date>("dateStartOnsiteTableColumn"));
        dateEndOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Date>("dateEndOnsiteTableColumn"));
        onsiteCourseIdOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Integer>("onsiteCourseIdOnsiteTableColumn"));
        lessonQuantityOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Integer>("lessonQuantityOnsiteTableColumn"));
        dayOccurOnsiteTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnsiteTableView, Date>("dayOccurOnsiteTableColumn"));
        onsiteTableView.setItems(onsiteTableViews);
    }

    //Do du lieu ra bang Onsite
    public void showOnlineCourseList(ObservableList<CourseManageBUS.OnlineTableView> onlineTableViews) {
//        onlineTableViews= courseManageBUS.getOnlineTableView();
        courseIdOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Integer>("courseIdOnlineTableColumn"));
        courseNameOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, String>("courseNameOnlineTableColumn"));
        courseDescriptionOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, String>("courseDescriptionOnlineTableColumn"));
        dateCreateOnlineTableView.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Date>("dateCreateOnlineTableView"));
        dateStartOnlineTableView.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Date>("dateStartOnlineTableView"));
        dateEndOnlineTableView.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Date>("dateEndOnlineTableView"));
        onlineCourseIdOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Integer>("onlineCourseIdOnlineTableColumn"));
        courseUrlOnlineTableColumn.setCellValueFactory(new PropertyValueFactory<CourseManageBUS.OnlineTableView, Integer>("courseUrlOnlineTableColumn"));
        onlineTableView.setItems(onlineTableViews);
    }

}
