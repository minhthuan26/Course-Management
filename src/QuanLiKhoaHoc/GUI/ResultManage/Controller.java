package QuanLiKhoaHoc.GUI.ResultManage;

import QuanLiKhoaHoc.BUS.ResultManage.ResultBUS;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final ResultBUS resultBUS = new ResultBUS();
    private final ObservableList<String> courseTypes = FXCollections.observableArrayList(
            new String("Onsite"),
            new String("Online")
    );
    private final ObservableList<String> searchTypes = FXCollections.observableArrayList(
            new String("Khoá học"),
            new String("Học viên")
    );


    private static ResultBUS.ResultTableView selectedRow = null;

    @FXML
    private ChoiceBox<String> courseTypeChoiceBtn;

    @FXML
    private ChoiceBox<String> courseChoiceBtn;

    @FXML
    private ChoiceBox<String> studentChoiceBtn;

    @FXML
    private ChoiceBox<String> searchTypeChoiceBtn;

    @FXML
    private ChoiceBox<String> searchValueChoiceBtn;

    @FXML
    private Button setScoreBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Spinner<Double> scoreSpinner;

    @FXML
    private TableView<ResultBUS.ResultTableView> resultTableView;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, Integer> courseIdTableColumn;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, Integer> studentIdTableColumn;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, String> courseNameTableColumn;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, String> studentNameTableColumn;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, Float> scoreTableColumn;

    @FXML
    private TableColumn<ResultBUS.ResultTableView, String> ratingTableColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setDefault();
        Handle();
    }

    public void setDefault(){
        courseTypeChoiceBtn.setItems(courseTypes);
        courseTypeChoiceBtn.getSelectionModel().selectFirst();
        searchTypeChoiceBtn.setItems(searchTypes);
        searchTypeChoiceBtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();
        chooseTypeOfSearch();
        scoreSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10, 0));
        showResultList(resultBUS.getResultTableViewList());
    }

    public void Handle(){
        courseTypeChoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfCourse();
            }
        });

        searchTypeChoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfSearch();
            }
        });

        courseChoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getStudenNameList();
            }
        });

        setScoreBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                int courseId = getSelectedCourseId();
                int studentId =  getSelectedStudentId();
                String score = scoreSpinner.getValue().toString();
                if(courseId < 0){
                    System.out.println("Nhập điểm thất bại");
                    alert("Thông báo", "Tất cả các lớp đã được phân công giảng dạy");
                }
                else if(studentId < 0){
                    System.out.println("Nhập điểm thất bại");
                    alert("Thông báo", "Tất cả các sinh viên đã được nhập điểm");
                }
                else{
                    CourseRegister courseRegister = resultBUS.setCourseRegister(courseId, studentId);
                    if(courseRegister != null){
                        CourseResult courseResult = resultBUS.setCourseResult(courseRegister.getRegisterId());
                        if(courseResult != null){
                            ResultDetail resultDetail = resultBUS.setResultDetail(courseResult.getResultId(), score);
                            if(resultDetail != null){
                                System.out.println("Nhập điểm thành công");
                                alert("Thông báo", "Nhập điểm thành công");
                                setDefault();
                            }
                            else{
                                System.out.println("Nhập điểm thật bại");
                                errorAlert("Lỗi", "Nhập điểm thất bại");
                                System.out.println(resultBUS.deleteCourseResult(courseResult));
                                System.out.println(resultBUS.deleteCourseRegister(courseRegister));
                            }
                        }
                        else{
                            System.out.println("Nhập điểm thất bại");
                            errorAlert("Lỗi", "Nhập điểm  thất bại");
                            System.out.println(resultBUS.deleteCourseRegister(courseRegister));
                        }
                    }
                    else{
                        System.out.println("Nhập điểm thật bại");
                        errorAlert("Lỗi", "Nhập điểm thất bại");
                    }
                }
            }
        });

        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setDefault();
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedRow = selectRow();
                if(selectedRow != null){
                    CourseRegister courseRegister = resultBUS.getCourseRegisterByIds(selectedRow.getCourseId(), selectedRow.getStudentId());
                    if(courseRegister != null){
                        CourseResult courseResult = resultBUS.getCourseResultByRegisterId(courseRegister.getRegisterId());
                        if(courseResult != null){
                            ResultDetail resultDetail = resultBUS.getResultDetailByResultId(courseResult.getResultId());
                            if(resultDetail != null){
                                resultDetail = resultBUS.deleteResultDetail(resultDetail);
                                courseResult = resultBUS.deleteCourseResult(courseResult);
                                courseRegister = resultBUS.deleteCourseRegister(courseRegister);
                                System.out.println("Huỷ thành công");
                                alert("Thông báo", "Đã huỷ nhập điểm");
                                setDefault();
                            }
                            else{
                                System.out.println("Huỷ thật bại");
                                errorAlert("Lỗi", "Huỷ thất bại");
                            }
                        }
                        else{
                            System.out.println("Huỷ thật bại");
                            errorAlert("Lỗi", "Huỷ thất bại");
                        }
                    }
                    else{
                        System.out.println("Huỷ thật bại");
                        errorAlert("Lỗi", "Huỷ thất bại");
                    }

                }
                else
                    errorAlert("Lỗi", "Vui lòng chọn 1 dòng trong bảng trước khi thực hiện huỷ");
            }
        });

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int searchValue = getSelectedSearchValue();
                if(searchValue > 0){
                    if(searchTypeChoiceBtn.getSelectionModel().getSelectedItem().equals("Khoá học")){
                        showResultList(resultBUS.getSearchResultByCourse(getSelectedSearchValue()));
                    }
                    else{
                        showResultList(resultBUS.getSearchResultByStudent(getSelectedSearchValue()));
                    }
                }
            }
        });
    }

    public int getSelectedCourseId(){
        if(courseChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(courseChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    public int getSelectedStudentId(){
        if(studentChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(studentChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    public int getSelectedSearchValue(){
        if(searchValueChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(searchValueChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    public void chooseTypeOfCourse(){
        if(courseTypeChoiceBtn.getSelectionModel().getSelectedItem().equals("Onsite")){
            courseChoiceBtn.setItems(resultBUS.getOnsiteList());
            courseChoiceBtn.getSelectionModel().select(0);
        }
        else{
            courseChoiceBtn.setItems(resultBUS.getOnlineList());
            courseChoiceBtn.getSelectionModel().select(0);
        }
        getStudenNameList();
    }

    public void chooseTypeOfSearch(){
        if(searchTypeChoiceBtn.getSelectionModel().getSelectedItem().equals("Khoá học")){
            searchValueChoiceBtn.setItems(resultBUS.getAllCourseListToGUI());
            searchValueChoiceBtn.getSelectionModel().select(0);
        }
        else{
            searchValueChoiceBtn.setItems(resultBUS.getAllStudentListToGUI());
            searchValueChoiceBtn.getSelectionModel().select(0);
        }
    }

    public void getStudenNameList(){
        studentChoiceBtn.setItems(resultBUS.getStudentNoneResultListToGUI(getSelectedCourseId()));
        studentChoiceBtn.getSelectionModel().select(0);
    }

    public ResultBUS.ResultTableView selectRow(){
        if (resultTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRow = resultTableView.getSelectionModel().getSelectedItem();
        return selectedRow;
    }

    public void showResultList(ObservableList<ResultBUS.ResultTableView> resultTableViewList){
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, Integer>("CourseId"));
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, Integer>("StudentId"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, String>("CourseName"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, String>("StudentName"));
        scoreTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, Float>("Score"));
        ratingTableColumn.setCellValueFactory(new PropertyValueFactory<ResultBUS.ResultTableView, String>("Rating"));
        resultTableView.setItems(resultTableViewList);
    }

    private void errorAlert(String title, String Message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
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

    //hiển thị thông báo
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
}
