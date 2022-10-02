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
    private TableView<CourseManageBUS.OnlineTableView> onlineTableView;
    @FXML
    private TableColumn<CourseManageBUS.OnlineTableView, Integer> courseIdOnlineTableColumn;
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
    private final ObservableList<String> typeCourse = FXCollections.observableArrayList(
            new String("Online"),
            new String("Onsite")
    );

    private final CourseManageBUS courseManageBUS = new CourseManageBUS();
    private static CourseManageBUS.OnlineTableView selectedRowOnline = null;
    private static CourseManageBUS.OnsiteTableView selectedRowOnsite = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Handle();
        showOnlineCourseList();
        showOnsiteCourseList();

        courseType.setItems(typeCourse);
        courseType.getSelectionModel().selectFirst();
        chooseTypeOfCourse();

    }

    private void chooseTypeOfCourse() {
        if (courseType.getSelectionModel().getSelectedItem().equals("Onsite")) {
            splitPane.setDividerPositions(0);
        } else {
            splitPane.setDividerPositions(1);
        }
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

    public void Handle() {
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
                if (selectedRowOnline != null) {
                    Assignment idOfAssignment = courseManageBUS.getIdAssignment(selectedRowOnline.getCourseIdOnlineTableColumn());
                    if (idOfAssignment != null) {
                        alert("Thông báo", "Vui lòng hủy phân công trước khi xóa");

                    } else {
                        Course onlineCourse = courseManageBUS.deleteOnlineCourseTest(selectedRowOnline.getCourseIdOnlineTableColumn());
                        if (onlineCourse != null) {
                            Course course = courseManageBUS.deleteCourseOnline(onlineCourse);
                            if (course != null) {
                                alert("Thông báo", "Xóa thành công");
                                System.out.println(course);
                                showOnlineCourseList();
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

                    } else {
                        Course onsiteCourse = courseManageBUS.deleteOnsiteCourseTest(selectRowOnsite().getCourseIdOnsiteTableColumn());

                        if (onsiteCourse != null) {
                            Course course = courseManageBUS.deleteCourseOnline(onsiteCourse);
                            if (course != null) {
                                alert("Thông báo", "Xóa thành công");
                                System.out.println("Xoa Thanh cong");
                                showOnsiteCourseList();
                            } else {
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
    }

    //Do du lieu ra bang Onsite
    public void showOnsiteCourseList() {
        ObservableList<CourseManageBUS.OnsiteTableView> onsiteTableViews = courseManageBUS.getOnsiteTableView();
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
    public void showOnlineCourseList() {
        ObservableList<CourseManageBUS.OnlineTableView> onlineTableViews = courseManageBUS.getOnlineTableView();
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
