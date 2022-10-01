package QuanLiKhoaHoc.GUI.ResultManage;

import QuanLiKhoaHoc.BUS.ResultManage.ResultBUS;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final ResultBUS resultBUS = new ResultBUS();
    private ObservableList<ResultTableView> resultTableViewList;
    private final ObservableList<String> types = FXCollections.observableArrayList(
            new String("Onsite"),
            new String("Online")
    );

    private static ResultTableView selectedRow = null;

    @FXML
    private ChoiceBox<String> courseTypeChoiceBtn;

    @FXML
    private ChoiceBox<String> courseChoiceBtn;

    @FXML
    private ChoiceBox<String> studentChoiceBtn;

    @FXML
    private Button setScoreBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Spinner<Double> scoreSpinner;

    @FXML
    private TableView<ResultTableView> resultTableView;

    @FXML
    private TableColumn<ResultTableView, Integer> courseIdTableColumn;

    @FXML
    private TableColumn<ResultTableView, Integer> studentIdTableColumn;

    @FXML
    private TableColumn<ResultTableView, String> courseNameTableColumn;

    @FXML
    private TableColumn<ResultTableView, String> studentNameTableColumn;

    @FXML
    private TableColumn<ResultTableView, Float> scoreTableColumn;

    @FXML
    private TableColumn<ResultTableView, String> ratingTableColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setDefault();
        Handle();
    }

    public void setDefault(){
        courseTypeChoiceBtn.setItems(types);
        courseTypeChoiceBtn.getSelectionModel().selectFirst();
        chooseTypeOfCourse();
        scoreSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10, 0));
        showAssignmentList();
    }

    public void Handle(){
        courseTypeChoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseTypeOfCourse();
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
    }

    private int getSelectedCourseId(){
        if(courseChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(courseChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
    }

    private int getSelectedStudentId(){
        if(studentChoiceBtn.getSelectionModel().getSelectedItem() == null)
            return -1;
        return Integer.parseInt(studentChoiceBtn.getSelectionModel().getSelectedItem().split("_")[0]);
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

    public void getStudenNameList(){
        studentChoiceBtn.setItems(resultBUS.getStudentNoneResultListToGUI(getSelectedCourseId()));
        studentChoiceBtn.getSelectionModel().select(0);
    }

    public ResultTableView selectRow(){
        if (resultTableView.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedRow = resultTableView.getSelectionModel().getSelectedItem();
        return selectedRow;
    }

    public ObservableList<ResultTableView> getResultTableViewList(){
        ObservableList<CourseRegister> allCourseRegisterList = resultBUS.getAllCourseRegisterList();
        resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allStudentList = resultBUS.getAllStudentListToGUI();
        ObservableMap<Integer, String> allCourseList = resultBUS.getAllCourseListToGUI();
        for(CourseRegister courseRegister : allCourseRegisterList){
            ResultTableView resultTableView = new ResultTableView();
            for(Map.Entry<Integer, String> student : allStudentList.entrySet()){
                if(student.getKey() == courseRegister.getPersonId()){
                    resultTableView.setStudentId(student.getKey());
                    resultTableView.setStudentName(student.getValue());
                    break;
                }
            }
            for(Map.Entry<Integer, String> course : allCourseList.entrySet()){
                if(course.getKey() == courseRegister.getCourseId()){
                    resultTableView.setCourseId(course.getKey());
                    resultTableView.setCourseName(course.getValue());
                    break;
                }
            }
            CourseResult courseResult = resultBUS.getCourseResultByRegisterId(courseRegister.getRegisterId());
            ResultDetail resultDetail = resultBUS.getResultDetailByResultId(courseResult.getResultId());
            resultTableView.setScore(resultDetail.getScore());
            resultTableView.setRating(resultDetail.getRating());
            resultTableViewList.add(resultTableView);
        }
        return resultTableViewList;
    }

    public void showAssignmentList(){
        resultTableViewList = getResultTableViewList();
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, Integer>("CourseId"));
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, Integer>("StudentId"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, String>("CourseName"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, String>("StudentName"));
        scoreTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, Float>("Score"));
        ratingTableColumn.setCellValueFactory(new PropertyValueFactory<ResultTableView, String>("Rating"));
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

    public static class ResultTableView{
        private int CourseId, StudentId;
        private String CourseName, StudentName, Rating;
        private float Score;

        public int getCourseId() {
            return CourseId;
        }

        public void setCourseId(int courseId) {
            CourseId = courseId;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int studentId) {
            StudentId = studentId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String courseName) {
            CourseName = courseName;
        }

        public String getStudentName() {
            return StudentName;
        }

        public void setStudentName(String studentName) {
            StudentName = studentName;
        }

        public String getRating() {
            return Rating;
        }

        public void setRating(String rating) {
            Rating = rating;
        }

        public float getScore() {
            return Score;
        }

        public void setScore(float score) {
            Score = score;
        }

        @Override
        public String toString() {
            return "ResultTableView{" +
                    "CourseId=" + CourseId +
                    ", StudentId=" + StudentId +
                    ", CourseName='" + CourseName + '\'' +
                    ", StudentName='" + StudentName + '\'' +
                    ", Rating='" + Rating + '\'' +
                    ", Score=" + Score +
                    '}';
        }
    }
}
